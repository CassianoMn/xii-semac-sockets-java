import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Exemplo Teórico UDP (Cliente):
 * Lê uma linha do teclado, envia via datagrama UDP para localhost:9876
 * e exibe a resposta do servidor.
 */
public class ExemploUDPSimplesClient {

    public static void main(String[] args) throws Exception {
        final String HOST = "localhost";
        final int PORTA = 9876;

        try (DatagramSocket clientSocket = new DatagramSocket();
             Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {

            System.out.print("Digite uma mensagem para enviar via UDP: ");
            String mensagem = scanner.nextLine();

            byte[] dadosEnvio = mensagem.getBytes(StandardCharsets.UTF_8);
            InetAddress ipServidor = InetAddress.getByName(HOST);

            DatagramPacket pacoteEnvio = new DatagramPacket(dadosEnvio, dadosEnvio.length, ipServidor, PORTA);
            clientSocket.send(pacoteEnvio);

            byte[] bufferResposta = new byte[1024];
            DatagramPacket pacoteResposta = new DatagramPacket(bufferResposta, bufferResposta.length);
            clientSocket.receive(pacoteResposta);

            String resposta = new String(
                    pacoteResposta.getData(),
                    0,
                    pacoteResposta.getLength(),
                    StandardCharsets.UTF_8
            );
            System.out.println("Resposta do servidor: " + resposta);
        }
    }
}
