Feature: Sistema de inserção, remoção, busca e update de produtos
 	O sistema consiste de 4 funcionalidades básicas: inserção, remoção,
 	busca e update. Para a inserção e update é necessário um ID válido, um nome e um valor.
 	Para a remoção e busca é necessário um ID válido.

	Background: Já existem produtos cadastrados
		Given que os produtos estão no db
		|   nome    |   id   |  valor  |
		|  Shampoo  |    1   |  10.00  |
		|   Carne   |    2   |  20.00  |
		|  Alface   |    3   |  5.00   |

  Scenario Outline: Insercao de produto
    Given Usuario deseja inserir um produto "<nome>" de id <id> e valor <valor> novo
    When Usuario faz requisicao
    Then Status "<status>"
  
  Examples: 
      | nome   | id | valor |  status       |
      | Carne  |  4 | 20.50 |  Inserido     |
      | Feijão |  1 | 10.00 |ID já existente|
      | Yakuti | -1 | 7.00  |  ID invalido  |     

  Scenario Outline: Remocao de produto
    Given Usuario deseja remover o produto <id>
    When Usuario tenta deletar
    Then Resultado "<status>" 

    Examples: 
      | id | status                |
      | 1  | Removido com sucesso  |
      | -1 | ID invalido           |
      | 5  | Produto nao encontrado|
      
  Scenario Outline: busca de produto
    Given Usuario deseja buscar o produto <id>
    When Usuario tenta buscar
    Then Produto "<status>"

    Examples: 
      | id |        status         |
      | 1  | Produto encontrado    |           
      | -1 | ID invalido           |
      | 5  | Produto nao encontrado|
      
  Scenario Outline: Atualizar produto
    Given Usuario deseja atualizar o produto <id> com nome "<nome>" e valor <valor>
    When Usuario tenta atualizar
    Then Atualizacao "<status>"

    Examples: 
      | id | nome  |  valor |        status         |
      | 1  |Alface |  6.00  | Produto atualizado    |
      | 1  |Alface | -6.00  | Falha ao atualizar    |           
      | -1 |Alface |  4.00  | ID invalido           |
      | 5  |Alface |  6.00  | Produto nao encontrado|
