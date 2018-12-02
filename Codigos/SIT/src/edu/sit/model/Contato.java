package edu.sit.model;

public class Contato {

	private Integer id;
	private String telefone;
	private String email;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	private Contato(Integer id, String telefone, String email) {
		setId(id);
		setTelefone(telefone);
		setEmail(email);
	}

	public static Contato criaContato() {
		return new Contato("Telefone não Informado", "Email não Informado");
	}

	public static Contato criaContato(String telefone, String email) {
		return new Contato(telefone, email);
	}

	public static Contato consultaContatoBanco(Integer id, String telefone, String email) {
		return new Contato(id, telefone, email);
	}

	@Override
	public String toString() {
		return "Telefone: " + getTelefone() + "\nE-mail: " + getEmail();
	}
}
