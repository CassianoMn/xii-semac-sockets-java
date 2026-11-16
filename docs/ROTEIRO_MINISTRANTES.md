# Roteiro dos Ministrantes — Minicurso Programação com Sockets (XII SEMAC)

Este roteiro guia os **5 ministrantes** durante as 4 horas de curso (2h Teoria + 2h Prática), garantindo ritmo constante e suporte rápido aos alunos usando **VS Code ou Terminal**.

---

## 1. Preparação Antes da Aula (15–20 min antes)

- Abrir [apresentacao-sockets-java.html](file:///c:/Users/Cassiano%20Menezes/Desktop/XII%20Semac%20-%20Programa%C3%A7%C3%A3o%20Com%20Sockets/slides/apresentacao-sockets-java.html) no navegador em tela cheia (`F`).
- Abrir o VS Code (ou dois terminais lado a lado) com fonte grande (`Ctrl` + `+`) para projeção.
- Verificar o IP da máquina do projetor na rede local (`ipconfig` no Windows / `ip a` no Linux) para usar na **Prática 3**.
- Liberar as portas `9876/UDP`, `5000/TCP` e `6000/TCP` no Firewall do Windows da máquina servidora (se forem testar entre notebooks diferentes).

---

## 2. Parte Teórica (Máximo 2 horas)

### Bloco 1 — Threads em Java (45 min)
1. **Slides 1 a 6**:
   - Apresentar a Agenda (Slide 2).
   - Explicar por que concorrência importa sem aprofundar em detalhes de escalonador do SO.
   - Mostrar diferença entre `start()` e `run()` e por que `Runnable` / lambda é preferível.
2. **Demos ao vivo (`exemplos-teoria/threads/`)**:
   ```powershell
   cd "exemplos-teoria\threads"
   javac ExemploThreadBasica.java ExemploRunnableSleepJoin.java
   java ExemploThreadBasica
   java ExemploRunnableSleepJoin
   ```
   - Chamar atenção: no `ExemploThreadBasica`, a ordem de `A` e `B` varia! No `ExemploRunnableSleepJoin`, o `join()` segura a `main`.

### Bloco 2 — Sockets UDP e TCP (65 min)
1. **Slides 7 a 11**:
   - Analogia Prédio (IP) + Apartamento (Porta).
   - Comparativo UDP vs TCP (sem entrar em detalhes pesados de cabeçalho IP/TCP).
   - Fluxo `DatagramSocket` / `DatagramPacket` (UDP) e `ServerSocket` / `Socket` (TCP).
2. **Demos ao vivo (`exemplos-teoria/sockets/`)**:
   - Abrir **2 terminais** lado a lado (Terminal 1 = Servidor, Terminal 2 = Cliente):
   ```powershell
   cd "exemplos-teoria\sockets"
   javac *.java
   # Terminal 1:
   java ExemploUDPSimplesServer
   # Terminal 2:
   java ExemploUDPSimplesClient
   ```
   - Repetir com `ExemploTCPSimplesServer` e `ExemploTCPSimplesClient`.
   - Fechar a teoria com o **Slide 11**: mostrar que `accept()` e `readLine()` bloqueiam a execução — daí a necessidade de **Threads** na Prática 3.

### Intervalo & Conferência de Ambiente (10 min)
- Pedir para os alunos testarem `javac -version` e `java -version` no terminal do VS Code ou prompt.
- Os 4 ministrantes de apoio circulam pela sala para destravar quem não tiver JDK no `PATH`.

---

## 3. Parte Prática (2 horas)

> **Dinâmica em sala**: 1 ministrante coda no projetor explicando linha a linha; os outros 4 circulam pelos corredores ajudando alunos com erros de sintaxe, imports ou execução.

### Prática 1 — Eco UDP (25 min)
- Pasta: `praticas/pratica1-udp-eco/`
- Ordem de escrita no projetor:
  1. `UdpEcoServer.java` (porta `9876`)
  2. `UdpEcoClient.java`
- Compilar e rodar em dois terminais:
  ```powershell
  cd "praticas\pratica1-udp-eco"
  javac *.java
  # Terminal 1: java UdpEcoServer
  # Terminal 2: java UdpEcoClient
  ```

### Prática 2 — Jogo de Adivinhação TCP (35 min)
- Pasta: `praticas/pratica2-tcp-adivinhacao/`
- Ordem de escrita no projetor:
  1. `JogoAdivinhacaoServer.java` (porta `5000`)
  2. `JogoAdivinhacaoClient.java`
- Destaque didático: mostrar que se abrir **2 clientes ao mesmo tempo**, o segundo fica esperando o primeiro terminar porque ainda **não há Threads** no servidor!

### Prática 3 — Chat Multi-cliente TCP + Threads (60 min)
- Pasta: `praticas/pratica3-chat-multicliente/`
- Ordem de escrita no projetor:
  1. `ChatServer.java` (porta `6000` + `CopyOnWriteArrayList` + `broadcast`)
  2. `ClientHandler.java` (`implements Runnable`)
  3. `ChatClient.java` (com `Thread` receptora + leitura do teclado)
- **Momento alto do curso (últimos 15 min)**:
  - Subir `ChatServer` no notebook do projetor.
  - Passar o IP local do projetor no quadro (ex.: `192.168.1.50`).
  - Os alunos rodam `java ChatClient 192.168.1.50` de seus próprios notebooks e conversam todos juntos no chat!

---

## 4. Erros Comuns em Sala e Como Resolver Rápido

| Erro | Causa Provável | Solução Rápida |
| :--- | :--- | :--- |
| `BindException: Address already in use` | Servidor anterior ainda está rodando em outro terminal | Fechar o terminal antigo (`Ctrl+C`) ou mudar a porta |
| Cliente TCP trava esperando resposta | Servidor usou `print()` em vez de `println()`, faltando `\n` para o `readLine()` | Trocar para `out.println(...)` e garantir `autoFlush = true` no `PrintWriter` |
| `ConnectException: Connection refused` | Servidor não foi iniciado antes do cliente, ou porta errada | Iniciar o `*Server` primeiro e conferir a porta |
| Alunos não conectam no Chat pelo IP da rede | Firewall do Windows bloqueando conexões de entrada no Java | Permitir `java.exe` em rede privada no Firewall do Windows ou usar `localhost` localmente |
| Acentos quebrados no terminal do Windows | Terminal Windows usando CP850/CP1252 | Códigos já usam `StandardCharsets.UTF_8`; no terminal usar `chcp 65001` se necessário |
