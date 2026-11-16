import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Prática 3 - Manipulador de Cliente (Runnable):
 * Roda em uma Thread dedicada para cada cliente conectado ao ChatServer.
 */
public class ClientHandler implements Runnable {

    private final Socket socket;
    private final List<PrintWriter> clientes;
    private PrintWriter saida;
    private String nomeUsuario = "Anônimo";

    public ClientHandler(Socket socket, List<PrintWriter> clientes) {
        this.socket = socket;
        this.clientes = clientes;
    }

    @Override
    public void run() {
        try (BufferedReader entrada = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true, StandardCharsets.UTF_8)) {

            this.saida = out;
            out.println("Digite seu apelido para entrar no chat:");

            String apelido = entrada.readLine();
            if (apelido != null && !apelido.trim().isEmpty()) {
                this.nomeUsuario = apelido.trim();
            }

            clientes.add(out);
            System.out.println(nomeUsuario + " entrou no chat.");
            ChatServer.broadcast("[SERVIDOR] " + nomeUsuario + " entrou no chat!");

            String mensagem;
            while ((mensagem = entrada.readLine()) != null) {
                if ("/sair".equalsIgnoreCase(mensagem.trim())) {
                    break;
                }
                String formatada = nomeUsuario + ": " + mensagem;
                System.out.println(formatada);
                ChatServer.broadcast(formatada);
            }
        } catch (Exception e) {
            System.err.println("Conexão perdida com " + nomeUsuario + ": " + e.getMessage());
        } finally {
            if (saida != null) {
                clientes.remove(saida);
            }
            ChatServer.broadcast("[SERVIDOR] " + nomeUsuario + " saiu do chat.");
            System.out.println(nomeUsuario + " desconectado.");
            try {
                socket.close();
            } catch (Exception ignored) {
            }
        }
    }
}
