package com.Produto.Model;

public class Produto {

	private String nome;
	private Double valor;
	private int id;
	
	public Produto() {
		
	}
	
	public Produto(String nome, Double valor, int id) {
		setNome(nome);
		setValor(valor);
		setId(id);
	}
	
	@Override
	public boolean equals(Object obj) {
		Produto produto = (Produto) obj;
		boolean val = Double.compare(valor,produto.getValor()) == 0 ? true:false;
		System.out.println(nome.equals(produto.getNome()) + " " + val + " " + (id == produto.getId()));
		return nome.equals(produto.getNome()) && val && id == produto.getId();
	}
	
	@Override
	public String toString() {
		return "{\"nome\":\""+ nome + "\", \"valor\":" + valor + ", \"id\":" + id + "}";
	}

	public void setNome(String nome) {
		// TODO Auto-generated method stub
		this.nome = nome;
	}

	public String getNome() {
		// TODO Auto-generated method stub
		return nome;
	}

	public void setValor(Double valor) {
		// TODO Auto-generated method stub
		this.valor = valor;
	}

	public Double getValor() {
		// TODO Auto-generated method stub
		return valor;
	}

	public void setId(int id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}

}
