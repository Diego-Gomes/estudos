using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;
using MongoDB.Driver;
using projetoBlog.Models;
using projetoBlog.Models.Home;
using System.Linq.Expressions;
using MongoDB.Bson;
using projetoBlog.Models.Dao;

namespace projetoBlog.Controllers
{
    public class HomeController : Controller
    {
        DaoPublicacao daoPublicacao = new DaoPublicacao();
        public async Task<ActionResult> Index()
        {

            IList<Publicacao> PublicacoesRecentes = await daoPublicacao.ListarAsync();

            var model = new IndexModel
            {
                PublicacoesRecentes = PublicacoesRecentes
            };

            return View(model);
        }

        [HttpGet]
        public ActionResult NovaPublicacao()
        {
            return View(new NovaPublicacaoModel());
        }

        [HttpPost]
        public async Task<ActionResult> NovaPublicacao(NovaPublicacaoModel model)
        {
            if (!ModelState.IsValid)
            {
                return View(model);
            }

            var post = new Publicacao
            {
                Autor = User.Identity.Name,
                Titulo = model.Titulo,
                DataCriacao = DateTime.UtcNow,
                Conteudo = model.Conteudo,
                Tags = model.Tags.Split(' ', ',', ';'),
                Comentarios = new List<Comentario>()
            };

           await daoPublicacao.InserirAsync(post);

            return RedirectToAction("Publicacao", new { id = post.Id });
        }

        [HttpGet]
        public async Task<ActionResult> Publicacao(string id)
        {

            Publicacao publicacao = await daoPublicacao.ConsultarAsync(id);

            if (publicacao == null)
            {
                return RedirectToAction("Index");
            }

            var model = new PublicacaoModel
            {
                Publicacao = publicacao,
                NovoComentario = new NovoComentarioModel
                {
                    PublicacaoId = id
                }
            };

            return View(model);
        }

        [HttpGet]
        public async Task<ActionResult> Publicacoes(string tag = null)
        {
            IList<Publicacao> posts = await daoPublicacao.ListarAsync(tag);
            return View(posts);
        }

        [HttpPost]
        public async Task<ActionResult> NovoComentario(NovoComentarioModel model)
        {
            if (!ModelState.IsValid)
            {
                return RedirectToAction("Publicacao", new { id = model.PublicacaoId });
            }

            var comentario = new Comentario
            {
                Autor = User.Identity.Name,
                Conteudo = model.Conteudo,
                DataCriacao = DateTime.UtcNow
            };
            
            await daoPublicacao.InserirComentarioAsync(model.PublicacaoId, comentario);
            return RedirectToAction("Publicacao", new { id = model.PublicacaoId });
        }
    }
}