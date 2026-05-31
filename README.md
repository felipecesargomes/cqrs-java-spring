# CQRS com Spring Boot

Este repositório é um estudo prático de CQRS (Command Query Responsibility Segregation) usando Java e Spring Boot.

O projeto está dividido em dois módulos:

- `command`: responsável por escrita (create/update/delete), com persistência relacional no MySQL.
- `query`: estrutura separada para leitura, com configuração própria.

Hoje, o fluxo de escrita já está funcional e expõe endpoints para criar produtos e reviews.

## Arquitetura resumida

No módulo `command`, o fluxo segue este caminho:

`Controller -> CommandBus -> CommandHandler -> Repository -> Banco`

Em vez de centralizar regras em um service tradicional, cada caso de uso de escrita é tratado por um handler específico.
O `CommandBus` faz o roteamento do comando para o handler correto.

## Tecnologias

- Java 17
- Spring Boot 3.4.5
- Spring Web
- Spring Data JPA
- MySQL
- Maven (wrapper `mvnw`)
- Docker Compose (MySQL, Kafka, Zookeeper, Kafdrop e Mongo)

## Requisitos

- JDK 17 instalado e ativo no terminal/IDE
- Docker e Docker Compose

## Como subir o ambiente

No diretório raiz do projeto (`cqrs`):

```bash
docker compose up -d
```

No `docker-compose.yml`, o MySQL está publicado em `3307` para evitar conflito local com `3306`.

## Configuração de banco (módulo command)

Arquivo: `command/src/main/resources/application.yml`

- URL: `jdbc:mysql://localhost:3307/command`
- Usuário: `root`
- Senha: `example`
- `ddl-auto`: `create`

Com `ddl-auto: create`, o Hibernate recria as tabelas a cada inicialização do app.
Se quiser manter dados entre reinícios, troque para `update`.

## Executando a aplicação command

```bash
cd /c/Users/felip/Downloads/cqrs/cqrs
./mvnw -pl command spring-boot:run
```

Por padrão, a API sobe em `http://localhost:8080`.

## Endpoints de escrita

### Criar produto

- Método: `POST`
- URL: `/api/v1/products`

Exemplo com `curl`:

```bash
curl -X POST "http://localhost:8080/api/v1/products" \
  -H "Content-Type: application/json" \
  -d '{
    "imageUrl": "https://exemplo.com/produto.png",
    "name": "Mouse Gamer",
    "description": "Mouse RGB 16000 DPI",
    "value": 199.90
  }'
```

### Criar review

- Método: `POST`
- URL: `/api/v1/reviews`

Exemplo com `curl`:

```bash
curl -X POST "http://localhost:8080/api/v1/reviews" \
  -H "Content-Type: application/json" \
  -d '{
    "userName": "Felipe",
    "rating": 5,
    "productId": 1
  }'
```

Observação: o `productId` precisa existir na tabela `products`.

## Validando no MySQL

Entrar no banco do container:

```bash
docker exec -it cqrs-mysql-1 mysql -uroot -pexample
```

Depois, no prompt MySQL:

```sql
USE command;
SHOW TABLES;
SELECT * FROM products;
SELECT * FROM reviews;
```

## Testes

Para rodar os testes do módulo `command`:

```bash
cd /c/Users/felip/Downloads/cqrs/cqrs
./mvnw -pl command -am test
```

## Problemas comuns

### Erro de compilação com `TypeTag :: UNKNOWN` ou `ExceptionInInitializerError`

Normalmente é ambiente em Java 11 com projeto exigindo Java 17.
Confirme no terminal:

```bash
java -version
```

Se não aparecer Java 17, ajuste o `JAVA_HOME` e o SDK da IDE.

### `No database selected` no MySQL

Você conectou no servidor, mas não escolheu o schema.
Execute:

```sql
USE command;
```

### Erro de JSON no `curl`

Garanta que o JSON esteja entre aspas simples e as chaves estejam balanceadas.

## Estrutura do repositório

```text
cqrs/
  command/
  query/
  docker-compose.yml
  pom.xml
```

## Próximos passos sugeridos

- Adicionar endpoints de consulta no módulo `query`
- Integrar eventos (Kafka) entre escrita e leitura
- Versionar migrations com Flyway ou Liquibase
- Criar testes de integração para os handlers
