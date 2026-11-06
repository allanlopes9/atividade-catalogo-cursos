# Respostas da atividade — Catálogo Independente de Cursos

## 1. Qual entidade é proprietária da relação?

A entidade `Curso` é a proprietária da relação, pois possui a anotação `@ManyToOne` e define o relacionamento com `Instrutor` por meio de `@JoinColumn`.

## 2. Em qual tabela está a chave estrangeira?

A chave estrangeira está na tabela `curso`, na coluna `instrutor_id`, que referencia o identificador (`id`) da tabela `instrutor`.

## 3. Por que as entidades não foram retornadas diretamente pela API?

Porque utilizamos DTOs para separar as entidades do banco de dados das respostas da API. Isso evita recursão no JSON, controla quais informações são expostas e facilita a manutenção do sistema.
