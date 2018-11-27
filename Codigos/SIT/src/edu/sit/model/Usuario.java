package edu.sit.model;

public class Usuario {
	private String login;
	private String senha;
	
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getSenha() {
		return senha;
	}
	
	@Override
	public String toString() {
		return "\nLogin\t\t: "+getLogin();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
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
		Usuario other = (Usuario) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}
	
	private Usuario(String login) {
		setLogin(login);
		setSenha("@"+login);
	}
	
	
	private Usuario(String login,String senha) {
		setLogin(login);
		setSenha(senha);
	}
	
	
	public static Usuario criaUsuario(String login) {
		return new Usuario(login);
	}
	
	public static Usuario criaUsuario(String login,String senha) {
		return new Usuario(login,senha);
	}
	
}
