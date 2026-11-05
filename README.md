# Catálogo de Cursos

**Atividade prática — Programação com Java e Spring Boot**

## Sistemas de Informação

### Integrantes

- **Allan Lopes Albino**
- **Heytor Eduardo Modolo Tonicioli**

---

## Objetivo do projeto

Desenvolver uma aplicação Spring Boot independente para representar, persistir e disponibilizar por meio de uma API REST a relação um-para-muitos (1:N) entre Instrutor e Curso.

Um instrutor poderá ministrar vários cursos, enquanto cada curso deverá possuir exatamente um instrutor responsável.

## Tecnologias utilizadas

- Java 21
- Spring Boot
- Maven
- Spring Web
- Spring Data JPA
- PostgreSQL
- Liquibase
- Bean Validation
- Spring Boot Test

## Funcionalidades previstas

- Cadastro de instrutores.
- Cadastro de cursos vinculados a instrutores existentes.
- Consulta de curso por ID.
- Listagem dos cursos de um instrutor.
- Validação dos dados de entrada.
- Tratamento centralizado de erros.

## Relacionamento principal

**Instrutor (1) → Curso (N)**

O projeto utiliza uma relação bidirecional entre as entidades Instrutor e Curso, com a chave estrangeira `instrutor_id` na tabela `curso`.

---

*Projeto desenvolvido como atividade prática da disciplina de Programação com Java e Spring Boot.*