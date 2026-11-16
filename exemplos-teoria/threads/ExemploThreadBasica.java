/**
 * Exemplo 1 (Teoria - Threads):
 * Demonstra a criação de duas threads usando a interface Runnable (forma recomendada)
 * e a execução concorrente com Thread.sleep().
 */
public class ExemploThreadBasica {

    public static void main(String[] args) {
        System.out.println("Iniciando a thread principal: " + Thread.currentThread().getName());

        // Usando lambda (implementa Runnable)
        Thread tarefaA = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("[Tarefa A] Passo " + i);
                pausar(400);
            }
        });

        Thread tarefaB = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("[Tarefa B] Passo " + i);
                pausar(400);
            }
        });

        // IMPORTANTE: start() cria uma nova pilha de execução.
        // Chamar run() diretamente executaria tudo na thread main de forma sequencial!
        tarefaA.start();
        tarefaB.start();

        System.out.println("Thread main finalizou o disparo das tarefas!");
    }

    private static void pausar(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
