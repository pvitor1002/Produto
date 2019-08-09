package com.Produto.Repository;

import com.Produto.Model.Produto;

public class ProdutoRepository {

	public String adicionar(Produto produto) {
		return "Sucesso ao criar";
	}

	public String buscar(int id) {
		// TODO Auto-generated method stub
		return "Sucesso ao encontrar";
	}

	public String atualizar(Produto produto) {
		// TODO Auto-generated method stub
		return "Sucesso ao atualizar";
	}

	public String deletar(int id) {
		// TODO Auto-generated method stub
		return "Sucesso ao deletar";
	}

}
