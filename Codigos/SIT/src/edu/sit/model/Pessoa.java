package edu.sit.model;

import java.util.Date;

import edu.sit.uteis.CalculoIdade;

public class Pessoa {
	
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
	private Pessoa( String nome, Date dataDeNascimento, String endereco, String cpf, Contato contato) {
		setNome(nome);
		setDataDeNascimento(dataDeNascimento);
		setEndereco(endereco);
		setCpf(cpf);
		setContato(contato);
	}
	
	public int getIdade(Date dataNacimento) {
				
		return  CalculoIdade.calculaIdade(dataNacimento);
		
	}
	public static Pessoa criaPessoa(String nome,Date dataNascimento) {
		return new Pessoa(nome,dataNascimento,"Endereço Não Informado","Cpf Não Informado",Contato.criaContato());
	}
	
	public static Pessoa criaPessoa(Date dataDeNascimento,String cpf) {
		return new Pessoa("Nome não Informado",dataDeNascimento,"Endereço não informado",cpf,Contato.criaContato());
	}
	@Override
	public String toString() {
	
		return getNome()+"\t\t"+getIdade(getDataDeNascimento());
	}

	

}
