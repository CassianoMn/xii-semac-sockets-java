import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Exemplo Teórico TCP (Cliente):
 * Conecta ao servidor TCP em localhost:6789, envia uma linha e lê a resposta.
 */
public class ExemploTCPSimplesClient {

    public static void main(String[] args) throws Exception {
        final String HOST = "localhost";
        final int PORTA = 6789;

        try (Socket clientSocket = new Socket(HOST, PORTA);
             PrintWriter saida = new PrintWriter(clientSocket.getOutputStream(), true, StandardCharsets.UTF_8);
             BufferedReader entrada = new BufferedReader(
                     new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
             Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {

            System.out.print("Digite uma mensagem para enviar via TCP: ");
            String mensagem = scanner.nextLine();

            saida.println(mensagem); // println envia o '\n' esperado pelo readLine() do servidor
            String resposta = entrada.readLine();

            System.out.println("Resposta do servidor: " + resposta);
        }
    }
}
