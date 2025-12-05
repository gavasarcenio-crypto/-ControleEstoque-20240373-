# -ControleEstoque-20240373-
Controle de Estoque com Módulo de Vendas

A API permite:
- Cadastro de clientes
- Cadastro de produtos
- Controle de estoque
- Registro de vendas
- Itens vinculados a cada venda
- Atualização automática de estoque ao vender

# Tecnologias utilizadas

Java 17+
Spring Boot 3
Spring Web
Spring JPA / Hibernate
MySQL
Lombok
Insomnia / Postman para testes

# Configuração do Banco de Dados:
Crie o banco:

CREATE DATABASE controle_estoque;

No arquivo application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/controle_estoque?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect


# Como rodar o projeto

Instalar MySQL e rodar o XAMPP
Criar o banco:
CREATE DATABASE controle_estoque;

Ajustar o application.properties

Rodar o projeto pela IDE (ou):
mvn spring-boot:run

Testar a API no Insomnia / Postman

# Como testar a API

1. Criar Cliente
POST http://localhost:8080/api/clientes
Body:
{
  "nome": "Roberto",
  "email": "TheRobertop@gmail.com"
}

2. Criar um produto
POST http://localhost:8080/api/produtos
Body:
{
  "nome": "Camiseta",
  "preco": 100.0
}

3. Criar um estoque
POST http://localhost:8080/api/estoques
Body:
{
  "quantidade": 15,
  "produtoId": 1
}

4. Consultar o estoque 
GET http://localhost:8080/api/estoques/1
Body:
{
  "id": 1,
  "quantidade": 15,
  "produtoId": 1
}

5. Registrar uma venda
POST http://localhost:8080/api/vendas
Body:
{
  "clienteId": 1,
  "itens": [
    { "produtoId": 1, "quantidade": 6 }
  ]
}

6. Consultar estoque (estoque foi atualizado)
GET http://localhost:8080/api/estoques/1
Body:
{
  "id": 1,
  "quantidade": 15,
  "produtoId": 1
}

7. Registrar venda com estoque insuficiente
POST http://localhost:8080/api/vendas
Body:
{
  "clienteId": 1,
  "itens": [
    {
      "produtoId": 1,
      "quantidade": 999
    }
  ]
}

8. Consultar estoque (estoque não diminuiu)
GET http://localhost:8080/api/estoques/1
Body:
{
  "id": 1,
  "quantidade": 15,
  "produtoId": 1
}

9. Listar produtos criados
GET http://localhost:8080/api/produtos

10. Listar Vendas realizadas
GET http://localhost:8080/api/vendas

11. Listar Clientes
GET http://localhost:8080/api/clientes

# Notas do desenvolvimento

O estoque é atualizado automaticamente quando uma venda é registrada.
As entidades usam @JsonIgnore e @JsonBackReference para evitar loops ao serializar JSON.
A API segue a estrutura:
Cliente → Venda → ItensVenda → Produto → Estoque

# Autor

Gabriel Vasconcelos Arcênio da Silva
Projeto desenvolvido como parte de estudo de Java + Spring Boot.
