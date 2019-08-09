package com.Produto.Service;

import org.mockito.InjectMocks;

import com.Produto.Model.Produto;
import com.Produto.Repository.ProdutoRepository;

public class ProdutoService {

	@InjectMocks
	private ProdutoRepository prodrep = new ProdutoRepository();
	
	public void Connect() {
		prodrep.Connect("127.0.0.1");
	}
	
	public void Close() {
		prodrep.close();
	}
	
	public String buscaProduto(int id) {
		String resposta = "Falha ao encontrar";
		if(id >= 0)
			resposta = prodrep.buscar(id);
		return resposta;
	}

	public String adicionarProduto(Produto produto) {
		String resposta = "Falha ao criar";
		if(produto != null && produto.getId() >= 0 && produto.getValor() >= 0.00 && buscaProduto(produto.getId()).equals("Falha ao encontrar")) {
			resposta = prodrep.adicionar(produto);
		}
		return resposta;
	}

	public String atualizarProduto(Produto produto) {
		String resposta = "Falha ao atualizar";
		if(produto != null && produto.getId() >= 0 && produto.getValor() >= 0.00 && !buscaProduto(produto.getId()).equals("Falha ao encontrar"))
			resposta = prodrep.atualizar(produto);
		return resposta;
	}

	public String deletarProduto(int id) {
		String resposta = "Falha ao deletar";
		if(id >= 0 && !buscaProduto(id).equals("Falha ao encontrar"))
			resposta = prodrep.deletar(id);
		return resposta;
	}

}
