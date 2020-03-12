using Microsoft.AspNetCore.Mvc;
using Alura.Financas.Modelos;

namespace Alura.FinancasWeb.Controllers
{
	public class HomeController : Controller
	{
		public IActionResult Index()
		{
			var cliente = new Cliente("Fluano de Tal");
			var conta = new Conta("12387-12", cliente);
			conta.Deposita(500);
			conta.Saca(400);
			return Ok($"O saldo da conta é {conta.Saldo}");
		}
	}
}