package br.com.alura.forum.configuracao.erro;

public class ErroDeFormularioDto {

	public ErroDeFormularioDto(String campo, String erro) {
		super();
		this.campo = campo;
		this.erro = erro;
	}
	
	public String getCampo() {
		return campo;
	}
	public String getErro() {
		return erro;
	}

	private String campo;
	private String erro;
	
}
