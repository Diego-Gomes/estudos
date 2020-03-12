package br.com.alura.forum.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.forum.model.Curso;

public interface ICursoRepository extends JpaRepository<Curso, Long>{

	Curso findByNome(String nomeCurso);

}
