import java.io.BufferedReader;
import java.io.IOException;
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
    private static final int LIMITE = 100;
    private static final String ERRO_PALPITE = "ERRO: Digite um número inteiro entre 1 e " + LIMITE + ".";

    public static void main(String[] args) {
        int numeroFixo = 0;
        if (args.length > 0) {
            try {
                numeroFixo = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                // Zero já representa um valor inválido quando há um argumento.
            }
            if (args.length != 1 || numeroFixo < 1 || numeroFixo > LIMITE) {
                System.err.println("Uso: java JogoAdivinhacaoServer [número de 1 a " + LIMITE + "]");
                return;
            }
        }

        Random random = new Random();

        try (ServerSocket serverSocket = new ServerSocket(PORTA)) {
            System.out.println("=== Servidor TCP Jogo de Adivinhação na porta " + PORTA + " ===");
            while (true) {
                System.out.println("Aguardando próximo jogador...");

                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader entrada = new BufferedReader(
                             new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
                     PrintWriter saida = new PrintWriter(clientSocket.getOutputStream(), true, StandardCharsets.UTF_8)) {

                    int numeroSecreto = numeroFixo == 0 ? random.nextInt(LIMITE) + 1 : numeroFixo;
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
                            saida.println(ERRO_PALPITE);
                            continue;
                        }
                        if (palpite < 1 || palpite > LIMITE) {
                            saida.println(ERRO_PALPITE);
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
                } catch (IOException e) {
                    System.err.println("Conexão com jogador encerrada: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao iniciar servidor TCP: " + e.getMessage());
        }
    }
}
