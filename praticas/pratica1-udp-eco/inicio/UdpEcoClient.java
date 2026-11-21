import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Prática 1 - Cliente UDP de Eco/Ping:
 * Envia mensagens em loop para o servidor UDP até o usuário digitar "sair".
 */
public class UdpEcoClient {

    public static final String HOST = "localhost";
    public static final int PORTA = 9876;
    private static final int TEMPO_ESPERA_MS = 2_000;
    private static final int LIMITE_ENVIO_BYTES = 1_024;
    private static final int BUFFER_RESPOSTA_BYTES = 8_192;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket();
             Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {

            InetAddress ipServidor = InetAddress.getByName(HOST);
            // TODO 2: configure o tempo máximo de espera.
            System.out.println("=== Cliente UDP. Destino: " + HOST + ":" + PORTA + " ===");
            System.out.println("Digite sua mensagem (ou 'sair' para encerrar):");

            while (true) {
                System.out.print("> ");
                if (!scanner.hasNextLine()) {
                    break;
                }
                String mensagem = scanner.nextLine();

                if ("sair".equalsIgnoreCase(mensagem.trim())) {
                    System.out.println("Encerrando cliente UDP...");
                    break;
                }

                byte[] dadosEnvio = mensagem.getBytes(StandardCharsets.UTF_8);
                if (dadosEnvio.length > LIMITE_ENVIO_BYTES) {
                    System.out.println("Digite uma mensagem de até " + LIMITE_ENVIO_BYTES + " bytes em UTF-8.");
                    continue;
                }
                DatagramPacket pacoteEnvio = new DatagramPacket(dadosEnvio, dadosEnvio.length, ipServidor, PORTA);
                socket.send(pacoteEnvio);

                // O prefixo e a conversão em maiúsculas podem aumentar a resposta.
                byte[] bufferResposta = new byte[BUFFER_RESPOSTA_BYTES];
                DatagramPacket pacoteResposta = new DatagramPacket(bufferResposta, bufferResposta.length);
                // TODO 2: trate SocketTimeoutException e volte ao prompt.
                socket.receive(pacoteResposta);

                String resposta = new String(
                        pacoteResposta.getData(),
                        pacoteResposta.getOffset(),
                        pacoteResposta.getLength(),
                        StandardCharsets.UTF_8
                );
                System.out.println("Servidor -> " + resposta);
            }
        } catch (IOException e) {
            System.err.println("Erro no cliente UDP: " + e.getMessage());
        }
    }
}
