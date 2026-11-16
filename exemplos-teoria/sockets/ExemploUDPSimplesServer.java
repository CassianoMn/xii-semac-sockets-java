import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

/**
 * Exemplo Teórico UDP (Servidor):
 * Recebe um datagrama, converte para maiúsculas e devolve ao remetente.
 * Baseado no exemplo clássico de Kurose & Ross, atualizado para Java moderno.
 */
public class ExemploUDPSimplesServer {

    public static void main(String[] args) throws Exception {
        final int PORTA = 9876;

        try (DatagramSocket serverSocket = new DatagramSocket(PORTA)) {
            System.out.println("Servidor UDP escutando na porta " + PORTA + "...");

            byte[] bufferRecebimento = new byte[1024];

            while (true) {
                DatagramPacket pacoteRecebido = new DatagramPacket(bufferRecebimento, bufferRecebimento.length);
                serverSocket.receive(pacoteRecebido);

                String mensagem = new String(
                        pacoteRecebido.getData(),
                        0,
                        pacoteRecebido.getLength(),
                        StandardCharsets.UTF_8
                );

                InetAddress ipCliente = pacoteRecebido.getAddress();
                int portaCliente = pacoteRecebido.getPort();
                System.out.println("Recebido de " + ipCliente.getHostAddress() + ":" + portaCliente + " -> " + mensagem);

                byte[] dadosResposta = mensagem.toUpperCase().getBytes(StandardCharsets.UTF_8);
                DatagramPacket pacoteResposta = new DatagramPacket(
                        dadosResposta,
                        dadosResposta.length,
                        ipCliente,
                        portaCliente
                );

                serverSocket.send(pacoteResposta);
            }
        }
    }
}
