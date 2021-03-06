﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Alura.ListaLeitura.Modelos;
using Alura.ListaLeitura.Persistencia;
using Microsoft.AspNetCore.Mvc;

// For more information on enabling MVC for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace Alura.WebAPI.WebApp.API
{
	[ApiController]
	[Route("[controller]")]
	public class LivrosController : ControllerBase
	{

		private readonly IRepository<Livro> _repo;

		public LivrosController(IRepository<Livro> repo)
		{
			_repo = repo;
		}


		[HttpGet]
		public IActionResult Recuperar()
		{
			var model = _repo.All.Select(x => x.ToModel()).ToList();
			if (model == null)
				return NotFound();

			return Ok(model);
		}

		[HttpGet("{id}")]
		public IActionResult Recuperar(int id)
		{
			var model = _repo.Find(id);
			if (model == null)
				return NotFound();

			return Ok(model);
		}

		[HttpPost]
		public IActionResult Incluir([FromBody] LivroUpload model)
		{
			if (ModelState.IsValid) {
				var livro = model.ToLivro();
				_repo.Incluir(livro);

				return Created("Recuperar", new { id = livro.Id });
			}

			return BadRequest();

		}

		[HttpPut]
		public IActionResult Alterar([FromBody] LivroUpload model)
		{
			if (ModelState.IsValid)
			{
				var livro = model.ToLivro();
				if (model.Capa == null)
				{
					livro.ImagemCapa = _repo.All
						.Where(l => l.Id == livro.Id)
						.Select(l => l.ImagemCapa)
						.FirstOrDefault();
				}
				_repo.Alterar(livro);
				return Ok();
			}
			return BadRequest();
		}

		[HttpDelete]
		public IActionResult Remover(int id)
		{
			var model = _repo.Find(id);
			if (model == null)
			{
				return NotFound();
			}
			_repo.Excluir(model);

			return NoContent();
		}
	}
}
