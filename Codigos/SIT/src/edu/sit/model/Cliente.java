package edu.sit.model;

import java.util.Date;

import edu.sit.uteis.CalculoIdade;

public class Cliente {
	
	private Integer id;
	private	String nome;
	private Date dataDeNascimento;
	private String endereco;
	private String cpf;
	private Contato contato;
	
	
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
	public Date getDataDeNascimento() {
		return dataDeNascimento;
	}
	public void setDataDeNascimento(Date dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Contato getContato() {
		return contato;
	}
	public void setContato(Contato contato) {
		this.contato = contato;
	}
	private Cliente( String nome, Date dataDeNascimento, String endereco, String cpf, Contato contato) {
		setNome(nome);
		setDataDeNascimento(dataDeNascimento);
		setEndereco(endereco);
		setCpf(cpf);
		setContato(contato);
	}
	
	public int getIdade(Date dataNacimento) {
				
		return  CalculoIdade.calculaIdade(dataNacimento);
		
	}
	public static Cliente criaPessoa(String nome,Date dataNascimento) {
		return new Cliente(nome,dataNascimento,"Endereço Não Informado","Cpf Não Informado",Contato.criaContato());
	}
	
	public static Cliente criaPessoa(Date dataDeNascimento,String cpf) {
		return new Cliente("Nome não Informado",dataDeNascimento,"Endereço não informado",cpf,Contato.criaContato());
	}
	@Override
	public String toString() {
	
		return getNome()+"\t\t"+getIdade(getDataDeNascimento());
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

	

}
