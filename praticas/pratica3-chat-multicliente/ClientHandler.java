import java.io.BufferedReader;
import java.io.IOException;
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

    public ClientHandler(Socket socket, List<PrintWriter> clientes) {
        this.socket = socket;
        this.clientes = clientes;
    }

    @Override
    public void run() {
        try (socket;
             BufferedReader entrada = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true, StandardCharsets.UTF_8)) {

            out.println("Digite seu apelido para entrar no chat:");

            String apelido = entrada.readLine();
            if (apelido == null || "/sair".equalsIgnoreCase(apelido.trim())) {
                return;
            }
            String nomeUsuario = apelido.isBlank() ? "Anônimo" : apelido.trim();

            clientes.add(out);
            try {
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
            } finally {
                clientes.remove(out);
                ChatServer.broadcast("[SERVIDOR] " + nomeUsuario + " saiu do chat.");
                System.out.println(nomeUsuario + " desconectado.");
            }
        } catch (IOException e) {
            System.err.println("Conexão encerrada: " + e.getMessage());
        }
    }
}
