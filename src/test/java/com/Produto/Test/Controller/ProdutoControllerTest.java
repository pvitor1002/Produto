package com.Produto.Test.Controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.Produto.ProdutoApplication;
import com.Produto.Controller.ProdutoController;
import com.Produto.Model.Produto;
import com.Produto.Service.ProdutoService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=ProdutoApplication.class)
@WebMvcTest(ProdutoController.class)
public class ProdutoControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @MockBean
	private ProdutoService prodserv;
	@Test
	public void GetSuccessfulTest() throws Exception {
		when(prodserv.buscaProduto(1)).thenReturn("Sucesso ao Encontrar");
		this.mockMvc.perform(get("/produto/1")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("Sucesso ao Encontrar")));
	}

}
