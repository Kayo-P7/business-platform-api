# TelegramBot API

API backend de uma plataforma de ofertas integrada ao Telegram.

O projeto tem como objetivo disponibilizar produtos e ofertas em diferentes canais, permitindo que os usuários consultem produtos através de uma API REST, aplicação web e futuramente por meio de um bot do Telegram.

## Objetivo

O projeto está sendo desenvolvido como um projeto de estudo e prática de desenvolvimento backend utilizando Java e Spring Boot.

A aplicação busca integrar:

- API REST
- PostgreSQL
- Autenticação e autorização
- Bot do Telegram
- Aplicação Web
- Integração com produtos e ofertas da Amazon

A ideia é centralizar os produtos e ofertas em uma API, permitindo que diferentes clientes consumam essas informações.

## Tecnologias

- Java
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA
- Hibernate
- PostgreSQL
- Flyway
- Docker
- Maven
- REST API
- Telegram Bot API

## Banco de Dados

O projeto utiliza PostgreSQL como banco de dados relacional.

O mapeamento das entidades é realizado utilizando:

- Spring Data JPA
- Hibernate
- Jakarta Persistence

As alterações estruturais do banco são controladas utilizando **Flyway**, permitindo versionar as mudanças do schema através de migrations.

Exemplo:

```text
src/
└── main/
    └── resources/
        └── db/
            └── migration/
                ├── V1__create_tables.sql
                └── V2__alter_products.sql
```

Dessa forma, as alterações no banco podem ser reproduzidas de maneira controlada entre diferentes ambientes.

## Segurança

A API utiliza Spring Security e JWT para autenticação e autorização.

Estão sendo trabalhados conceitos como:

- `UserDetails`
- `UserDetailsService`
- `AuthenticationManager`
- `UsernamePasswordAuthenticationToken`
- JWT
- `SecurityContext`
- `SecurityContextHolder`
- `OncePerRequestFilter`
- BCrypt
- Roles e Authorities
- Autorização baseada em roles
- Bearer Token

### Roles

Atualmente existem diferentes níveis de acesso:

- `CUSTOMER` — acesso aos recursos destinados aos clientes.
- `ADMIN` — gerenciamento dos recursos administrativos da plataforma.

## Funcionalidades

### Autenticação

- Registro de usuário
- Login
- Geração de JWT
- Validação do JWT
- Autenticação utilizando Bearer Token
- Autorização baseada em roles

### Produtos

- Cadastro de produtos
- Consulta de produtos
- Busca de produtos
- Persistência utilizando JPA/Hibernate
- Controle de estoque
- Informações de preço
- Informações de categoria e marca
- Metadados dos produtos
- Controle de acesso aos recursos

### Persistência

O projeto utiliza entidades JPA para representar os dados da aplicação.

O modelo de produtos possui informações como:

- Título
- Descrição
- Preço
- Estoque
- Categoria
- Marca
- SKU
- Código de barras
- QR Code
- Metadados do produto

As alterações do banco são versionadas através do Flyway.

## Arquitetura

O projeto utiliza uma arquitetura baseada em camadas, separando as responsabilidades da aplicação.

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
JPA / Hibernate
    ↓
PostgreSQL
```

A estrutura também utiliza:

- Controllers
- Services
- Repositories
- DTOs
- Entities
- Security
- Exceptions
- Configurations

## API

A aplicação disponibiliza endpoints REST para gerenciamento dos recursos.

A comunicação utiliza HTTP e os dados são transmitidos principalmente no formato JSON.

A autenticação dos endpoints protegidos utiliza:

```http
Authorization: Bearer <JWT>
```

## Docker

O PostgreSQL pode ser executado através do Docker, facilitando a configuração do ambiente de desenvolvimento.

A utilização do Docker também permite manter o banco de dados isolado do ambiente local.

## Integrações

### Telegram

O projeto possui integração com a Telegram Bot API.

O objetivo é permitir que usuários consultem produtos e ofertas diretamente através do Telegram.

### Amazon

Está sendo desenvolvida uma integração para obtenção de produtos e ofertas da Amazon.

Os dados obtidos poderão posteriormente ser disponibilizados através da API e do bot do Telegram.

## Em desenvolvimento

As próximas etapas do projeto incluem:

- Integração com Amazon
- Bot do Telegram
- Frontend
- Sistema de ofertas e promoções
- Integração entre API, frontend e Telegram
- Melhorias na autenticação
- Refresh Token
- Testes automatizados
- Documentação da API
- Melhorias na arquitetura

## Estrutura do projeto

A estrutura segue a organização por responsabilidades:

```text
src/
├── main/
│   ├── java/
│   │   └── ...
│   └── resources/
│       ├── db/
│       │   └── migration/
│       └── application.yml
└── test/
```

## Status

🚧 **Em desenvolvimento**

O projeto está sendo desenvolvido gradualmente como forma de aprofundar conhecimentos em desenvolvimento backend, Java, Spring Boot, JPA, Hibernate, PostgreSQL, segurança e integração entre diferentes serviços.