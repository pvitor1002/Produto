package com.Produto.Cucumber.Test.StepDef;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.Produto.Controller.ProdutoController;
import com.Produto.Cucumber.Test.Config.Configurer;
import com.Produto.Model.Produto;
import com.Produto.Repository.ProdutoRepository;
import com.Produto.Service.ProdutoService;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import io.cucumber.datatable.DataTable;

@WebMvcTest(ProdutoController.class)
public class ProdutoStepDef {
	
	private List<Produto> produtosList = new ArrayList<>();
	
	private ProdutoService prodserv = new ProdutoService();
	
	private Produto produto;
	private ResultActions resposta;
	private int i = 0;
	
	@Autowired
    MockMvc mockMvc;
    
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	
	@Given("que os produtos estao no db")
	public void que_os_produtos_estao_no_db(List<Produto> produtos) {
		if(i == 0) {
			produtosList = produtos;
			for (Produto produto : produtosList) {
				prodserv.adicionarProduto(produto);
			}
			i++;
		}
	}

	@Given("Usuario deseja inserir um produto {string} de id {int} e valor {double} novo")
	public void usuario_deseja_inserir_um_produto_de_id_e_valor_novo(String nome, Integer id, Double valor) {
	    produto = new Produto(nome, valor, id);
	}

	@When("Usuario faz requisicao")
	public void usuario_faz_requisicao() throws Exception {
		System.err.println(mockMvc);
		resposta = mockMvc.perform(post("/produto").contentType(APPLICATION_JSON_UTF8)
	            .content(produto.toString()));
	}

	@Then("Status {string}")
	public void status(String status) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		resposta.andExpect(content().string(containsString(status)));
	}

	@Given("Usuario deseja remover o produto {int}")
	public void usuario_deseja_remover_o_produto(int id) {
	    // Write code here that turns the phrase above into concrete actions
		produto = new Produto();
	    produto.setId(id);
	}

	@When("Usuario tenta deletar")
	public void usuario_tenta_deletar() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    resposta = mockMvc.perform(delete("/produto/" + produto.getId()));
	}

	@Then("Resultado {string}")
	public void resultado(String resultado) throws Exception {
	    // Write code here that turns the phrase above into concrete actions~
		resposta.andExpect(content().string(containsString(resultado)));
	}

	@Given("Usuario deseja buscar o produto {int}")
	public void usuario_deseja_buscar_o_produto(int id) {
	    // Write code here that turns the phrase above into concrete actions
		produto = new Produto();
	    produto.setId(id);
	}

	@When("Usuario tenta buscar")
	public void usuario_tenta_buscar() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    resposta = mockMvc.perform(get("/produto/" + produto.getId()));
	}

	@Then("Produto {string}")
	public void produto(String resultado) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		resposta.andExpect(content().string(containsString(resultado)));
	}

	@Given("Usuario deseja atualizar o produto {int} com nome {string} e valor {double}")
	public void usuario_deseja_atualizar_o_produto_com_nome_e_valor(int id, String nome, Double valor) {
	    // Write code here that turns the phrase above into concrete actions
	    produto = new Produto(nome, valor, id);
	}

	@When("Usuario tenta atualizar")
	public void usuario_tenta_atualizar() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    resposta = mockMvc.perform(put("/produto").contentType(APPLICATION_JSON_UTF8)
	            .content(produto.toString()));
	}

	@Then("Atualizacao {string}")
	public void atualizacao(String resultado) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		resposta.andExpect(content().string(containsString(resultado)));
	}
}
