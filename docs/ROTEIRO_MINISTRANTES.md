# Roteiro dos ministrantes

O percurso tem 240 minutos. Use o [PPTX com notas](../slides/programacao-com-sockets-em-java.pptx) no modo apresentador, com o [PDF](../slides/programacao-com-sockets-em-java.pdf) aberto como cópia de projeção. Os horários abaixo são relativos ao início da aula.

## Antes da turma chegar

- Abra o deck e os terminais do projetor com fonte ampliada. Extraia o ZIP em uma pasta do computador da aula. Verifique JDK e compilação com um terminal da mesma máquina.
- Deixe uma cópia compilada da solução do chat pronta para a abertura. Abra servidor e três clientes em `localhost` com Ana, Bia e Caio. Pare o servidor ao terminar a abertura.
- Leve uma cópia do pacote offline. Combine a transição entre os dois blocos de teoria, a entrega das bases das práticas e quem sinaliza o horário de corte.
- Para a demonstração entre computadores, confira com a organização se as máquinas podem se comunicar. Faça uma conexão de teste real com um segundo dispositivo. Se houver isolamento ou bloqueio, use quatro terminais locais, com um servidor e três clientes.

## Agenda e projeção

| Tempo | Slides | Responsável | O que fazer e observar |
| --- | --- | --- | --- |
| 00:00–00:10 | 1–3 | 1 | Mostrar o chat da abertura e pedir `java -version` e `javac -version`. Apoio forma duplas para destravar ambiente, enquanto o relato da demo segue. |
| 00:10–00:35 | 4–9 | 1 | Executar as [demos de threads](../exemplos-teoria/threads/README.md). Perguntar em qual thread `run()` executa quando chamado pelo main e por que a última linha vem depois dos dois `join()`. Se a turma já estiver com o terminal aberto, convide quem quiser a rodar os dois exemplos junto. |
| 00:35–00:50 | 10–13 | 2 | Identificar cliente, servidor, endereço e porta. Mostrar por que o destino é o próprio computador ao usar `localhost`. |
| 00:50–01:10 | 14–18 | 2 | Rodar a [demo UDP](../exemplos-teoria/sockets/README.md). Apontar endereço e porta de origem. Encerrar o servidor para liberar `9876/UDP`. |
| 01:10–01:40 | 19–24 | 2 | Rodar a [demo TCP](../exemplos-teoria/sockets/README.md). Mostrar `accept()`, `println()`, `readLine()`, flush e EOF. Se atrasar, use as telas com a saída prevista e preserve a prática. |
| 01:40–01:50 | 25–27 | 2 | Prever a espera do segundo jogador. Mostrar o laço de aceitação do chat e o caminho de uma mensagem até os demais clientes. |
| 01:50–02:00 | 27 | 5 | Intervalo. Fechar processos de demo e organizar a pasta da Prática 1. Quem está em suporte deixa o código compilável nas máquinas que precisarem. |
| 02:00–02:22 | 28–29 | 3 | [Eco UDP](../praticas/pratica1-udp-eco/README.md). Às 02:15, começar o teste com `olá`, servidor parado e servidor reiniciado. |
| 02:22–02:52 | 30–31 | 3 | [Adivinhação TCP](../praticas/pratica2-tcp-adivinhacao/README.md). Subir com segredo `42`. Às 02:44, abrir o segundo cliente para ver a espera e liberar com `sair` no primeiro. |
| 02:52–03:45 | 32–35 | 4 | [Chat](../praticas/pratica3-chat-multicliente/README.md). Às 03:04, guiar os três TODOs. Às 03:22, começar a validação local; quem concluir antes pode seguir os desafios extras do slide 35. Reservar os últimos 8 min para a demonstração com máquinas da sala se já tiverem passado no teste de rede. |
| 03:45–03:55 | 36 | 1 e 4 | Pedir a uma dupla que demonstre a conversa e explique a espera no jogo e o `start()` no servidor do chat. |
| 03:55–04:00 | 36 | 5 | Usar a margem para dúvidas e compartilhar o repositório ou o ZIP. |

## Quando uma etapa demorar

- **Ambiente individual.** O apoio atende na carteira ou forma dupla com um ambiente pronto. Quem conduz continua com a turma.
- **Checkpoint atrasado.** O integrante 5 oferece o trecho seguinte ou a solução daquele laboratório. A dupla recompila e demonstra o comportamento antes de mudar de etapa.
- **Servidor local indisponível.** Confira se a demo anterior terminou e se cliente e servidor usam a mesma configuração. Use a tabela de [diagnóstico do participante](GUIA_PARTICIPANTE.md#diagnóstico-rápido).
- **Rede indisponível.** Altere a demonstração para `localhost` na máquina do projetor. Abrir três clientes locais atende ao mesmo critério de concorrência e broadcast.
- **Projeção indisponível no aplicativo.** Abra a cópia em PDF. Mantenha este roteiro e as saídas de terminal à mão para retomar a demo.

## Checagem de saída

Peça a uma dupla para associar uma linha de código a cada resposta. De onde sai a porta de resposta UDP? Por que `readLine()` pode aguardar? Onde o servidor do chat inicia outra thread? Anote em quais passos a turma precisou usar a recuperação.
