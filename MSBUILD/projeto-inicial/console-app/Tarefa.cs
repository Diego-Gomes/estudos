using System;

namespace Alura.MsBuild.Modelos
{
	public class Tarefa
	{
		public int Id { get; set; }
		public string Titulo { get; set; }
		public DateTime Prazo { get; set; }
		public Categoria Categoria { get; set; }
		
		public Tarefa(string titulo, Categoria categoria, DateTime prazo)
		{
			Titulo = titulo;
			Categoria = categoria;
			Prazo = prazo;
		}
		
		public int DiasParaSerConcluida => (Prazo - DateTime.Now).Days;
		
		override public string ToString()
		{
			return $"{Titulo} ({Categoria.Descricao}) vai terminar em {DiasParaSerConcluida} dias.";
		}	
	}
}