
const db = require('../../config/database')
const LivroDao = require('../dao/livro-dao');



module.exports = (app) => {

    app.get('/', function(req, res){

        res.send("<HTML><HEAD><META  charset='UTF-8'/></HEAD><BODY><H1>SERVIDOR NODE </H1></BODY></HTML>")
    
    });
    
    app.get('/livros', function(req, res){

        const livroDao = new LivroDao(db);
        livroDao.lista()
        .then(livros => res.marko(
            require('../views/livros/lista/lista.marko'),
            {
                livros
            }

        ))
        .catch(erro => console.log(erro));
        
    });
};
