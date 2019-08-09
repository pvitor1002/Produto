package com.Produto.Test.Controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.Produto.ProdutoApplication;
import com.Produto.Controller.ProdutoController;
import com.Produto.Model.Produto;
import com.Produto.Service.ProdutoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=ProdutoApplication.class)
@WebMvcTest(ProdutoController.class)
public class ProdutoControllerTest {

	@MockBean
    ProdutoService prodserv;
	
	@Autowired
    MockMvc mockMvc;
    
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	@Test
	public void GetSuccessfulTest() throws Exception {
		when(prodserv.buscaProduto(1)).thenReturn("Sucesso ao encontrar");
		this.mockMvc.perform(get("/produto/1")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("Sucesso ao encontrar")));
		verify(prodserv).buscaProduto(1);
	}
	
	@Test
	public void PostSuccessfulTest() throws Exception {
		Produto produto = new Produto("Alface", 10.0, 2);
		when(prodserv.adicionarProduto(produto)).thenReturn("Sucesso ao criar");
	    this.mockMvc.perform(post("/produto").contentType(APPLICATION_JSON_UTF8)
		            .content(produto.toString()))
	      			.andDo(print())
	      			.andExpect(status().isOk())
	      			.andExpect(content().string(containsString("Sucesso ao criar")));
	    verify(prodserv).adicionarProduto(produto);
	}
	
	@Test
	public void PutSuccessfullTest() throws Exception {
		Produto produto = new Produto("Paçoca", 10.0, 2);
		when(prodserv.atualizarProduto(produto)).thenReturn("Sucesso ao atualizar");
	    this.mockMvc.perform(put("/produto").contentType(APPLICATION_JSON_UTF8)
		            .content(produto.toString()))
	      			.andDo(print())
	      			.andExpect(status().isOk())
	      			.andExpect(content().string(containsString("Sucesso ao atualizar")));
	    verify(prodserv).atualizarProduto(produto);
	}
	
	@Test
	public void DeleteSuccessfulTest() throws Exception {
		when(prodserv.deletarProduto(1)).thenReturn("Sucesso ao deletar");
		this.mockMvc.perform(delete("/produto/1")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("Sucesso ao deletar")));
		verify(prodserv).deletarProduto(1);
	}
}
