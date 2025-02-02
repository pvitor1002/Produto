Feature: Sistema de inserção, remoção, busca e update de produtos
 	O sistema consiste de 4 funcionalidades básicas: inserção, remoção,
 	busca e update. Para a inserção e update é necessário um ID válido, um nome e um valor.
 	Para a remoção e busca é necessário um ID válido.

	Background: Já existem produtos cadastrados
		Given que os produtos estao no db
		|   nome  |  valor  |   id   |
		|  Shampoo|  10.00  |    1   |
		|   Carne |  20.00  |    2   |
		|  Alface |  5.00   |    3   |

  Scenario Outline: Insercao de produto
    Given Usuario deseja inserir um produto "<nome>" de id <id> e valor <valor> novo
    When Usuario faz requisicao
    Then Status "<status>"
  
  Examples: 
      | nome   | id | valor |  status          |
      | Carne  |  4 | 20.50 |Sucesso ao criar  |
      | Feijão |  1 | 10.00 |Falha ao criar    |
      | Yakuti | -1 | 7.00  |Falha ao criar    |     

  Scenario Outline: Remocao de produto
    Given Usuario deseja remover o produto <id>
    When Usuario tenta deletar
    Then Resultado "<status>" 

    Examples: 
      | id | status                |
      | 1  | Sucesso ao deletar    |
      | -1 | Falha ao deletar      |
      | 5  | Falha ao deletar      |
      
  Scenario Outline: busca de produto
    Given Usuario deseja buscar o produto <id>
    When Usuario tenta buscar
    Then Produto "<status>"

    Examples: 
      | id |        status        										    |
      | 1  | {"nome":"Shampoo", "valor":10.0, "id":1}     |           
      | -1 | Falha ao encontrar    										    |
      | 5  | Falha ao encontrar    										    |
      
  Scenario Outline: Atualizar produto
    Given Usuario deseja atualizar o produto <id> com nome "<nome>" e valor <valor>
    When Usuario tenta atualizar
    Then Atualizacao "<status>"

    Examples: 
      | id | nome  |  valor |        status         |
      | 1  |Alface |  6.00  | Sucesso ao atualizar  |
      | 1  |Alface | -6.00  | Falha ao atualizar    |           
      | -1 |Alface |  4.00  | Falha ao atualizar    |
      | 5  |Alface |  6.00  | Falha ao atualizar    |
