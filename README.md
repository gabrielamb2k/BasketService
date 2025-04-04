# BasketService
# Basket Service

## Descrição
O **Basket Service** é um microsserviço responsável por gerenciar o carrinho de compras de um e-commerce. Ele armazena os itens adicionados pelos usuários, permitindo operações como adição, remoção e recuperação de produtos no carrinho. O serviço utiliza **MongoDB** como banco de dados NoSQL, **Redis** para cache e **Docker** para facilitar a implantação.

## Tecnologias Utilizadas
- **Linguagem:** [Java 17]
- **Banco de Dados:** MongoDB
- **Cache:** Redis
- **Containerização:** Docker
- **Frameworks e Bibliotecas:** [Spring Boot, Lombok, OpenFeign]

## Funcionalidades
- Adicionar itens ao carrinho
- Remover itens do carrinho
- Deletar itens do carrinho
- Utilização de cache para otimizar o desempenho das requisições

## Arquitetura
O serviço segue a arquitetura de microsserviços e utiliza **MongoDB** para armazenar os dados persistentes e **Redis** para armazenar temporariamente os dados do carrinho, garantindo respostas rápidas. 
