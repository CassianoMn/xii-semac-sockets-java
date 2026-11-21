# Prática 2 · Adivinhação TCP

**30 minutos · Slides 30–31 · Porta 5000/TCP**

O servidor atende uma sessão por vez. Cada palpite e cada resposta ocupam uma linha UTF-8. A conexão termina quando o jogador acerta, digita `sair` ou fecha o cliente.

## 1. Execute a base e complete o protocolo

Abra A e B em [inicio/](inicio/) e compile em A:

```shell
javac -encoding UTF-8 JogoAdivinhacaoServer.java JogoAdivinhacaoClient.java
```

Em A, execute `java JogoAdivinhacaoServer 42`. Em B, execute `java JogoAdivinhacaoClient`. Confira a mensagem de boas-vindas. O argumento fixa o segredo para que toda a turma obtenha a mesma saída. Sem o argumento, o servidor sorteia o valor a cada sessão.

Complete os TODOs em `JogoAdivinhacaoServer.java` e recompile depois de encerrar os processos.

| Entrada | Resposta esperada para segredo 42 |
| --- | --- |
| `abc`, `0` ou `101` | `ERRO: Digite um número inteiro entre 1 e 100.` |
| `20` | `MAIOR` |
| `80` | `MENOR` |
| `42` | `ACERTOU em 3 tentativa(s)!` se a sequência foi a desta tabela. A sessão é encerrada. |
| `sair` | `Jogo encerrado. Até a próxima!` e a sessão termina. |

A base já converte a linha em inteiro e lida com texto inválido. Adicione a validação de faixa antes do incremento de tentativas. Faça a comparação com o segredo e interrompa o laço em caso de acerto. Preserve o `println()` com `autoFlush` para que o cliente consiga ler cada resposta.

## 2. Observe a espera do segundo jogador

Reabra B. Em outro terminal na mesma pasta, execute também `java JogoAdivinhacaoClient`. A segunda conexão pode abrir, mas as boas-vindas só aparecem depois que o primeiro jogador digita `sair`, acerta ou desconecta. Identifique no servidor em que momento a execução volta a `accept()`.

## Retomada e encerramento

Compare o TODO de validação e as respostas com [a solução do servidor](JogoAdivinhacaoServer.java). Para rodar a solução, abra os terminais nesta pasta e repita os comandos. Feche os jogadores com `sair` e o servidor com `Ctrl+C`.
