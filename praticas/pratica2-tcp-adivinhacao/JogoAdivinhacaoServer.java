import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * Prática 2 - Servidor TCP: Jogo de Adivinhação (1 cliente por vez).
 * Sorteia um número entre 1 e 100 e orienta o cliente com MAIOR, MENOR ou ACERTOU.
 */
public class JogoAdivinhacaoServer {

    public static final int PORTA = 5000;

    public static void main(String[] args) {
        Random random = new Random();
        System.out.println("=== Servidor TCP Jogo de Adivinhação na porta " + PORTA + " ===");

        try (ServerSocket serverSocket = new ServerSocket(PORTA)) {
            while (true) {
                System.out.println("Aguardando próximo jogador...");

                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader entrada = new BufferedReader(
                             new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
                     PrintWriter saida = new PrintWriter(clientSocket.getOutputStream(), true, StandardCharsets.UTF_8)) {

                    int numeroSecreto = random.nextInt(100) + 1;
                    int tentativas = 0;

                    System.out.println("Jogador conectado: " + clientSocket.getInetAddress().getHostAddress()
                            + " (Número secreto: " + numeroSecreto + ")");

                    saida.println("Bem-vindo ao Jogo da Adivinhação! Tente acertar o número entre 1 e 100.");

                    String palpiteTexto;
                    while ((palpiteTexto = entrada.readLine()) != null) {
                        if ("sair".equalsIgnoreCase(palpiteTexto.trim())) {
                            saida.println("Jogo encerrado. Até a próxima!");
                            break;
                        }

                        int palpite;
                        try {
                            palpite = Integer.parseInt(palpiteTexto.trim());
                        } catch (NumberFormatException e) {
                            saida.println("ERRO: Digite um número inteiro válido entre 1 e 100.");
                            continue;
                        }

                        tentativas++;
                        if (palpite < numeroSecreto) {
                            saida.println("MAIOR");
                        } else if (palpite > numeroSecreto) {
                            saida.println("MENOR");
                        } else {
                            saida.println("ACERTOU em " + tentativas + " tentativa(s)!");
                            break;
                        }
                    }
                } catch (Exception e) {
                    System.err.println("Conexão com jogador encerrada: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao iniciar servidor TCP: " + e.getMessage());
        }
    }
}
