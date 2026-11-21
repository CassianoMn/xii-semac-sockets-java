import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

/**
 * Prática 1 - Servidor UDP de Eco/Ping:
 * Escuta na porta 9876, recebe mensagens em loop, mostra quem enviou
 * e devolve uma confirmação com a mensagem em maiúsculas.
 */
public class UdpEcoServer {

    public static final int PORTA = 9876;
    private static final int LIMITE_ENTRADA_BYTES = 1_024;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(PORTA)) {
            System.out.println("=== Servidor UDP de Eco iniciado na porta " + PORTA + " ===");
            byte[] buffer = new byte[LIMITE_ENTRADA_BYTES + 1];

            while (true) {
                DatagramPacket pacoteRecebido = new DatagramPacket(buffer, buffer.length);
                socket.receive(pacoteRecebido);
                if (pacoteRecebido.getLength() > LIMITE_ENTRADA_BYTES) {
                    System.out.println("Datagrama ignorado: excede o limite de " + LIMITE_ENTRADA_BYTES + " bytes.");
                    continue;
                }

                String mensagem = new String(
                        pacoteRecebido.getData(),
                        pacoteRecebido.getOffset(),
                        pacoteRecebido.getLength(),
                        StandardCharsets.UTF_8
                );

                InetAddress ipCliente = pacoteRecebido.getAddress();
                int portaCliente = pacoteRecebido.getPort();

                System.out.printf("[%s:%d] disse: %s%n", ipCliente.getHostAddress(), portaCliente, mensagem);

                String resposta = "ECO: " + mensagem.toUpperCase(Locale.ROOT);
                byte[] dadosResposta = resposta.getBytes(StandardCharsets.UTF_8);

                // TODO 1: construa o datagrama de resposta e envie ao remetente.
            }
        } catch (IOException e) {
            System.err.println("Erro no servidor UDP: " + e.getMessage());
        }
    }
}
