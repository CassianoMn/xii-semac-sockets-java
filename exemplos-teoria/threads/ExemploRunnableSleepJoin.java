/**
 * Exemplo 2 (Teoria - Threads):
 * Demonstra o uso de join() para aguardar o término de outras threads
 * antes de continuar na thread principal.
 */
public class ExemploRunnableSleepJoin {

    static class TarefaDownload implements Runnable {
        private final String arquivo;
        private final int tempoMs;

        public TarefaDownload(String arquivo, int tempoMs) {
            this.arquivo = arquivo;
            this.tempoMs = tempoMs;
        }

        @Override
        public void run() {
            System.out.println("Baixando " + arquivo + " na " + Thread.currentThread().getName() + "...");
            try {
                Thread.sleep(tempoMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Concluído: " + arquivo);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new TarefaDownload("slides.pdf", 1200), "Thread-Download-1");
        Thread t2 = new Thread(new TarefaDownload("codigo.zip", 800), "Thread-Download-2");

        t1.start();
        t2.start();

        // A thread main espera t1 e t2 terminarem antes de exibir a mensagem final
        t1.join();
        t2.join();

        System.out.println("Todos os downloads foram finalizados!");
    }
}
