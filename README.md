# Telegram Bot S

## 📖 Sobre o projeto

O **Telegram Bot S** é um projeto de estudos focado no desenvolvimento Backend com **Java** e **Spring Boot**.

O projeto começou como uma integração com a **Telegram Bot API**, mas evoluiu para uma API REST completa, servindo como laboratório para aprender conceitos utilizados em aplicações profissionais.

O objetivo é construir uma aplicação escalável, organizada e de fácil manutenção, aplicando boas práticas de arquitetura, persistência de dados, tratamento de erros, validações e integrações externas.

---

# 🚀 Tecnologias utilizadas

* Java
* Spring Boot
* Spring Web
* Spring Data JPA
* PostgreSQL
* Hibernate
* Maven
* Jackson
* Docker
* REST API
* Bean Validation
* DTO Pattern
* UUID
* Global Exception Handler
* Pageable (Paginação)

---

# 📦 Funcionalidades implementadas

## Produtos

* ✅ Cadastro de produtos
* ✅ Busca por ID
* ✅ Listagem de produtos
* ✅ Atualização de produtos
* ✅ Remoção de produtos
* ✅ Remoção de todos os produtos

## Consultas

* ✅ Busca por nome
* ✅ Busca por faixa de preço
* ✅ Busca por estoque
* ✅ Busca por estoque baixo
* ✅ Busca por produtos ativos

## Banco de dados

* ✅ PostgreSQL
* ✅ Spring Data JPA
* ✅ Persistência com Hibernate

## Validação

* ✅ Bean Validation (`@Valid`)
* ✅ Validação de campos obrigatórios
* ✅ Validação de preços
* ✅ Validação de quantidade

## Tratamento de exceções

* ✅ `@ControllerAdvice`
* ✅ `GlobalExceptionHandler`
* ✅ `ProductNotFoundException`
* ✅ `ProductAlreadyExistsException`
* ✅ `InsufficientStockException`
* ✅ `StandardError` para respostas padronizadas

## Paginação

* ✅ Paginação utilizando `Pageable`
* ✅ Resposta utilizando `Page<ProductResponse>`

---

# 📁 Estrutura do projeto

```text
src
 ├── controller
 ├── dto
 ├── exception
 ├── model
 ├── repository
 └── service
```

---

# 📌 Próximos passos

## API

* 🔲 Ordenação (`Sort`)
* 🔲 Filtros avançados
* 🔲 Specifications (consultas dinâmicas)

## Documentação

* 🔲 Swagger / OpenAPI

## Testes

* 🔲 JUnit 5
* 🔲 Mockito
* 🔲 Testes de integração

## Segurança

* 🔲 Spring Security
* 🔲 JWT
* 🔲 Controle de usuários e permissões

## Banco de dados

* 🔲 Flyway para migrations

## Mensageria

* 🔲 RabbitMQ
* 🔲 Processamento assíncrono

## Infraestrutura

* 🔲 Docker Compose
* 🔲 Deploy

## Integrações

* 🔲 Integração completa com a Telegram Bot API
* 🔲 Processamento de mensagens
* 🔲 Respostas automáticas
* 🔲 Comandos personalizados

## Futuro do projeto

* 🔲 Sistema de anúncios automáticos
* 🔲 Consumo de APIs externas
* 🔲 Arquitetura em microsserviços (estudo)
* 🔲 Integração com serviços em nuvem (AWS)

---

# 🎯 Objetivo

Este projeto tem como objetivo consolidar conhecimentos em desenvolvimento Backend utilizando Java e Spring Boot, aplicando tecnologias amplamente utilizadas no mercado e evoluindo gradualmente para uma aplicação completa com autenticação, mensageria, testes, infraestrutura e integração com bots do Telegram.

O desenvolvimento é incremental, buscando reproduzir cenários reais encontrados em aplicações corporativas.
