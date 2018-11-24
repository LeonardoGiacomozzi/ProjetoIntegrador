package edu.sit.model;

public class Produto {

	private Integer id;
	private String nome;
	private Integer categoriaId;
	private Categoria categoria;
	private Integer fornecedorId;
	private Fornecedor fornecedor;
	private Integer quantidade;
	private Double valorUnitario;

	public Categoria getCategoria() {
		return categoria;
	}

	public Integer getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}

	public Integer getFornecedorId() {
		return fornecedorId;
	}

	public void setFornecedorId(Integer fornecedorId) {
		this.fornecedorId = fornecedorId;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public static Produto criaProdutoSemFornecedor(String nome, Categoria categoria, Integer quatidade,
			Double valorUnitario) {
		return new Produto(nome, categoria,
				Fornecedor.criaFornecedorFull("Sem Fornecedor", "Sem Fornecedor", "Sem Fornecedor", 0), quatidade,
				valorUnitario);
	}

	public static Produto criaProduto(String nome, Categoria categoria, Integer quatidade, Double valorUnitario,
			Fornecedor fornecedor) {
		return new Produto(nome, categoria, fornecedor, quatidade, valorUnitario);
	}

	public static Produto criaProdutoBanco(String nome, Integer categoria, Integer fornedcedor, Integer quantidade,
			Double vaorUnitario) {
		return new Produto(nome, categoria, fornedcedor, quantidade, vaorUnitario);
	}

	public static Produto consultaProdutoBanco(Integer id, String nome, Integer quantidade, Double valorUnitario, Integer categoria, Integer fornecedor) {
		return new Produto(id, nome, quantidade, valorUnitario, categoria, fornecedor);
	}
	
	private Produto(Integer id, String nome, Integer quantidade, Double valorUnitario, Integer categoria, Integer funcionario) {
		setId(id);
		setNome(nome);
		setQuantidade(quantidade);
		setValorUnitario(valorUnitario);
		setCategoriaId(categoria);
		setFornecedorId(funcionario);
	}

	private Produto(String nome, Categoria categoria, Fornecedor fornecedor, Integer quantidade, Double valorUnitario) {
		setCategoria(categoria);
		setFornecedor(fornecedor);
		setNome(nome);
		setQuantidade(quantidade);
		setValorUnitario(valorUnitario);
	}

	private Produto(String nome, Integer categoria, Integer fornecedor, Integer quantidade, Double valorUnitario) {
		setCategoriaId(categoria);
		setFornecedorId(fornecedor);
		setNome(nome);
		setQuantidade(quantidade);
		setValorUnitario(valorUnitario);
	}

	@Override
	public String toString() {
		return "ID:\t\t" + getId() + "\nNome:\t\t" + getNome() +  
				(getCategoria() == null ? "\nCategoria: \tNão Preenchido " + getCategoriaId() : getCategoria().toString()) +
				(getFornecedor() == null ? "\nFornecedor: \tNão Preenchido " + getFornecedorId() : getFornecedor()) + 
				"\nQuantidade:\t" + getQuantidade() + "\nValor Unitário:\t" + getValorUnitario();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object produto) {
		if (this == produto)
			return true;
		if (produto == null)
			return false;
		if (getClass() != produto.getClass())
			return false;
		Produto outroProduto = (Produto) produto;
		if (id == null) {
			if (outroProduto.id != null)
				return false;
		} else if (!id.equals(outroProduto.id))
			return false;
		if (nome == null) {
			if (outroProduto.nome != null)
				return false;
		} else if (!nome.equals(outroProduto.nome))
			return false;
		return true;
	}
}
