# Prática 3 · Chat TCP com threads

**53 minutos · Slides 32–35 · Porta 6000/TCP**

O servidor aceita conexões repetidamente e inicia um `ClientHandler` por cliente. Cada handler registra a saída do seu cliente e pode enviar mensagens para todas as saídas registradas. No cliente, uma thread lê mensagens da rede enquanto a principal lê o teclado.

## 1. Abra a base

Abra quatro terminais em [inicio/](inicio/). Compile em A:

```shell
javac -encoding UTF-8 ChatServer.java ClientHandler.java ChatClient.java
```

Em A, execute `java ChatServer`. Em B, C e D, execute `java ChatClient`. A base chama `run()` diretamente e atende uma sessão por vez. Ela também deixa o corpo do broadcast e a remoção do cliente para o exercício.

## 2. Complete os TODOs

| Passo | Arquivo | Mudança e resultado observável |
| --- | --- | --- |
| 1 | `ChatServer.java` | Troque `threadCliente.run()` por `threadCliente.start()`. Três clientes recebem o pedido de apelido sem esperar que o primeiro saia. |
| 2 | `ChatServer.java` | Em `broadcast()`, use `writer.println(mensagem)` em cada saída cadastrada. Todos os clientes restantes recebem os avisos e as mensagens. |
| 3 | `ClientHandler.java` | Em `finally`, remova `out` da lista antes do aviso de saída. O mesmo encerramento atende `/sair`, EOF e erros de leitura. |

O servidor usa `CopyOnWriteArrayList` para que um broadcast possa percorrer um retrato da lista mesmo quando outro handler cadastra ou remove um cliente. O protocolo usa a primeira linha para o apelido, linhas seguintes para a conversa e `/sair` para fechar a sessão. A thread receptora já está pronta no código de partida.

## 3. Verifique o chat em localhost

1. Recompile e abra o servidor, depois três clientes. Entre na ordem Ana, Bia e Caio. Aguarde o aviso de entrada de cada um.
2. Digite `olá` como Ana. Bia e Caio devem receber `Ana: olá`. Faça Caio responder.
3. Em Bia, digite `/sair`. Ana e Caio recebem `[SERVIDOR] Bia saiu do chat.` e continuam conversando.
4. Inicie Bia novamente e teste outra mensagem. Depois feche um cliente com `Ctrl+C` e observe o aviso de saída.
5. Como teste final de recuperação, pare o servidor. Nos clientes o aviso de encerramento orienta a pressionar Enter para sair se o terminal estiver esperando teclado.

O teste entre computadores é opcional e vem depois da validação local. Na rede preparada pela equipe, execute `java ChatClient IP_DO_SERVIDOR`, substituindo o argumento pelo endereço fornecido. O servidor fica na máquina indicada pela equipe. Se os computadores não se alcançarem, execute todos os processos no mesmo computador com os comandos locais.

## 4. Desafios extras (opcional)

Concluiu o checkpoint em `localhost` antes do tempo? Escolha um ou mais dos desafios simples abaixo e teste recompilando:

1. **Horário nas mensagens (`[HH:mm]`)** — No `ClientHandler.java`, importe `java.time.LocalTime` e `java.time.format.DateTimeFormatter` e formate a mensagem enviada como `"[14:32] Ana: olá"`:
   ```java
   String hora = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
   String formatada = "[" + hora + "] " + nomeUsuario + ": " + mensagem;
   ```
2. **Comando `/online`** — No laço de leitura do `ClientHandler.java`, antes de chamar `broadcast()`, verifique se o usuário digitou `/online`. Se sim, responda **apenas para ele** usando `out.println("[SERVIDOR] Conectados agora: " + clientes.size());` e use `continue;`.
3. **Evitar eco para quem enviou** — No `ChatServer.java`, crie uma sobrecarga `public static void broadcast(String mensagem, PrintWriter ignorar)` que pula o remetente com `if (writer == ignorar) continue;`. Use essa versão para as mensagens de conversa e mantenha `broadcast(String)` para avisos do servidor.

## Retomada e encerramento

Compare [o laço e o broadcast](ChatServer.java) e [o bloco de limpeza](ClientHandler.java). A solução completa está nesta pasta e usa os mesmos comandos. Termine os clientes com `/sair` e o servidor com `Ctrl+C`.
