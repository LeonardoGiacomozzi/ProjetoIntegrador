package edu.sit.model;

public class Funcionario {

		private Integer id;
		private String nome;
		private ECargo cargo;
		
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
		@Override
		public String toString() {
			return "Funcionario [id=" + id + ", nome=" + nome + ", cargo=" + cargo + "]";
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
		private Funcionario(String nome, ECargo cargo) {
			setCargo(cargo);
			setNome(nome);
			
		}
		
		public static Funcionario criaFuncionario(String nome, ECargo cargo) {
			return new Funcionario(nome, cargo);
		}
		
}
