using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using MongoDB.Driver;

namespace projetoBlog.Models.Dao
{
    public class DaoUsuario : Dao
    {

        private static readonly string _NOME_DA_COLECAO = "Usuarios" ;
        private static readonly IMongoCollection<Usuario> Collection = _BaseDeDados.GetCollection<Usuario>(_NOME_DA_COLECAO);
        
        public async Task<Usuario> ConsultarAsync(string email)
		{
           var usuario = await Collection.Find(x => x.Email == email).SingleOrDefaultAsync();
           return usuario;
        }

        public async Task InserirAsync(Usuario usuario)
		{
            await Collection.InsertOneAsync(usuario);
        }
    }
} 
