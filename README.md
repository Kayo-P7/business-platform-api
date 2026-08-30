# TelegramBot API

API backend de uma plataforma de ofertas integrada ao Telegram.

O projeto tem como objetivo disponibilizar produtos e ofertas em diferentes canais,
permitindo que o usuário consulte as ofertas através de uma aplicação web ou de um
bot do Telegram.

## Objetivo

A aplicação está sendo desenvolvida como um projeto de estudo e prática de
desenvolvimento backend com Java e Spring Boot.

A ideia é integrar:

- Backend REST
- Aplicação Web (Frontend)
- Bot do Telegram
- Integração com produtos/ofertas da Amazon

Os produtos poderão ser consultados pelo site e também através do Telegram.

## Tecnologias

- Java
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA
- PostgreSQL
- Docker
- Maven
- REST API
- Telegram Bot API

## Segurança

A API utiliza Spring Security e JWT para autenticação e autorização.

Atualmente estão sendo implementados conceitos como:

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

### Roles

Atualmente existem diferentes níveis de acesso:

- `CUSTOMER` — acesso às ofertas disponíveis.
- `ADMIN` — gerenciamento dos recursos administrativos da plataforma.

## Funcionalidades

### Autenticação

- Login
- Registro de usuário
- Geração de JWT
- Validação do JWT
- Autenticação através de Bearer Token

### Produtos

- Consulta de produtos
- Busca por nome
- Gerenciamento administrativo dos produtos
- Controle de acesso baseado em roles

### Em desenvolvimento

- Integração com Amazon
- Bot do Telegram
- Frontend
- Sistema de ofertas e promoções
- Integração entre API, frontend e Telegram

## Arquitetura

O projeto está sendo desenvolvido utilizando uma arquitetura baseada em camadas,
separando responsabilidades entre:

- Controllers
- Services
- Repositories
- DTOs
- Models/Entities
- Security
- Exceptions

## Status

🚧 Em desenvolvimento.

O projeto continua sendo desenvolvido e novas funcionalidades serão adicionadas
gradualmente.