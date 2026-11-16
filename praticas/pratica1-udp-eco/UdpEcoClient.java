import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Prática 1 - Cliente UDP de Eco/Ping:
 * Envia mensagens em loop para o servidor UDP até o usuário digitar "sair".
 */
public class UdpEcoClient {

    public static final String HOST = "localhost";
    public static final int PORTA = 9876;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket();
             Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {

            InetAddress ipServidor = InetAddress.getByName(HOST);
            System.out.println("=== Cliente UDP conectado a " + HOST + ":" + PORTA + " ===");
            System.out.println("Digite sua mensagem (ou 'sair' para encerrar):");

            while (true) {
                System.out.print("> ");
                String mensagem = scanner.nextLine();

                if ("sair".equalsIgnoreCase(mensagem.trim())) {
                    System.out.println("Encerrando cliente UDP...");
                    break;
                }

                byte[] dadosEnvio = mensagem.getBytes(StandardCharsets.UTF_8);
                DatagramPacket pacoteEnvio = new DatagramPacket(dadosEnvio, dadosEnvio.length, ipServidor, PORTA);
                socket.send(pacoteEnvio);

                byte[] bufferResposta = new byte[1024];
                DatagramPacket pacoteResposta = new DatagramPacket(bufferResposta, bufferResposta.length);
                socket.receive(pacoteResposta);

                String resposta = new String(
                        pacoteResposta.getData(),
                        0,
                        pacoteResposta.getLength(),
                        StandardCharsets.UTF_8
                );
                System.out.println("Servidor -> " + resposta);
            }
        } catch (Exception e) {
            System.err.println("Erro no cliente UDP: " + e.getMessage());
        }
    }
}
