import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Prática 3 - Servidor de Chat Multi-cliente (TCP + Threads):
 * Aceita múltiplas conexões simultâneas e cria uma Thread (ClientHandler) para cada cliente.
 */
public class ChatServer {

    public static final int PORTA = 6000;
    private static final List<PrintWriter> clientesConectados = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        System.out.println("=== Servidor de Chat TCP iniciado na porta " + PORTA + " ===");

        try (ServerSocket serverSocket = new ServerSocket(PORTA)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nova conexão recebida de: " + clientSocket.getInetAddress().getHostAddress());

                ClientHandler handler = new ClientHandler(clientSocket, clientesConectados);
                Thread threadCliente = new Thread(handler);
                threadCliente.start();
            }
        } catch (Exception e) {
            System.err.println("Erro no servidor de chat: " + e.getMessage());
        }
    }

    public static void broadcast(String mensagem) {
        for (PrintWriter writer : clientesConectados) {
            writer.println(mensagem);
        }
    }
}
