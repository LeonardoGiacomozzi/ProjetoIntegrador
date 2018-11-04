package edu.sit.model;

public class Fornecedor {
	private Integer id;
	private String nome;
	private String CNPJ;
	private String pessoaResponsavel;
	private Contato contato;
	private Integer contatoid;

	public Integer getContatoid() {
		return contatoid;
	}

	public void setContatoid(Integer contatoid) {
		this.contatoid = contatoid;
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

	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public String getPessoaResponsavel() {
		return pessoaResponsavel;
	}

	public void setPessoaResponsavel(String pessoaResponsavel) {
		this.pessoaResponsavel = pessoaResponsavel;
	}

	private Fornecedor(String nome, String cNPJ, String pessoaResponsavel, Contato contato) {
		setCNPJ(cNPJ);
		setContato(contato);
		setNome(nome);
		setPessoaResponsavel(pessoaResponsavel);
	}
	
	private Fornecedor(Integer id, String nome, String cNPJ, String pessoaResponsavel, Integer contato) {
		setId(id);
		setNome(nome);
		setCNPJ(cNPJ);
		setPessoaResponsavel(pessoaResponsavel);
		setContatoid(contato);
	}

	public static Fornecedor criaFornecedorFull(String nome, String cNPJ, String pessoaResponsavel, Contato contato) {
		return new Fornecedor(nome, cNPJ, pessoaResponsavel, contato);
	}
	
	public static Fornecedor consultaFornecedorBanco(Integer id, String nome, String cNPJ, String pessoaResponsavel, Integer contato) {
		return new Fornecedor (id, nome, cNPJ, pessoaResponsavel, contato);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CNPJ == null) ? 0 : CNPJ.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object fornecedor) {
		if (this == fornecedor)
			return true;
		if (fornecedor == null)
			return false;
		if (getClass() != fornecedor.getClass())
			return false;
		Fornecedor other = (Fornecedor) fornecedor;
		if (CNPJ == null) {
			if (other.CNPJ != null)
				return false;
		} else if (!CNPJ.equals(other.CNPJ))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ID \t\t" + getId() + "\nFornecedor:\t\t" + getNome() + "\nContato:\t\t" + getContato().getEmail();
	}

}
