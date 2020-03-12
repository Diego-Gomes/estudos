package br.com.alura.forum.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.alura.forum.model.Topico;

public class AtualizarTopicoForm {

	
	@NotNull @NotEmpty @Length(min=5)
	private String titulo;
	@NotNull @NotEmpty @Length(min=10)
	private String mensagem;
	

	public String getTitulo() {
		return titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public AtualizarTopicoForm(String titulo,String mensagem) {
		this.titulo = titulo;
		this.mensagem = mensagem;
	}

	public Topico atualizar(Topico topico) {
		
		topico.setMensagem(mensagem);
		topico.setTitulo(titulo);
		
		return topico;
	}


	
	
}
