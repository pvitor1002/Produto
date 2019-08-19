package com.Produto.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.Produto.Model.Produto;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.Delete.Where;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
@Repository
public class ProdutoRepository {
	
	private Cluster cluster;
	private Session session;
	
	public void connect(final String node) {
		cluster = Cluster.builder().withoutJMXReporting().addContactPoint(node).build();
		session = cluster.connect();
	}
	
	public void close() {
		cluster.close();
		session.close();
	}
	public String buscar(int id) {
		connect("127.0.0.1");
		Produto produto = null;
		ResultSet result = session.execute("Select * from StorageControl.produtos where id = " + id + ";");
		List<Row> list = result.all();
		close();
		if(list.size() == 0)
			return "Falha ao encontrar";
		else {
			System.out.println(list + " " + list.size());
			for (Row row : list) {
				produto = new Produto(row.getString("nome"), new Double(row.getFloat("valor")), row.getInt("id"));
			}
			if(produto != null) {
				return produto.toString();
			}
			close();
			return "Falha ao encontrar";
		}
	}
	
	public String adicionar(Produto produto) {
		connect("127.0.0.1");
		try {
		Insert insert = QueryBuilder.insertInto("StorageControl","produtos")
				.value("id", produto.getId())
				.value("nome", produto.getNome())
				.value("valor", produto.getValor());
		session.execute(insert);
		close();
		return "Sucesso ao criar";
		} catch (Exception e) {
			close();
			return "Falha ao criar";
		}
	}

	public String atualizar(Produto produto) {
		connect("127.0.0.1");
		try{
			session.execute("UPDATE StorageControl.produtos SET nome = \'" + produto.getNome() + "\', valor = " + produto.getValor() + " WHERE id = " + produto.getId() + ";");
			close();
			return "Sucesso ao atualizar";
		} catch(Exception e) {
			close();
			return "Falha ao atualizar";
		}
	}

	public String deletar(int id) {
		connect("127.0.0.1");
		try {
			Where delete = QueryBuilder.delete().from("StorageControl", "produtos").where(QueryBuilder.eq("id", id));
			session.execute(delete);
			close();
			return "Sucesso ao deletar";
		} catch(Exception e) {
			close();
			return "Falha ao deletar";
		}
	}

}
