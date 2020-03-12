package br.com.alura.forum.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.alura.forum.data.repository.ICursoRepository;
import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Topico;

public class TopicoForm {

	@NotNull @NotEmpty @Length(min=5)
	private String titulo;
	@NotNull @NotEmpty @Length(min=10)
	private String mensagem;
	@NotNull @NotEmpty
	private String nomeCurso;

	
	public String getTitulo() {
		return titulo;
	}


	public String getMensagem() {
		return mensagem;
	}


	public String getNomeCurso() {
		return nomeCurso;
	}


	public TopicoForm(String titulo, String mensagem, String nomeCurso) {
		this.titulo = titulo;
		this.mensagem = mensagem;
		this.nomeCurso = nomeCurso;
	}


	public Topico converter(ICursoRepository iCursoRepository) {
		
		Curso curso = iCursoRepository.findByNome(nomeCurso);
		
		return new Topico(titulo, mensagem, curso);
	}

}
