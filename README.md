# -ControleEstoque-20240373-
Controle de Estoque com Módulo de Vendas

# Como executar o projeto
1. Pré-requisitos:

Java 17
Maven
MySQL (XAMPP ou Workbench)
Postman, ThunderClient ou Insomnia para testar os endpoints

# Configuração do Banco de Dados:
Crie o banco:

CREATE DATABASE controle_estoque;

No arquivo application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/controle_estoque?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Rodando a Aplicação

No terminal:

mvn spring-boot:run


Ou no IntelliJ/Eclipse, execute a classe:

ControleEstoqueApplication.java

# Como testar a API
1. Criar Cliente

POST

/api/clientes


Body:

{
  "nome": "João da Silva",
  "email": "joao@email.com"
}

2. Consultar produto antes da venda

GET

/api/produtos/{id}

3. Registrar uma Venda (estoque suficiente)

POST

/api/vendas


Body:

{
  "clienteId": 1,
  "itens": [
{
"produtoId": 1,
"quantidade": 2
}
]
}


Resultado esperado:

Estoque do produto é reduzido

Venda salva

4. Tentar registrar Venda com estoque insuficiente

POST

/api/vendas


Body:

{
  "clienteId": 1,
  "itens": [
{
"produtoId": 1,
"quantidade": 9999
}
]
}


Resultado esperado:

Erro HTTP 400

Estoque não deve ser alterado (rollback)

5. Consultar Vendas e Clientes

GET

/api/clientes


GET

/api/vendas

# Entidades implementadas

Cliente (1 → N Venda)

Venda

ItemVenda (N ↔ M Produto + quantidade + preço unitário)

Produto (estoque atualizado na venda)

# Lógica de Baixa de Estoque

Antes de registrar a venda, o sistema valida se existe estoque para todos os itens.
Se qualquer produto tiver estoque insuficiente a venda inteira é cancelada.
Se tudo estiver ok o estoque é reduzido item por item.
Transação marcada com @Transactional para garantir rollback.
