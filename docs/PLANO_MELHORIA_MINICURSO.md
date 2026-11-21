# Plano de produção do minicurso Programação com Sockets em Java

**XII SEMAC · Universidade Federal de Sergipe · 4 horas · 5 ministrantes**

O resultado da aula será um chat de terminal em que três clientes trocam mensagens e continuam conversando quando um deles sai. O eco UDP apresenta o envio de datagramas. O jogo de adivinhação TCP introduz um protocolo de texto e torna visível a espera do segundo jogador. O chat retoma esse exemplo para mostrar como o servidor passa a atender vários clientes.

## 1. Diagnóstico do repositório

Revisão do commit `220a027` da branch `master`.

| Material | O que foi encontrado | Decisão de produção |
| --- | --- | --- |
| [Apresentação HTML](../slides/apresentacao-sockets-java.html) | Protótipo de 12 telas. A agenda indica 65 min de sockets e 10 min de intervalo, enquanto o roteiro atualizado prevê 55 e 20. O conteúdo traz simplificações como “UDP mais rápido” e apresenta estados de escalonamento como se fossem os estados de `Thread` em Java. | Criar a apresentação oficial do zero, com identidade visual própria, diagramas autorais e notas de fala. Retirar o HTML da trilha de apresentação no README e no roteiro quando a versão final estiver disponível. |
| [README](../README.md), [roteiro](ROTEIRO_MINISTRANTES.md) e [divisão de tarefas](DIVISAO_TAREFAS_EQUIPE.md) | Servem de índice e de rascunho para a equipe. Os dois documentos internos ligam para um caminho absoluto do computador de Cassiano. Faltam etapas com saídas esperadas, material do participante e pontos de recuperação durante a aula. | Reduzir o README à entrada do curso. Dar ao estudante um guia executável e à equipe um roteiro com falas, comandos, checkpoints, transições e contingências. |
| [Exemplos teóricos](../exemplos-teoria/) | Já existem demos de threads, UDP e TCP. A demo de UDP e a Prática 1 usam `9876/UDP`. | Reaproveitar as demos, registrar exatamente o que observar e encerrar a demo UDP antes de abrir a Prática 1. |
| [Práticas](../praticas/) | Os três projetos trazem o código completo. O eco aguarda indefinidamente se o servidor não responder. O jogo rejeita texto inválido, mas trata `0` como palpite válido. O chat já tem broadcast, thread receptora e comando `/sair`. | Fornecer uma base com trechos a completar e uma solução por prática. Priorizar recuperação no UDP, validação de faixa no jogo e um fluxo de entrada, broadcast e saída reproduzível no chat. |
| [Aula 3 — Programação Paralela/Concorrente](../Aula%203%20-%20programacao%20paralela.ppt) e [Programação de sockets](../programacao%20soket.pdf) | A aula de José Aélio de Oliveira Júnior oferece exemplos de fluxo concorrente, `Runnable` e `join()`. O material de redes oferece sequências cliente-servidor UDP e TCP e exemplos de eco. A aula de threads usa a API Java SE 7 como referência. | Manter os dois arquivos como referências. Produzir explicações e diagramas próprios, atribuir as referências e conferir a terminologia e a API na documentação do JDK adotado. |

**Execução local verificada.** Os 13 arquivos Java compilaram no JDK 21.0.10 com alvo Java 17. No Windows, foram executadas as demos de threads, os ecos UDP e TCP, as três práticas e o chat com três clientes em loopback. O ensaio no computador da sala, em duas máquinas na rede do local e em um runtime JDK 17 ainda faz parte da preparação.

## 2. Público e resultados de aprendizagem

O ponto de partida proposto é saber compilar um programa Java simples e reconhecer classes, métodos, laços e exceções. Incluir no guia o comando de verificação do JDK e um teste mínimo de compilação. Usar JDK 21 no ensaio da equipe e manter Java 17 como mínimo anunciado após validar também nesse runtime. Toda a aula deve ser executável com `javac` e `java`, com o VS Code como opção.

Ao final, cada dupla deve conseguir:

1. Identificar cliente, servidor, endereço e porta, sabendo quando usar `localhost`.
2. Enviar um datagrama UDP, conferir o eco e explicar a espera quando a resposta não chega.
3. Trocar linhas de texto por TCP e explicar a função de `accept()`, da quebra de linha, do flush e do fim de fluxo.
4. Explicar por que o segundo jogador espera no servidor sequencial e apontar onde o chat usa `start()` para atender clientes concorrentemente.
5. Demonstrar o chat com três clientes e observar que os outros dois seguem ativos após um `/sair`.

## 3. Roteiro de quatro horas

Os horários são relativos ao início da aula. Os primeiros 120 min incluem explicação, demos, checagem inicial e intervalo. Os 120 seguintes incluem os laboratórios e o fechamento.

| Tempo | Min | Atividade | Evidência ou transição |
| --- | ---: | --- | --- |
| 00:00–00:10 | 10 | Abertura, demonstração curta do chat e checagem de JDK e terminais | A turma vê o resultado final e a equipe identifica quem precisa de apoio. |
| 00:10–00:35 | 25 | Concorrência em Java. `Runnable`, `run()`, `start()`, `join()` e demo A/B | Prever o que muda ao chamar `run()` diretamente. Conferir a ordem variável das tarefas. |
| 00:35–00:50 | 15 | Cliente-servidor, endereço, porta, loopback e duas janelas de terminal | Cada dupla consegue apontar qual processo escuta e qual inicia a troca. |
| 00:50–01:10 | 20 | UDP. Endereçar, enviar e receber datagramas. Demo de eco | Identificar de onde o servidor extrai o endereço e a porta da resposta. |
| 01:10–01:40 | 30 | TCP. Conexão, socket aceito, fluxo de bytes, protocolo por linhas e demo | Explicar por que uma leitura aguarda quando ainda falta a quebra de linha. |
| 01:40–01:50 | 10 | Espera por conexão, espera por dados e atendimento concorrente | Prever o comportamento de dois clientes em um servidor sequencial. |
| 01:50–02:00 | 10 | Intervalo e retomada do ambiente | Fechar as demos, liberar `9876/UDP` e deixar as bases dos laboratórios acessíveis. |
| 02:00–02:22 | 22 | Prática 1. Eco UDP | Ver o eco em loopback e recuperar o cliente após uma resposta ausente. |
| 02:22–02:52 | 30 | Prática 2. Adivinhação TCP | Jogar, testar uma entrada inválida e observar a espera de outro cliente. |
| 02:52–03:45 | 53 | Prática 3. Chat TCP com threads | Validar três clientes, broadcast, saída e continuidade da conversa. |
| 03:45–03:55 | 10 | Demonstração das duplas e revisão das decisões de implementação | Usar as evidências dos laboratórios para relacionar protocolo e concorrência. |
| 03:55–04:00 | 5 | Margem e encerramento | Distribuir o link e o pacote de estudo com soluções e referências. |

Usar bases compiláveis para que a turma complete apenas os pontos de aprendizagem. Se uma dupla perder um checkpoint, o apoio entrega a versão pronta daquele passo. Em atraso geral, encurtar a demonstração final em rede ou mostrar uma das demos teóricas com a saída já preparada. Preservar a execução local das três práticas.

## 4. Apresentação oficial

Entregar uma apresentação editável em **PPTX**, proporção **16:9**, e uma cópia **PDF para projeção offline**. Manter as notas de fala no editável e os comandos completos no roteiro. O storyboard tem **35 slides principais**, incluindo as telas de apoio aos laboratórios.

| Slides | Conteúdo a produzir |
| --- | --- |
| 1–3 | Capa com XII SEMAC, UFS e nomes confirmados da equipe. Mostrar o chat com três terminais, os objetivos e a agenda. |
| 4–9 | Sequencial e concorrente em duas linhas do tempo. Papéis de `Runnable` e `Thread`. Comparação entre `run()` e `start()`. Demo A/B e previsão sobre `join()`. |
| 10–13 | Dois processos na mesma máquina. Endereço, porta, `localhost` e porta de origem. Quadro TCP/UDP com critérios de escolha. |
| 14–18 | Sequência UDP com origem e destino, `DatagramSocket`, `DatagramPacket`, tamanho recebido e demo com eco e espera de resposta. |
| 19–24 | Sequência TCP com conexão e `accept()`. Diferença entre o socket de escuta e o socket aceito. Fluxo de bytes, linha UTF-8, flush, EOF e demo. |
| 25–27 | Segundo cliente aguardando atendimento, thread de aceitação e um `ClientHandler` por cliente. Diagrama do broadcast. |
| 28–29 | Eco UDP. Instruções do objetivo, terminais A/B, checkpoint e saída esperada. |
| 30–31 | Adivinhação TCP. Tabela mínima de mensagens e experimento com dois jogadores. |
| 32–34 | Chat. Diagrama com três clientes, passos de construção e critério de saída e reconexão. |
| 35 | Síntese das decisões técnicas, material para levar e referências principais. |

**Projeção.** Usar tipografia de alto contraste, corpo a partir de 24 pt como ponto de partida e trechos curtos de código editável. Nos diagramas, identificar explicitamente quem envia, quem recebe e quando o processo espera. Também diferenciar cliente e servidor por texto e posição. Usar saídas de terminal reais em trechos curtos e validar a leitura do fundo da sala. O deck deve funcionar offline, com fontes disponíveis na máquina e sem depender de links para as demos.

**Notas.** Cada tela de explicação ou demo deve ter uma nota com o tempo previsto, o que apontar, uma pergunta ou observação para a turma e a próxima transição. Nas telas de laboratório, manter o checkpoint projetado durante a execução.

**Revisão técnica.** Dizer que TCP oferece um fluxo de bytes confiável e ordenado e que a aplicação precisa delimitar suas mensagens. Dizer que UDP preserva datagramas, com entrega e ordem não garantidas. Se os estados da JVM entrarem como material extra, usar `Thread.State` e distinguir `BLOCKED` por monitor de espera por I/O no sentido didático. Conferir o conteúdo na [API Java de redes](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/net/package-summary.html), em [Thread.State](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/Thread.State.html), na [RFC 768](https://www.rfc-editor.org/info/rfc768/) e na [RFC 9293](https://www.rfc-editor.org/info/rfc9293/).

## 5. Laboratórios e exemplos

Adotar a mesma estrutura em cada pasta. O `README.md` inclui objetivo, comandos por terminal, mudanças a fazer, entradas de teste, saídas esperadas e encerramento. Criar `inicio/` com a base compilável e `solucao/` a partir das classes existentes. Compilar cada versão em sua própria pasta. Para cada etapa, publicar o trecho mínimo necessário para recuperar quem se atrasou.

| Prática | O que a equipe prepara | O que a dupla implementa e demonstra |
| --- | --- | --- |
| **1 · Eco UDP (22 min)** | 5 min de execução inicial, 10 de mudança, 7 de teste. Base com I/O preparada. Explicar o buffer e medir o tamanho em bytes. Padronizar a transformação em maiúsculas e a resposta com prefixo `ECO:`. | Completar a resposta para o endereço e a porta do remetente e tratar timeout de leitura no cliente. Testar `olá` → `ECO: OLÁ`, depois interromper o servidor e conferir que o cliente volta a aceitar entrada. |
| **2 · Jogo TCP (30 min)** | 7 min de protocolo, 15 de implementação, 8 de teste. Base com `ServerSocket`, streams UTF-8 e protocolo por linhas. Preparar roteiro de demonstração com valor secreto conhecido pela pessoa que conduz. | Completar a validação e as respostas `MAIOR`, `MENOR` e `ACERTOU`. Ver erro para texto e números fora de 1–100, acerto com fechamento da sessão e atendimento do segundo jogador após a saída do primeiro. |
| **3 · Chat (53 min)** | 12 min para explorar a base, 18 para completar a lógica, 15 para testes de troca e saída, 8 para demonstração em rede autorizada ou repetição local. Fornecer leitura do teclado, thread receptora no cliente e estrutura de cadastro de conexões. | Ligar `accept()` a um `ClientHandler` com `start()`, completar o broadcast e remover o cliente que saiu. Conferir apelido, mensagens de três clientes, aviso de saída, continuidade dos outros dois e reconexão. |

Alinhar os nomes de classes e os protocolos entre exemplos, slides, guias e soluções. Documentar as portas atuais (`9876/UDP`, `6789/TCP` na demo, `5000/TCP` e `6000/TCP`) e como trocar uma porta ocupada dos dois lados. Para a versão de sala, tratar escrita com quebra de linha e flush, fim de fluxo, encerramento de sockets e diagnóstico de conexão recusada. Usar timeout de conexão no chat se a etapa em rede for mantida. O protocolo do chat deve explicitar primeira linha como apelido, mensagens subsequentes e `/sair`.

## 6. Materiais e divisão entre os cinco ministrantes

Os papéis seguem a divisão existente. Confirmar os nomes com a equipe e usar um revisor diferente do autor para cada entrega.

| Responsável | Entrega | Revisão | Em sala |
| --- | --- | --- | --- |
| Integrante 1 | Modelo visual, montagem de `slides/programacao-com-sockets-em-java.pptx`, exportação PDF, slides e notas de concorrência | 2 revisa conteúdo e 5 testa a projeção. | Abertura e bloco de threads. |
| Integrante 2 | Slides e notas UDP/TCP, diagramas cliente-servidor e guias das demos em `exemplos-teoria/` | 1 e 3 conferem termos, comandos e passagem para as práticas. | Redes, UDP, TCP e transição para conexão bloqueante. |
| Integrante 3 | Bases, soluções, guias e testes manuais das práticas de eco e jogo | 2 executa os dois guias a partir de cópia limpa. | Conduz as práticas 1 e 2. |
| Integrante 4 | Base, solução, protocolo e guia do chat. Ensaio de loopback e teste entre duas máquinas | 3 replica o chat e verifica a recuperação após saída de um cliente. | Conduz a prática 3 e decide sobre a demo em rede com o integrante 5. |
| Integrante 5 | README revisado, guia em `docs/GUIA_PARTICIPANTE.md`, revisão do roteiro, checklist da equipe e pacote offline | 1 e 4 percorrem as instruções na ordem da aula. | Triagem de ambiente, cronômetro e entrega de checkpoints. |

- **Guia do participante.** Preparação prévia, como obter o ZIP, teste do JDK, comandos PowerShell e Bash usando caminhos consistentes, como abrir os terminais, diferenças entre loopback e IP de rede, solução dos erros mais comuns e links para laboratórios e estudo posterior. Deixar os comandos e exemplos copiáveis.
- **Roteiro dos ministrantes.** Usar a agenda deste plano como fonte para as demais agendas. Incluir, por bloco, responsável, página do deck, arquivo a abrir, comando, reação esperada e ponto de corte. Durante a projeção, os outros quatro dividem suporte, controle de tempo e preparação da próxima sessão.
- **Pacote de sala.** PPTX, PDF, materiais do participante, bases, soluções e o código das demos da mesma revisão do repositório. Testar a extração em pasta com espaços e preparar uma cópia no computador da aula e outra em pendrive. Deixar o JDK verificado antes da sessão.

## 7. Ordem de produção e aceite

A documentação anterior cita **19/11** como prazo interno. A XII SEMAC ocorre de **23 a 28 de novembro de 2026**. Trabalhar com uma janela curta e confirmar com a organização a data e a infraestrutura do minicurso. [Edital da XII SEMAC](https://proex.ufs.br/pagina/33845)

1. **21/11, início.** Confirmar responsáveis, horários, perfil da turma e sala. Fixar a agenda, os comandos, os protocolos e os checkpoints. Os responsáveis por slides começam pelo storyboard e pela identidade visual enquanto os responsáveis pelos laboratórios preparam os trechos que serão projetados.
2. **21/11, primeira versão integrada.** Juntar slides, código, notas e guias. Cada revisor executa o material de outra frente e devolve apenas correções que afetam clareza, execução ou tempo. Fechar primeiro apresentação, guias e soluções. Imagens adicionais, desafio extra e gravações podem entrar depois que o núcleo passar no ensaio.
3. **22/11 ou antes da sessão.** Cronometrar o percurso em ordem, com papéis e pausas reais. Uma pessoa deve seguir o guia como participante em uma cópia limpa. Registrar tempo efetivo, saídas e ajustes necessários. Corrigir e gerar um único pacote identificado pela revisão final.
4. **30 minutos antes da aula.** Abrir PPTX/PDF no projetor e conferir legibilidade. Compilar o chat, checar o JDK, testar três clientes no mesmo computador e decidir o modo da demonstração final. Usar a rede do local somente após confirmar que duas máquinas se comunicam e que o acesso está autorizado. Se houver bloqueio ou isolamento entre máquinas, seguir com um servidor e três clientes em `localhost`.

### Checklist para considerar o material pronto

- [ ] A agenda soma 240 minutos, com responsáveis e transições no roteiro e no deck.
- [ ] O PPTX e o PDF abrem offline e são legíveis na projeção. Agenda, exemplos e nomes de classes batem com os guias.
- [ ] Um colega replica as três práticas a partir do ZIP em uma pasta com espaços. A equipe verifica JDK 17 e 21, os sistemas de sala e a entrada e saída com acentos.
- [ ] O eco recupera a execução após timeout. O jogo rejeita texto e números fora de faixa, fecha a sessão e libera o próximo jogador. O chat troca mensagens entre três clientes e continua após saída normal ou fechamento de um cliente.
- [ ] A equipe ensaia a interrupção de um servidor e a porta ocupada. Os guias registram o que a turma observa, como retomar e quais processos fechar ao fim de cada bloco.
- [ ] Cada ministrante executa os trechos que vai apoiar. Slides, soluções, comandos previstos e cópias offline correspondem à mesma revisão.
