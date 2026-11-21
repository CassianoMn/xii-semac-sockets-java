# Prática 1 · Eco UDP

**22 minutos · Slides 28–29 · Porta 9876/UDP**

O cliente envia uma mensagem em UTF-8. O servidor lê o datagrama, identifica de onde ele veio e responde em maiúsculas com o prefixo `ECO:`. O cliente aguarda até dois segundos por resposta e pode tentar novamente.

## 1. Abra a base e complete os TODOs

Abra dois terminais em [inicio/](inicio/). Encerre o servidor da demo UDP se ele ainda estiver aberto. Compile em A:

```shell
javac -encoding UTF-8 UdpEcoServer.java UdpEcoClient.java
```

1. **Servidor.** Em `UdpEcoServer.java`, monte o datagrama de resposta usando `dadosResposta`, `ipCliente` e `portaCliente`. Envie pelo socket que recebeu o pedido.
2. **Cliente.** Em `UdpEcoClient.java`, configure `socket.setSoTimeout(TEMPO_ESPERA_MS)`. Trate `SocketTimeoutException` em torno de `receive()`. Quando esse prazo vencer, informe a espera e use `continue` para voltar ao prompt.
3. Recompile. Em A, execute `java UdpEcoServer`. Em B, execute `java UdpEcoClient`.

O limite de entrada é de 1.024 **bytes**. `getLength()` informa o tamanho recebido. A resposta reserva mais espaço porque acrescenta um prefixo e a conversão para maiúsculas pode mudar o tamanho em UTF-8.

## 2. Teste a recuperação

| Ação | Saída esperada |
| --- | --- |
| Digite `olá` em B | `Servidor -> ECO: OLÁ`. A registra o endereço e a porta do remetente. |
| Encerre A com `Ctrl+C` e digite `ping` em B | Após cerca de 2 s, aparece `Sem resposta em 2 segundos. Confira o servidor e tente novamente.` e o prompt volta. |
| Execute `java UdpEcoServer` novamente em A e reenvie `ping` em B | O mesmo cliente recebe `Servidor -> ECO: PING`. |

O timeout indica que a resposta ainda não chegou dentro da janela do cliente. Antes de segurar a aula por um problema de espera, confira se o servidor já exibiu a mensagem de escuta e se a porta está correta.

## Retomada e encerramento

Consulte [a montagem da resposta](UdpEcoServer.java) e [o tratamento de timeout](UdpEcoClient.java). Para rodar a solução, abra dois terminais nesta pasta e repita os comandos. Digite `sair` em B e use `Ctrl+C` em A.
