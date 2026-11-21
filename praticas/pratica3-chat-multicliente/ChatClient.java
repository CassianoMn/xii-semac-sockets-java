import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Prática 3 - Cliente de Chat Multi-cliente:
 * Usa uma Thread secundária para receber mensagens do servidor em tempo real
 * enquanto a Thread principal lê o que o usuário digita no teclado.
 */
public class ChatClient {

    public static final String HOST_PADRAO = "localhost";
    public static final int PORTA = 6000;
    private static final int TEMPO_CONEXAO_MS = 3_000;

    public static void main(String[] args) {
        String host = (args.length > 0) ? args[0] : HOST_PADRAO;

        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, PORTA), TEMPO_CONEXAO_MS);
            System.out.println("Conectado ao chat em " + host + ":" + PORTA);
            conversar(socket);
        } catch (IOException e) {
            System.err.println("Erro no chat: " + e.getMessage());
        }
    }

    private static void conversar(Socket socket) throws IOException {
        try (BufferedReader entrada = new BufferedReader(
                     new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             PrintWriter saida = new PrintWriter(socket.getOutputStream(), true, StandardCharsets.UTF_8);
             Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {

            System.out.println("Dica: digite /sair para encerrar.");

            AtomicBoolean conexaoEncerrada = new AtomicBoolean();
            // Esta thread recebe da rede enquanto main espera pelo teclado.
            Thread receptor = new Thread(() -> {
                try {
                    String mensagemServidor;
                    while ((mensagemServidor = entrada.readLine()) != null) {
                        System.out.println(mensagemServidor);
                    }
                } catch (IOException e) {
                    if (!conexaoEncerrada.get()) {
                        System.out.println("Falha ao ler do servidor: " + e.getMessage());
                    }
                } finally {
                    if (!conexaoEncerrada.getAndSet(true)) {
                        System.out.println("Conexão encerrada. Pressione Enter para sair.");
                    }
                }
            });
            receptor.setDaemon(true);
            receptor.start();

            // Thread principal lê o teclado e envia ao servidor
            while (!conexaoEncerrada.get() && scanner.hasNextLine()) {
                String texto = scanner.nextLine();
                if (conexaoEncerrada.get()) {
                    break;
                }
                boolean sair = "/sair".equalsIgnoreCase(texto.trim());
                if (sair) {
                    conexaoEncerrada.set(true);
                }
                saida.println(texto);
                if (saida.checkError() || sair) {
                    break;
                }
            }
            conexaoEncerrada.set(true);
        }
    }
}
