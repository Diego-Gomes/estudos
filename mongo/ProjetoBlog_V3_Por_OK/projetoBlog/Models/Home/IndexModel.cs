using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace projetoBlog.Models.Home
{
    public class IndexModel
    {
        public IList<Publicacao> PublicacoesRecentes { get; set; }
    }
}