package edu.sit.model;

public class Contato {
	
	private String telefone;
	private String email;
	
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private Contato(String telefone, String email) {
		setEmail(email);
		setTelefone(telefone);
	}
	public static Contato criaContato() {
		return new Contato("Telefone não Informado","Email não Informado");
	}
	public static Contato criaContato(String telefone, String email) {
		return new Contato(telefone,email);
	}

}
