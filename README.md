# Programação com Sockets em Java

Minicurso de quatro horas da XII Semana Acadêmico-Cultural da Universidade Federal de Sergipe. A turma usa sockets UDP e TCP para construir um eco, um jogo de adivinhação e um chat que atende vários clientes com threads.

## Para acompanhar a aula

1. Leia o [guia do participante](docs/GUIA_PARTICIPANTE.md) e confira `java -version` e `javac -version`. Use JDK 17 ou 21.
2. Abra a [apresentação em PDF](slides/programacao-com-sockets-em-java.pdf) e veja o que será demonstrado nos [exemplos de threads](exemplos-teoria/threads/README.md) e de [UDP e TCP](exemplos-teoria/sockets/README.md).
3. Acompanhe os laboratórios abaixo. A pasta `inicio/` de cada prática tem os pontos a completar. Os arquivos Java ao lado das instruções são a solução de referência.

| Laboratório | Resultado |
| --- | --- |
| [1. Eco UDP](praticas/pratica1-udp-eco/README.md) | Enviar um datagrama, conferir a resposta em maiúsculas e retomar após um timeout. |
| [2. Adivinhação TCP](praticas/pratica2-tcp-adivinhacao/README.md) | Trocar linhas entre cliente e servidor e observar o atendimento sequencial. |
| [3. Chat TCP com threads](praticas/pratica3-chat-multicliente/README.md) | Trocar mensagens com três clientes e observar o chat continuar após a saída de um deles. |

## Para ministrar

- [PowerPoint editável com notas de fala](slides/programacao-com-sockets-em-java.pptx)
- [Roteiro de 240 minutos e contingências](docs/ROTEIRO_MINISTRANTES.md)
- [Papéis da equipe e checklist de ensaio](docs/DIVISAO_TAREFAS_EQUIPE.md)

## Referências

A equipe consultou materiais de aula de programação concorrente e de redes para o curso. A especificação e a API podem ser consultadas em [Java SE 17](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/net/package-summary.html), [RFC 768 · UDP](https://www.rfc-editor.org/info/rfc768/) e [RFC 9293 · TCP](https://www.rfc-editor.org/info/rfc9293/).
