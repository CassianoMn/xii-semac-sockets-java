# Demonstrações de threads

Abra esta pasta no terminal. Compile uma vez com:

```shell
javac -encoding UTF-8 ExemploThreadBasica.java ExemploRunnableSleepJoin.java
```

## Ordem de execução

Execute `java ExemploThreadBasica` duas vezes. Cada tarefa imprime cinco passos. A ordem de cada tarefa é preservada e a intercalação de A e B pode variar. Antes de executar, pergunte o que mudaria se as duas tarefas fossem chamadas com `run()` no lugar de `start()`.

## Espera pelo término

Execute `java ExemploRunnableSleepJoin`. A demo simula downloads de `slides.pdf` e `codigo.zip`. As duas threads terminam em algum momento e o main só imprime `Todos os downloads foram finalizados!` depois dos dois `join()`. Abra o fonte para identificar quem chama `join()` e quem é aguardado.
