package edu.sit.model;

public class Categoria {

	private Integer id;
	private String nome;
	
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
		Categoria other = (Categoria) obj;
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
	@Override
	public String toString() {
		return "Caracteristica Id=" + getId() + " Nome=" + getNome();
	}
	private Categoria(String nome) {
		setNome(nome);
	}
	
	private Categoria(Integer id, String nome) {
		setNome(nome);
		setId(id);
	}
	
	public static Categoria criaCategoria(String nome) {
		return new Categoria(nome);
	}
	
	public static Categoria criaCategoriaId(Integer id,String nome) {
		return new Categoria(id,nome);
	}
}
