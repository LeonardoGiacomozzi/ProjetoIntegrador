package edu.sit.model;

import java.time.LocalDate;

import edu.sit.uteis.CalculoIdade;

public class Cliente {
	
	private Integer id;
	private	String nome;
	private LocalDate dataDeNascimento;
	private String endereco;
	private String cpf;
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
	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}
	public void setDataDeNascimento(LocalDate dataDeNascimento) {
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
	
	
	public Cliente (String nome, LocalDate dataDeNascimento, String endereco, String cpf, Integer contato) {
		setNome(nome);
		setDataDeNascimento(dataDeNascimento);
		setEndereco(endereco);
		setCpf(cpf);
		setContatoid(contato);
	}
	
	public Cliente(Integer id, String nome, LocalDate dataDeNascimento, String endereco, String cpf, Integer contato) {
		setId(id);
		setNome(nome);
		setDataDeNascimento(dataDeNascimento);
		setEndereco(endereco);
		setCpf(cpf);
		setContatoid(contato);
	}
	
	public static int getIdade(LocalDate dataNacimento) {
				
		return  CalculoIdade.calculaIdade(dataNacimento);
		
	}
	
	@Override
	public String toString() {
		return "ID: \t\t" + getId() + "\nNome: \t\t" + getNome()+ "\nIdade: \t\t" + getIdade(getDataDeNascimento()) + 
				"\nEnderešo: \t" + getEndereco() + "\nCPF: \t\t" + getCpf() + 
				(getContato() == null ? "\nContatoID: \t" + getContatoid() : getContato().toString());
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
