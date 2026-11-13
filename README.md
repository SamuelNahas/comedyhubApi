<h1 align="center">😂 ComedyHub API</h1>

<p align="center">
  <b>Backend REST da ComedyHub</b>, uma rede social para publicar e interagir com conteúdo de humor.<br>
  Este repositório contém o protótipo da API em Java e Spring Boot.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-23-ED8B00?logo=openjdk&logoColor=white" alt="Java 23">
  <img src="https://img.shields.io/badge/Spring_Boot-3.3-6DB33F?logo=springboot&logoColor=white" alt="Spring Boot 3.3">
  <img src="https://img.shields.io/badge/Spring_Security-JWT-6DB33F?logo=springsecurity&logoColor=white" alt="Spring Security + JWT">
  <img src="https://img.shields.io/badge/PostgreSQL-4169E1?logo=postgresql&logoColor=white" alt="PostgreSQL">
  <img src="https://img.shields.io/badge/Swagger-OpenAPI-85EA2D?logo=swagger&logoColor=black" alt="Swagger / OpenAPI">
  <img src="https://img.shields.io/badge/Maven-C71A36?logo=apachemaven&logoColor=white" alt="Maven">
</p>

<p align="center">
  <a href="https://github.com/lucaserm/my-docs-comedyhub"><b>📚 Documentação técnica da plataforma →</b></a>
</p>

## ✨ Destaques

- **Autenticação stateless com JWT**: registro e login emitem tokens assinados (Spring Security OAuth2 Resource Server + Nimbus JOSE).
- **Senhas com BCrypt** e rotas protegidas por padrão — só cadastro, login e documentação são públicos.
- **Arquitetura em camadas**: `controller → service → repository`, com DTOs e mapper separando a API das entidades JPA.
- **Persistência com Spring Data JPA** sobre PostgreSQL.
- **Documentação interativa** com Swagger UI (SpringDoc OpenAPI).

A plataforma completa foi projetada como um ecossistema multi-cliente (web em React, mobile em React Native, Redis, WebSockets, notificações e mídia); a arquitetura está na [documentação técnica](https://github.com/lucaserm/my-docs-comedyhub).

## Endpoints

| Método | Rota | Acesso | Descrição |
|---|---|---|---|
| `POST` | `/api/auth/register` | público | Cadastra um usuário |
| `POST` | `/api/auth/login` | público | Autentica e devolve um JWT |
| `POST` | `/api/users` | autenticado | Cria um usuário |
| `GET` | `/api/users` | autenticado | Lista os usuários |
| `GET` | `/api/users/{id}` | autenticado | Busca um usuário por id |

Swagger UI: `http://localhost:9091/swagger-ui.html`

## Estrutura

```
prot/src/main/java/com/comedyhub/prot/
├── config/       # Spring Security, JWT e Swagger
├── controller/   # AuthController e UserController
├── dto/          # requests, responses e mapper
├── model/        # entidades JPA
├── repository/   # Spring Data JPA
└── service/      # regras de negócio
```

## Como rodar

Pré-requisitos: JDK 23, Maven e PostgreSQL com um banco `comedyhub`
(credenciais padrão em `prot/src/main/resources/application.properties`).

```bash
cd prot
./mvnw spring-boot:run
```

A API sobe em `http://localhost:9091`.
