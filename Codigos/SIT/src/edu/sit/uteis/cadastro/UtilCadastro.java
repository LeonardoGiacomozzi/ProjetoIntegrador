package edu.sit.uteis.cadastro;

import java.time.LocalDate;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.CategoriaDao;
import edu.sit.bancodedados.dao.FornecedorDao;
import edu.sit.controller.cadastro.CategoriaController;
import edu.sit.controller.cadastro.FornecedorController;
import edu.sit.erro.cadastro.CadastroExeption;
import edu.sit.erro.leitura.LeituraException;
import edu.sit.erros.dao.DaoException;
import edu.sit.model.Categoria;
import edu.sit.model.Fornecedor;
import edu.sit.uteis.Leitor;

public class UtilCadastro {
	
	
	public static String pedeNome() {
		String nome = null;

		while (nome == null) {
			try {
				System.out.print("Nome:\t");
				nome = Leitor.leString();
			} catch (LeituraException e) {
				System.out.println(e.getMessage());
			}
		}
		return nome;
	}
	
	public static Integer pedeFornecedor() throws CadastroExeption {
		Integer fornecedorId = null;

		while (fornecedorId == null) {
			try {
				System.out.println("Fornecedor:\t");
				for (Fornecedor fornecedor : new FornecedorDao().consultaTodos()) {
					System.out.println("ID\t: " + fornecedor.getId() + "Nome\t: " + fornecedor.getNome());
				}
				System.out.println("0 --- CADASTRAR FORNECEDOR");
				fornecedorId = Leitor.leInteger();
				if (fornecedorId == 0) {
					System.out.println(FornecedorController.CadastraFornecedor() ? "Fornecedor cadastrada\n"
							: "Erro ao cadastrar categoria");
					fornecedorId = new FornecedorDao().pegaUltimoID();
				}
			} catch (LeituraException | ConexaoException | DaoException e) {
				System.out.println(e.getMessage());
			}
		}
		return fornecedorId;

	}

	public static Integer pedeCategoria() throws  CadastroExeption {
		Integer categoriaId = null;

		while (categoriaId == null) {
			try {
				System.out.println("Categoria:\t");
				for (Categoria categoria : new CategoriaDao().consultaTodos()) {
					System.out.println("ID\t: " + categoria.getId() + "Nome\t: " + categoria.getNome());
				}
				System.out.println("0 --- CADASTRAR CATEGORIA");
				categoriaId = Leitor.leInteger();
				if (categoriaId == 0) {
					System.out.println(CategoriaController.cadastro() ? "Categoria cadastrada\n"
							: "Erro ao cadastrar categoria");
					categoriaId = new CategoriaDao().pegaUltimoID();
				}
			} catch (LeituraException | ConexaoException | DaoException e) {
				System.out.println(e.getMessage());
			}
		}
		return categoriaId;

	}

	public static Integer pedeQuantidade() {

		Integer quantidade = null;

		while (quantidade == null) {
			try {
				System.out.print("Quantidade:\t");
				quantidade = Leitor.leInteger();
			} catch (LeituraException e) {
				System.out.println(e.getMessage());
			}
		}
		return quantidade;

	}

	public static Double pedeValorUnitario() {

		Double valorUnitario = null;

		while (valorUnitario == null) {
			try {
				System.out.print("Valor Unitario:\t");
				valorUnitario = Leitor.leDouble();
			} catch (LeituraException e) {
				System.out.println(e.getMessage());
			}
		}
		return valorUnitario;

	}

	public static String pedeCpf() {
	
		String cpf = null;
		while (cpf == null) {
			try {
				System.out.print("Cpf:\t");
				cpf = Leitor.leCpf();

			} catch (LeituraException e) {
				System.out.println(e.getMessage());

			}
		}
		return cpf;
	}

	public static String pedeEndereco() {
		
		String endereco = null;
		while (endereco == null) {
			try {
				System.out.print("Endereço:\t");
				endereco = Leitor.leString();
			} catch (LeituraException e) {
				System.out.println(e.getMessage());
			}
		}
		return endereco;
		
	}

	public static LocalDate pedeDataNascimento() {
		
		LocalDate dataNascimento = null;
		while (dataNascimento == null) {
			try {
				System.out.print("Data de Nascimento (dd/mm/aaaa):\t");
				dataNascimento = Leitor.leData();
			} catch (LeituraException e) {
				System.out.println(e.getMessage());
			}
		}
		return dataNascimento;
	}

	public static String pedeTelefone() {
		
		String tel = null;
		while (tel == null) {
			try {
				System.out.print("Telefone:\t");
				tel = Leitor.leString();
			} catch (LeituraException e) {
				System.out.println(e.getMessage());
			}
		}
		return tel;
	}
	
	public static String pedeEmail() {
		
		String email = null;
		
		while (email == null) {
			try {
				System.out.print("Email:\t");
				email = Leitor.leString();
			} catch (LeituraException e) {
				System.out.println(e.getMessage());
			}
		}
		
		return email;
	}

	public static String pedeCnpj() {
		String cnpj = null;

		while (cnpj == null) {
			
			try {
				System.out.print("Cnpj:\t");
				cnpj = Leitor.leCnpj();
			} catch (LeituraException e) {
				System.out.println(e.getMessage());
			}
		}
	return cnpj;
	}

	public static String pedePessoaResponsavel() {
		
		String pessoaResponsavel = null;

		while (pessoaResponsavel == null) {
			try {
				System.out.print("Nome da pessoa responsavel:\t");
				pessoaResponsavel = Leitor.leString();
			} catch (LeituraException e) {
				System.out.println(e.getMessage());
			}
		}
		return pessoaResponsavel;
	}

}
