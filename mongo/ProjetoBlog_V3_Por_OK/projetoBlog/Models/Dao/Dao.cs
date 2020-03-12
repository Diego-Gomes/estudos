using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using MongoDB.Driver;
using Microsoft.Extensions.Options;

namespace projetoBlog.Models.Dao
{
    public class Dao
    {
        
        private readonly readonly IMongoDatabase _BaseDeDados;

        public Dao(IOptions<LocationSettings> settings)
        {
            var _cliente = new MongoClient(STRING_DE_CONEXAO);
            _BaseDeDados = _cliente.GetDatabase(NOME_DA_BASE);
        }



    }
}
