# LambdaForm

Este projeto é uma função Lambda desenvolvida em Java que processa dados de um formulário de contato. A função foi projetada para ser integrada com um front-end (como um formulário de contato) para receber e processar dados, como nome, email, telefone e mensagem, e armazená-los conforme necessário.

## Descrição

A função Lambda recebe dados em formato JSON enviados por um formulário de contato. Ela pode ser configurada para armazenar esses dados em um serviço da AWS, como o Amazon S3 ou um banco de dados. O projeto utiliza a biblioteca AWS SDK para Java e outras dependências, como Log4j2 para logging e Lombok para simplificação do código.

## Tecnologias Utilizadas

- **Java 17**: A linguagem usada para desenvolver a função Lambda.
- **AWS Lambda**: Para execução sem servidor.
- **AWS SDK**: Para integração com serviços da AWS (como S3).
- **Log4j2**: Para logging da aplicação.
- **Lombok**: Para simplificação do código e redução de boilerplate.
- **Jackson**: Para manipulação de JSON.

## Dependências

O projeto utiliza as seguintes dependências:

- `aws-lambda-java-core`: Para criar funções Lambda em Java.
- `aws-lambda-java-log4j2`: Para integração com o Log4j2 para logging.
- `aws-sdk-s3`: Para interagir com o Amazon S3.
- `lombok`: Para simplificação de código.
- `jackson-databind` e `jackson-datatype-jsr310`: Para manipulação de JSON.

## Como Usar

### Pré-requisitos

Antes de começar, certifique-se de que você tem os seguintes softwares instalados:

- [JDK 17 ou superior](https://adoptopenjdk.net/)
- [Maven](https://maven.apache.org/)
- [AWS CLI](https://aws.amazon.com/cli/)

### Instalação

1. Clone o repositório para sua máquina local:
```bash
git clone https://github.com/seu-usuario/LambdaForm.git
cd LambdaForm
```

2. Compile o projeto usando Maven:
```bash
mvn clean package
```

3. Isso gerará um arquivo LambdaForm-1.0-SNAPSHOT.jar no diretório target/.

## Implantação na AWS

Para implantar a função Lambda, siga as etapas abaixo:

1. Faça login na AWS Console.
2. Navegue até o serviço Lambda.
3. Crie uma nova função Lambda:
   - Selecione Author from Scratch.
   - Defina um nome para a função (por exemplo, LambdaForm).
   - Selecione o runtime Java 17.
   - Carregue o arquivo JAR gerado (LambdaForm-1.0-SNAPSHOT.jar) no campo de código da função.
   - Configure a função para ser acionada por uma API Gateway ou outro serviço da AWS conforme necessário.
4. Defina as permissões para que a Lambda acesse outros serviços (como S3, caso esteja usando).
5. Teste a função com um evento de amostra ou com a integração do formulário de contato.

## Configuração do Maven

Para configurar o projeto para gerar o JAR com as dependências, o arquivo pom.xml já está configurado para usar o maven-shade-plugin, que cria um JAR "uber" contendo todas as dependências.

Para compilar o JAR com todas as dependências, execute o seguinte comando:

```bash 
mvn clean package
```

## Logs

Os logs da execução da Lambda podem ser visualizados no Amazon CloudWatch Logs.
 
## Licença
Este projeto está licenciado sob a licença MIT. Consulte o arquivo LICENSE para mais informações.