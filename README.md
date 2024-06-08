# Teste Infuse #

# Sobre o projeto
Criar uma solução java em formato de API que atenda aos seguintes requisitos para a recepção de pedidos dos clientes

Utilizei o Flyway para criar as tabelas e os inserts dos clientes (com id: 1 a 10), será criado automaticamente na primeira execução do sistema.

O script da tabela está localizado em resources/db.migration

# Tecnologias utilizadas
## Back end
- Java 17
- Spring Boot 3.1.5
- Spring JPA / Hibernate
- Lombok
- MapStruct
- Maven
- Flyway
- OpenApi
- Banco de Dados MySQL

# Como executar o projeto
## Back end
Pré-requisitos: Java 17 e MySql Server

```bash
# clonar repositório
git clone https://github.com/etoshio/teste-infuse

# criar o schema no banco de dados - Mysql
infuse

# abrir o projeto com o IDE (eclipse ou intelliJ) e abrir o application.yaml 
alterar a propriedade url, username e password do banco de dados mysql

# executar o comando
mvn clean install

# rodar aplicação
mvn spring-boot:run

# abrindo o openapi da aplicação no navegador (Chrome/FireFox)
http://localhost:8080/swagger-ui/index.html

# json para inserir dados
[
  {
    "numeroControle": 1235,
    "dataCadastro": "2024-06-08",
    "nomeProduto": "teste",
    "valor": 10,
    "quantidade": 10,
    "clienteId": 1
  }
]
```

