# Fisctrack Core

## 🔥 Sobre
Este projeto é uma API REST construída com **Java 21** e **Quarkus** para gerenciar notas fiscais. A API permite o gerenciamento completo de:

- **Produtos** - Cadastro, consulta, atualização e remoção de produtos
- **Fornecedores** - Gerenciamento completo de fornecedores com endereços
- **Notas Fiscais** - Emissão e controle de notas fiscais com itens

### 🌐 Documentação
- **Swagger UI**: [https://wiriswernek.github.io/fisctrack-core/](https://wiriswernek.github.io/fisctrack-core/)
- **Repositório**: [https://github.com/WirisWernek/fisctrack-core](https://github.com/WirisWernek/fisctrack-core)

### � Endpoints da API
A API expõe os seguintes endpoints principais:

- `GET/POST/PUT/DELETE /api/produto` - Gerenciamento de produtos
- `GET/POST/PUT/DELETE /api/fornecedor` - Gerenciamento de fornecedores
- `GET/POST/PUT/DELETE /api/nota-fiscal` - Gerenciamento de notas fiscais

## �🔨 Como executar esta aplicação?

### Pré-requisitos
- **Java** na versão `21` ou superior
- **Maven** na versão `3.8.7` ou superior
- **Git** para clonar o repositório
- **PostgreSQL** rodando na porta `5432` com um banco de dados nomeado `fisctrack_db`

### 📋 Passo a passo

#### 1. Clonar o repositório
```bash
git clone https://github.com/WirisWernek/fisctrack-core.git
cd fisctrack-core
```

#### 2. Configurar o banco de dados
**Opção 1: Docker (Recomendado)**
```bash
docker run --name postgresql_fisctrack \
  -e POSTGRES_PASSWORD=postgres \
  -e POSTGRES_DB=fisctrack_db \
  -p 5432:5432 \
  -d postgres:17
```

**Opção 2: PostgreSQL local**
- Certifique-se de que o PostgreSQL está executando na porta `5432`
- Crie um banco de dados chamado `fisctrack_db`
- Ajuste as credenciais no arquivo `src/main/resources/application.properties` se necessário

#### 3. Executar a aplicação

**Modo de desenvolvimento (recomendado):**
```bash
./mvnw quarkus:dev
```
*ou*
```bash
mvn quarkus:dev
```

A aplicação estará disponível em:
- **API**: `http://localhost:8080/api`
- **Swagger UI**: `http://localhost:8080/swagger-ui`

### 📦 Outras opções de execução

**Empacotar a aplicação:**
```bash
./mvnw package
```

**Gerar executável nativo:**
```bash
./mvnw package -Dnative
```

### ⚙️ Configuração

#### Configuração do banco de dados
O arquivo `src/main/resources/application.properties` contém as configurações padrão:

```properties
quarkus.datasource.db-kind=postgresql
quarkus.datasource.username=postgres
quarkus.datasource.password=postgres
quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/fisctrack_db
```

Se você estiver usando as configurações padrão com Docker, não é necessário alterar nada.

#### Variáveis de ambiente (opcional)
Você pode sobrescrever as configurações usando variáveis de ambiente:

```bash
export QUARKUS_DATASOURCE_USERNAME=seu_usuario
export QUARKUS_DATASOURCE_PASSWORD=sua_senha
export QUARKUS_DATASOURCE_JDBC_URL=jdbc:postgresql://localhost:5432/seu_banco
```

### 🧪 Testando a API

Após executar a aplicação, você pode testar os endpoints:

**Exemplo - Listar produtos:**
```bash
curl -X GET "http://localhost:8080/api/produto" \
  -H "accept: application/json"
```

**Exemplo - Criar um produto:**
```bash
curl -X POST "http://localhost:8080/api/produto" \
  -H "accept: application/json" \
  -H "Content-Type: application/json" \
  -d '{
    "descricao": "Produto Teste",
    "preco": 29.90
  }'
```

### 🔗 Links úteis

- **Swagger UI Local**: `http://localhost:8080/swagger-ui`
- **OpenAPI Spec Local**: `http://localhost:8080/swagger`
- **Health Check**: `http://localhost:8080/q/health`

## 📦 Tecnologias utilizadas

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/apachemaven-C71A36.svg?style=for-the-badge&logo=apachemaven&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Quarkus](https://img.shields.io/badge/quarkus-%234794EB.svg?style=for-the-badge&logo=quarkus&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)

### Principais dependências:
- **Java 21** - Linguagem de programação principal
- **Quarkus 3.19.4** - Framework reativo para Java
- **PostgreSQL** - Banco de dados relacional
- **Hibernate ORM with Panache** - ORM para persistência de dados
- **SmallRye OpenAPI** - Geração de documentação da API
- **Jakarta Validation** - Validação de dados
- **RESTEasy** - Implementação JAX-RS para APIs REST
- **Lombok** - Redução de código boilerplate

## 🏗️ Arquitetura do projeto

O projeto segue uma arquitetura em camadas bem definida:

```
src/main/java/io/github/wiriswernek/fisctrack/
├── core/                 # Classes base e exceções
│   ├── baseclass/       # Classes abstratas base
│   ├── exceptions/      # Exceções customizadas
│   └── mapper/          # Mapeadores entre DTOs e entidades
├── domain/              # Camada de domínio
│   ├── model/
│   │   ├── dto/        # Data Transfer Objects
│   │   ├── entity/     # Entidades JPA
│   │   ├── enums/      # Enumerações
│   │   └── repository/ # Interfaces de repositório
│   └── service/        # Serviços de negócio
└── rest/               # Camada de apresentação
    └── controller/     # Controllers REST
```

## 🤝 Contribuição

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📝 Licença

Este projeto está sob a licença Apache 2.0. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 👨‍💻 Autor

**Wiris Wernek**
- GitHub: [@WirisWernek](https://github.com/WirisWernek)
- Website: [wiriswernek.tech](https://wiriswernek.tech)
- Email: [wiriswernek@gmail.com](mailto:wiriswernek@gmail.com)