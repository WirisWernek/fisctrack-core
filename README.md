# Fisctrack Core

## 🔥 Sobre
Este projeto é uma API construída com Java e Quarkus para gerenciar notas fiscais, através dela é possível gerenciar produtos, fornecedores e as próprias notas

## 🔨 Como executar esta aplicação?

### Pré requisitos
- Java na versão `21`
- Maven na versão `3.8.7`
- Um servidor ou instância do PostgreSQL rodando na porta `5432` com um banco de dados vazio nomeado de `fisctrack_db`
	- É recomendado o uso de um container para o banco de dados local, ele pode ser criado a partir do seguinte comando `docker run --name postgresql_fisctrack -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=fisctrack_db -p 5432:5432 -d postgres:17`

### Tutorial 
Para rodar a aplicação voce deve:
- Primeiro clonar este repositório
- Navegar para a pasta `fisctrack-core`
- Ajustar as variáveis no arquivo `application.properties`
	- Se estiver utilizando o banco de dados em um container criado pelo tópico anterior não é necessário nenhum ajuste.
- Executar o comando `mvn quarkus:dev` ou `./mvnw quarkus:dev` para rodar a aplicação

Caso deseje empacotar a aplicação e gerar um executável, você deve:
- Dentro da pasta executar o comando `mvn package` ou `./mvnw package`

Para gerar um executável nativo deve:
- Dentro da pasta executar o comando `mvn package -Dnative` ou `./mvnw package -Dnative`

## 📦 Tecnologias usadas
- ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
- ![Maven](https://img.shields.io/badge/apachemaven-C71A36.svg?style=for-the-badge&logo=apachemaven&logoColor=white)
- ![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
- ![Quarkus](https://img.shields.io/badge/quarkus-%234794EB.svg?style=for-the-badge&logo=quarkus&logoColor=white)
- ![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)