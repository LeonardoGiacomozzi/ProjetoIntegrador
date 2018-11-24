package edu.sit.model;

public class Funcionario {

	private Integer id;
	private String nome;
	private String cpf;
	private ECargo cargo;
	private Contato contato;
	private Integer contatoid;

	public Integer getContatoid() {
		return contatoid;
	}

	public void setContatoid(Integer contatoid) {
		this.contatoid = contatoid;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public ECargo getCargo() {
		return cargo;
	}

	public void setCargo(ECargo cargo) {
		this.cargo = cargo;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	@Override
	public String toString() {
		return "Funcionario: \t" + getNome() + "\nCPF: \t\t" + getCpf() + "\nCargo: \t\t" + getCargo() 
				+ (getContato() == null ? "\nContatoID: \t" + getContatoid() : getContato().toString());
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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	private Funcionario(String nome, String cpf, ECargo cargo, Contato contato) {
		setNome(nome);
		setCpf(cpf);
		setCargo(cargo);
	}

	private Funcionario(Integer id, String nome, String cpf, ECargo cargo, Integer contato) {
		setId(id);
		setNome(nome);
		setCpf(cpf);
		setContatoid(contato);
		setCargo(cargo);
	}

	private Funcionario(String nome, String cpf, ECargo cargo, Integer contato) {
		setNome(nome);
		setCpf(cpf);
		setContatoid(contato);
		setCargo(cargo);

	}

	public static Funcionario criaFuncionario(String nome, String cpf, ECargo cargo, Contato contato) {
		return new Funcionario(nome, cpf, cargo, contato);
	}

	public static Funcionario criaFuncionarioBanco(String nome, String cpf, ECargo cargo, Integer contato) {
		return new Funcionario(nome, cpf, cargo, contato);
	}

	public static Funcionario consultaFuncionarioBanco(Integer id, String nome, String cpf, ECargo cargo,
			Integer contato) {
		return new Funcionario(id, nome, cpf, cargo, contato);
	}
	public static Funcionario criaFuncionario(String nome, String cpf, ECargo cargo, Integer contato) {
		return new Funcionario(nome, cpf, cargo, contato);}
}
