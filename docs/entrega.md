# Entrega — Catálogo Independente de Cursos

## 1. URL do repositório GitHub

https://github.com/allanlopes9/atividade-catalogo-cursos

## 2. Hash do commit final da implementação

2e3d040

## 3. Nome do banco de dados utilizado

catalogocursos_test

## 4. Saída resumida de ./mvnw clean test

- Testes executados: 8
- Falhas: 0
- Erros: 0
- Testes ignorados: 0
- Resultado: BUILD SUCCESS

## 5. Perguntas sobre o relacionamento

### Qual entidade é proprietária da relação?

A entidade `Curso` é a proprietária da relação, pois possui a anotação `@ManyToOne` e define o relacionamento com `Instrutor` por meio de `@JoinColumn`.

### Em qual tabela está a chave estrangeira?

A chave estrangeira está na tabela `curso`, na coluna `instrutor_id`, que referencia o identificador (`id`) da tabela `instrutor`.

### Por que as entidades não foram retornadas diretamente pela API?

Porque utilizamos DTOs para separar as entidades do banco de dados das respostas da API. Isso evita recursão no JSON, controla quais informações são expostas e facilita a manutenção do sistema.
