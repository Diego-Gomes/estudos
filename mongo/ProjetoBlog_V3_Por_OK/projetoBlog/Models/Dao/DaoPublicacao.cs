using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using MongoDB.Driver;

namespace projetoBlog.Models.Dao
{
    public class DaoPublicacao : Dao
    {

        private static readonly string NOME_DA_COLECAO = "Publicacoes"; 
        private static readonly IMongoCollection<Publicacao> Collection = _BaseDeDados.GetCollection<Publicacao>(NOME_DA_COLECAO);


        public async Task<IList<Publicacao>> ListarAsync(string tag = null)
        {
            var filter = Builders<Publicacao>.Filter.Empty;

            if (tag != null)
                filter = Builders<Publicacao>.Filter.AnyEq(x => x.Tags, tag);

            List<Publicacao> publicacoes = await Collection.Find(filter)
                                                            .SortByDescending(x => x.DataCriacao)
                                                            .Limit(10)
                                                            .ToListAsync();
           return publicacoes;
        }

        public async Task InserirAsync(Publicacao publicacao)
		{
            await Collection.InsertOneAsync(publicacao);
        }

        public async Task<Publicacao> ConsultarAsync(string id = null)
		{

            var filter = Builders<Publicacao>.Filter.Eq(x => x.Id, id);

            Publicacao publicacao = await Collection.Find(filter)
                                                     .SingleOrDefaultAsync();

            return publicacao;
        }

        public async Task InserirComentarioAsync(string id, Comentario comentario)
		{

            var filter = Builders<Publicacao>.Filter.Eq(x => x.Id, id);
            var update = Builders<Publicacao>.Update.Push(x => x.Comentarios, comentario);

            await Collection.UpdateOneAsync(filter, update);

        }

    }
}
