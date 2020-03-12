package br.com.alura.forum.dto;

public class JWTDto {

	private String token;
	
	public JWTDto(String token, String tipoAutenticacao) {
		this.token = token;
		this.tipoAutenticacao = tipoAutenticacao;
	}

	private String tipoAutenticacao;

	public String getToken() {
		return token;
	}

	public String getTipoAutenticacao() {
		return tipoAutenticacao;
	}
	
	
	
}
