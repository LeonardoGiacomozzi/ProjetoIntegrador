package edu.sit.DataObject;

import java.util.ArrayList;

import edu.sit.model.Produto;

public class ProdutoQuantidade {
	private ArrayList<Produto> itensPedido = new ArrayList<Produto>();
	private ArrayList<Integer> quantidadeProduto= new ArrayList<Integer>();
	
	public ArrayList<Produto> getItensPedido() {
		return itensPedido;
	}
	public void setItensPedido(ArrayList<Produto> itensPedido) {
		this.itensPedido = itensPedido;
	}
	public ArrayList<Integer> getQuantidadeProduto() {
		return quantidadeProduto;
	}
	public void setQuantidadeProduto(ArrayList<Integer> quantidadeProduto) {
		this.quantidadeProduto = quantidadeProduto;
	}
	public ProdutoQuantidade(ArrayList<Produto> produtos, ArrayList<Integer> quantidadeProduto) {
		setItensPedido(produtos);
		setQuantidadeProduto(quantidadeProduto);
	}
}
