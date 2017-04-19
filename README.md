# invocacao-do-mal
EP - Sistemas Distribuídos. O Mal reverbera novamente os cânticos nefastos que anunciam a jubilação.

Tutorial para RMI
https://www.mkyong.com/java/java-rmi-distributed-objects-example/

Mais antigo, mais explicações e em português
http://www.linhadecodigo.com.br/artigo/2831/exemplo-pratico-do-uso-de-rmi-em-sistemas-distribuidos-servico-de-criptografia.aspx

UUID - Ids únicos
http://www.javapractices.com/topic/TopicAction.do?Id=56

--------------------------------------------------------------------------------------

Como executar (Isso só funciona com as coisas setadas na mão):

## Colocar as classes em uma pasta, compilar tudo.
javac *.java

## Começar o registro no diretorio que tiver as classes tudo
start rmiregistry

## Startar o Servidor
java -Djava.security.policy=server.policy Repository

## Startar o cliente
java -Djava.security.policy=server.policy Client

-------------------------------------------------------------------------------------

O que falta fazer:

- Remover tudo que foi hardcoded, tipo o nome do servidor.

- Fazer o meu no client

- Adaptar as funções que foram feitas no Client para tratarem as entradas e responderem aos comandos (tirar os returns e colocar prints, além do Scanner e etc)
