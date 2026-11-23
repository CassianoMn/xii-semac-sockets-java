# Guia do participante

Nos laboratórios, você vai trocar datagramas com um servidor UDP, enviar palpites por uma conexão TCP e conectar três terminais a um chat. Pode trabalhar em dupla e alternar quem opera o cliente e quem acompanha o servidor.

## Preparação

1. Obtenha a pasta do curso pelo ZIP distribuído em sala ou faça o download do repositório no GitHub. Extraia tudo antes de abrir o terminal.
2. Use um **JDK 17 ou 21** (no computador do laboratório ou no seu notebook). No terminal, execute `java -version` e `javac -version`. As duas ferramentas precisam estar acessíveis e devem vir da mesma instalação do JDK (versões anteriores ao Java 11, como JDK 8, não compilam os exemplos).
3. Abra no terminal a raiz do projeto. Se o caminho tiver espaços, use aspas, como em `cd "Caminho da pasta do curso"`. Os comandos desta documentação funcionam em PowerShell e Bash.
4. **Dica para Windows (CMD/PowerShell):** antes de rodar os clientes, execute `chcp 65001` no terminal se quiser evitar acentos corrompidos ao digitar palavras como `olá` (ou teste sem acento, como `ola`).
5. Faça o teste abaixo a partir da raiz. A saída deve mostrar a thread principal e passos das tarefas A e B. O VS Code é uma opção de editor. Toda a execução cabe no terminal.

```shell
cd exemplos-teoria/threads
javac -encoding UTF-8 ExemploThreadBasica.java
java ExemploThreadBasica
```

## Como trabalhar em cada laboratório

1. Abra dois terminais na mesma pasta `inicio/` da prática. No chat, abra mais dois para os clientes adicionais. Use o mesmo diretório de trabalho para compilar e executar.
2. Compile com o comando do laboratório. Inicie o servidor no terminal A e aguarde a mensagem de escuta. Abra o cliente no terminal B.
3. Trabalhe nos pontos `TODO` em ordem. Após editar um arquivo, encerre os processos afetados, compile de novo e repita o checkpoint.
4. Compare a saída com a prevista no roteiro. Se precisar retomar uma etapa, copie apenas o trecho correspondente da solução. Para rodar a solução inteira, suba um nível a partir de `inicio/` e execute os mesmos comandos naquela pasta.
5. Encerre clientes com o comando indicado e termine o servidor com `Ctrl+C` antes de abrir a próxima prática.

| Etapa | Porta | Material |
| --- | --- | --- |
| Eco UDP | 9876/UDP | [Instruções e testes](../praticas/pratica1-udp-eco/README.md) |
| Adivinhação | 5000/TCP | [Instruções e testes](../praticas/pratica2-tcp-adivinhacao/README.md) |
| Chat | 6000/TCP | [Instruções e testes](../praticas/pratica3-chat-multicliente/README.md) |

## Diagnóstico rápido

| O que você vê | O que conferir |
| --- | --- |
| `javac` não encontrado | Confira a instalação do JDK e abra um terminal novo após atualizar o PATH. |
| Classe principal não encontrada | Confira a pasta atual, o nome da classe e se a compilação gerou o `.class`. Execute, por exemplo, `java ChatClient`. |
| Porta ocupada | Encerre o servidor anterior com `Ctrl+C`. A demo UDP usa a mesma porta do laboratório de eco. |
| Cliente TCP recebe conexão recusada | Inicie o servidor, confira a mensagem de escuta e confira host e porta. |
| Cliente TCP ou UDP aguarda | No UDP, procure o timeout após a alteração da prática. No TCP, verifique quem deve enviar primeiro, o terminador de linha e o flush. No jogo, o segundo cliente só recebe as boas-vindas após a sessão anterior. |
| Acentos incorretos | Compile com `-encoding UTF-8`. Use um terminal configurado para entrada e saída UTF-8. Se necessário, use `chcp 65001` no console do Windows e reabra a aplicação Java. |
| Chat funciona localmente, mas o colega não conecta | `localhost` aponta para sua própria máquina. Teste pelo IP da máquina servidora apenas na rede preparada pela equipe. Volte ao modo local se o caminho estiver indisponível. |

Ao final, saiba explicar de onde o servidor UDP tirou o destino da resposta, o que delimita a mensagem TCP e onde o chat inicia um fluxo para atender cada cliente. Revise com os [slides em PDF](../slides/programacao-com-sockets-em-java.pdf).
