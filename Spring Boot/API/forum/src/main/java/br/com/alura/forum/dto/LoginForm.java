package br.com.alura.forum.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {

	private String email;
	private String senha;
	
	
	public String getEmail() {
		return email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public LoginForm(String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}
	
	@Override
	public String toString() {
		return this.email+" "+this.senha;
	}

	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(email, senha);
	}

}
