import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Prática 2 - Cliente TCP: Jogo de Adivinhação.
 * Conecta ao servidor na porta 5000 e envia palpites até acertar ou digitar "sair".
 */
public class JogoAdivinhacaoClient {

    public static final String HOST = "localhost";
    public static final int PORTA = 5000;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORTA);
             BufferedReader entrada = new BufferedReader(
                     new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             PrintWriter saida = new PrintWriter(socket.getOutputStream(), true, StandardCharsets.UTF_8);
             Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {

            // Lê a mensagem de boas-vindas do servidor
            String boasVindas = entrada.readLine();
            System.out.println("Servidor: " + boasVindas);

            while (true) {
                System.out.print("Seu palpite (ou 'sair'): ");
                String palpite = scanner.nextLine();
                saida.println(palpite);

                String resposta = entrada.readLine();
                if (resposta == null) {
                    System.out.println("Servidor encerrou a conexão.");
                    break;
                }

                System.out.println("Servidor: " + resposta);

                if (resposta.startsWith("ACERTOU") || "sair".equalsIgnoreCase(palpite.trim())) {
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Erro no cliente TCP: " + e.getMessage());
        }
    }
}
