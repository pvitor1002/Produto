package com.Produto.Cucumber.Test.StepDef;

import cucumber.api.java.en.*;

public class ProdutoStepDef {
	@Given("que os produtos estão no db")
	public void que_os_produtos_estão_no_db(io.cucumber.datatable.DataTable dataTable) {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
	    throw new cucumber.api.PendingException();
	}

	@Given("Usuario deseja inserir um produto {string} de id {int} e valor {double} novo")
	public void usuario_deseja_inserir_um_produto_de_id_e_valor_novo(String string, Integer int1, Double double1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("Usuario faz requisicao")
	public void usuario_faz_requisicao() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("Status {string}")
	public void status(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("Usuario deseja remover o produto {int}")
	public void usuario_deseja_remover_o_produto(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("Usuario tenta deletar")
	public void usuario_tenta_deletar() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("Resultado {string}")
	public void resultado(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("Usuario deseja buscar o produto {int}")
	public void usuario_deseja_buscar_o_produto(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("Usuario tenta buscar")
	public void usuario_tenta_buscar() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("Produto {string}")
	public void produto(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("Usuario deseja atualizar o produto {int} com nome {string} e valor {double}")
	public void usuario_deseja_atualizar_o_produto_com_nome_e_valor(Integer int1, String string, Double double1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("Usuario tenta atualizar")
	public void usuario_tenta_atualizar() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("Atualizacao {string}")
	public void atualizacao(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
}
