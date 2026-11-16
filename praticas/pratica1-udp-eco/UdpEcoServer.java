import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

/**
 * Prática 1 - Servidor UDP de Eco/Ping:
 * Escuta na porta 9876, recebe mensagens em loop, mostra quem enviou
 * e devolve uma confirmação com a mensagem em maiúsculas.
 */
public class UdpEcoServer {

    public static final int PORTA = 9876;

    public static void main(String[] args) {
        System.out.println("=== Servidor UDP de Eco iniciado na porta " + PORTA + " ===");

        try (DatagramSocket socket = new DatagramSocket(PORTA)) {
            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket pacoteRecebido = new DatagramPacket(buffer, buffer.length);
                socket.receive(pacoteRecebido);

                String mensagem = new String(
                        pacoteRecebido.getData(),
                        0,
                        pacoteRecebido.getLength(),
                        StandardCharsets.UTF_8
                );

                InetAddress ipCliente = pacoteRecebido.getAddress();
                int portaCliente = pacoteRecebido.getPort();

                System.out.printf("[%s:%d] disse: %s%n", ipCliente.getHostAddress(), portaCliente, mensagem);

                String resposta = "ECO: " + mensagem.toUpperCase();
                byte[] dadosResposta = resposta.getBytes(StandardCharsets.UTF_8);

                DatagramPacket pacoteResposta = new DatagramPacket(
                        dadosResposta,
                        dadosResposta.length,
                        ipCliente,
                        portaCliente
                );
                socket.send(pacoteResposta);
            }
        } catch (Exception e) {
            System.err.println("Erro no servidor UDP: " + e.getMessage());
        }
    }
}
