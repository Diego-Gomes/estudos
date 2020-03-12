import java.util.Calendar;

public class Funcionario {

	private String nome;
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	private int idade;
	private Calendar dataNascimento;

	public Funcionario(String nome, int idade, Calendar dataNacimento) {
		// TODO Auto-generated constructor stub

		this.nome = nome;
		this.idade = idade;
		this.dataNascimento = dataNacimento;
	}

}
