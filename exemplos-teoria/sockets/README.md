# Demonstrações de UDP e TCP

Abra dois terminais nesta pasta, A para o servidor e B para o cliente. Compile uma vez em A:

```shell
javac -encoding UTF-8 ExemploUDPSimplesServer.java ExemploUDPSimplesClient.java ExemploTCPSimplesServer.java ExemploTCPSimplesClient.java
```

| Demo | Terminal A | Terminal B | Observe |
| --- | --- | --- | --- |
| UDP, porta 9876 | `java ExemploUDPSimplesServer` | `java ExemploUDPSimplesClient` | Em B, digite `olá`. Confira `Resposta do servidor: OLÁ`. Ache a porta de origem em A. |
| TCP, porta 6789 | `java ExemploTCPSimplesServer` | `java ExemploTCPSimplesClient` | Em B, digite `olá`. Ache `accept()` e `readLine()` no servidor e `println()` no cliente. |

Feche o servidor com `Ctrl+C` ao fim de cada demo. A prática de eco usa a mesma porta da demo UDP. Abra um cliente somente depois do seu servidor. O tratamento do tempo de espera no UDP é tema da Prática 1.
