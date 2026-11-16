import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Prática 3 - Cliente de Chat Multi-cliente:
 * Usa uma Thread secundária para receber mensagens do servidor em tempo real
 * enquanto a Thread principal lê o que o usuário digita no teclado.
 */
public class ChatClient {

    public static final String HOST_PADRAO = "localhost";
    public static final int PORTA = 6000;

    public static void main(String[] args) {
        String host = (args.length > 0) ? args[0] : HOST_PADRAO;

        try (Socket socket = new Socket(host, PORTA);
             BufferedReader entrada = new BufferedReader(
                     new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             PrintWriter saida = new PrintWriter(socket.getOutputStream(), true, StandardCharsets.UTF_8);
             Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {

            System.out.println("Conectado ao chat em " + host + ":" + PORTA);
            System.out.println("Dica: digite /sair para encerrar.");

            // Thread dedicada apenas para escutar mensagens vindas do servidor
            Thread receptor = new Thread(() -> {
                try {
                    String mensagemServidor;
                    while ((mensagemServidor = entrada.readLine()) != null) {
                        System.out.println(mensagemServidor);
                    }
                } catch (Exception e) {
                    System.out.println("Conexão com o servidor encerrada.");
                }
            });
            receptor.setDaemon(true);
            receptor.start();

            // Thread principal lê o teclado e envia ao servidor
            while (scanner.hasNextLine()) {
                String texto = scanner.nextLine();
                saida.println(texto);
                if ("/sair".equalsIgnoreCase(texto.trim())) {
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao conectar ao chat: " + e.getMessage());
        }
    }
}
