import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Exemplo Teórico TCP (Servidor):
 * Aceita uma conexão por vez, lê uma linha, converte para maiúsculas e responde.
 */
public class ExemploTCPSimplesServer {

    public static void main(String[] args) throws Exception {
        final int PORTA = 6789;

        try (ServerSocket welcomeSocket = new ServerSocket(PORTA)) {
            System.out.println("Servidor TCP aguardando conexões na porta " + PORTA + "...");

            while (true) {
                try (Socket connectionSocket = welcomeSocket.accept();
                     BufferedReader entrada = new BufferedReader(
                             new InputStreamReader(connectionSocket.getInputStream(), StandardCharsets.UTF_8));
                     PrintWriter saida = new PrintWriter(connectionSocket.getOutputStream(), true, StandardCharsets.UTF_8)) {

                    System.out.println("Cliente conectado: " + connectionSocket.getInetAddress().getHostAddress());
                    String linha = entrada.readLine();

                    if (linha != null) {
                        System.out.println("Recebido: " + linha);
                        saida.println(linha.toUpperCase());
                    }
                }
            }
        }
    }
}
