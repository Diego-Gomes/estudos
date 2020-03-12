using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Mongo
{
	class Program
	{
		static void Main(string[] args)
		{

			IDictionary<string, object> dicionario = new Dictionary<string, object>
			{
				{ "nome", "Diego Gomes" }
			};
			var documento = new MongoDB.Bson.BsonDocument(dicionario);

			Console.WriteLine(documento);

			Console.ReadKey();

		}
	}
}
