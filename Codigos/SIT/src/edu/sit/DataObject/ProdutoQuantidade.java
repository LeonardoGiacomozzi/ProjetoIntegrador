package edu.sit.DataObject;


import edu.sit.model.Produto;

public class ProdutoQuantidade {
	private Produto itensPedido;
	private Integer quantidadeProduto;
	public Produto getItensPedido() {
		return itensPedido;
	}
	public void setItensPedido(Produto itensPedido) {
		this.itensPedido = itensPedido;
	}
	public Integer getQuantidadeProduto() {
		return quantidadeProduto;
	}
	public void setQuantidadeProduto(Integer quantidadeProduto) {
		this.quantidadeProduto = quantidadeProduto;
	}
	 public ProdutoQuantidade(Produto produto, Integer quantidade) {

		 setItensPedido(produto);
		 setQuantidadeProduto(quantidade);
	}
	 public ProdutoQuantidade() {

	}
	
	
}
