# Programação com Sockets em Java — XII SEMAC

Repositório oficial do minicurso **Programação com Sockets em Java**, desenvolvido para a **XII SEMAC**.  
O curso tem duração estimada de **4 horas** (2h teóricas + 2h práticas) e guia os participantes desde os fundamentos de **Threads** até a construção de um **Chat Multi-cliente em rede local com TCP + Threads**.

---

## Estrutura do Repositório

```text
.
├── slides/
│   └── apresentacao-sockets-java.html    # Apresentação HTML offline (12 slides)
├── exemplos-teoria/
│   ├── threads/                          # Demos rápidas de Thread, Runnable, sleep e join
│   └── sockets/                          # Demos rápidas de UDP e TCP
├── praticas/
│   ├── pratica1-udp-eco/                 # Prática 1: Eco/Ping com DatagramSocket (UDP)
│   ├── pratica2-tcp-adivinhacao/         # Prática 2: Jogo de Adivinhação com ServerSocket (TCP)
│   └── pratica3-chat-multicliente/       # Prática 3: Chat Multi-cliente (TCP + Threads)
├── docs/
│   ├── ROTEIRO_MINISTRANTES.md           # Cronograma e guia de troubleshooting em sala
│   └── DIVISAO_TAREFAS_EQUIPE.md         # Divisão de papéis para equipe de 5 ministrantes
├── Aula 3 - programacao paralela.ppt     # Material base de referência (Threads)
└── programacao soket.pdf                 # Material base de referência (Sockets)
```

---

## Pré-requisitos

- **Java JDK 17+** (testado com JDK 21)
- **VS Code** (com Extension Pack for Java) ou qualquer **Terminal** com `javac` e `java` no `PATH`
- Navegador web moderno (para abrir os slides em `slides/apresentacao-sockets-java.html`)

---

## Como Executar os Exemplos e Práticas

### 1. Apresentação Teórica
Abra o arquivo [`slides/apresentacao-sockets-java.html`](slides/apresentacao-sockets-java.html) diretamente no navegador.  
Use as setas `←` / `→` ou `Espaço` para navegar e `F` para tela cheia.

### 2. Prática 1 — Eco UDP
```bash
cd praticas/pratica1-udp-eco
javac *.java

# Terminal 1 (Servidor)
java UdpEcoServer

# Terminal 2 (Cliente)
java UdpEcoClient
```

### 3. Prática 2 — Jogo de Adivinhação TCP
```bash
cd praticas/pratica2-tcp-adivinhacao
javac *.java

# Terminal 1 (Servidor)
java JogoAdivinhacaoServer

# Terminal 2 (Cliente)
java JogoAdivinhacaoClient
```

### 4. Prática 3 — Chat Multi-cliente (TCP + Threads)
```bash
cd praticas/pratica3-chat-multicliente
javac *.java

# Terminal 1 (Servidor)
java ChatServer

# Terminal 2, 3, ... (Clientes)
java ChatClient
# Ou conectando via IP na rede local:
java ChatClient 192.168.X.X
```

---

## Documentação da Equipe

- [Roteiro dos Ministrantes](docs/ROTEIRO_MINISTRANTES.md)
- [Divisão de Tarefas da Equipe (5 pessoas)](docs/DIVISAO_TAREFAS_EQUIPE.md)
