var logger = require('../servicos/logger');


module.exports = function(app){

  const PAGAMENTO_CRIADO = "CRIADO";
  const PAGAMENTO_CONFIRMADO = "CONFIRMADO";
  const PAGAMENTO_CANCELADO = "CANCELADO";

  app.get('/pagamentos', function(req, res){
    console.log('Recebida requisicao de teste na porta 3000.')
    logger.info('Recebida requisicao de teste na porta 3000.')
    res.send('OK.');
  });

  app.get('/pagamentos/pagamento/:id', function(req, res){
    var id = req.params.id;
    console.log('consultando pagamento: ' + id);

    var memcachedClient = app.servicos.memcachedClient();

    memcachedClient.get('pagamento-' + id, function(erro, retorno){
      if (erro || !retorno){
        console.log('MISS - chave nao encontrada');

        let dao = criarConexao();

        dao.buscaPorId(id, function(erro, resultado){
          if(erro){
            console.log('erro ao consultar no banco: ' + erro);
            res.status(500).send(erro);
            return;
          }
          console.log('pagamento encontrado: ' + JSON.stringify(resultado));
          res.json(resultado);
          return;
        });
        //HIT no cache
      } else {
        console.log('HIT - valor: ' + JSON.stringify(retorno));
        res.json(retorno);
        return;
      }
    });

  });

  app.post('/pagamentos/pagamento', function(req, res){

    if (validarDadosPagamento(req)){
      res.status(400).send(erros);
      return;
    }

    let body = req.body;
    let pagamento = body['pagamento'];

    pagamento.status = PAGAMENTO_CRIADO;
    pagamento.data = new Date;

    let dao = criarConexao();

    dao.salva(pagamento, function(erro, resultado){
      if(erro) {
        console.log('Erro ao inserir no banco:' + erro);
        res.status(500).send(erro);
        return;
      }
      pagamento.id = resultado.insertId;
      console.log('pagamento criado: '+pagamento.id);
     
      // ISERINDO NO CACHE
      var cache = app.servicos.memcachedClient();
      cache.set('pagamento-' + pagamento.id, pagamento, 100000, function (err) {
         console.log('nova chave: pagamento-' + pagamento.id)
       });

      if (pagamento.forma_de_pagamento == 'cartao'){
        var cartao = req.body["cartao"];
       
        var cartoesClient = new app.servicos.cartoesClient();

        cartoesClient.autoriza(cartao, function(exception, request, response, retorno){
              if(exception){
                console.log(exception);
                res.status(400).send(retorno);
                return;
              }

              jsonSucesso(res, pagamento, retorno);
              return;
        });

      }else{
          jsonSucesso(res, pagamento, {});
      }

    });

  });

  app.put('/pagamentos/pagamento/:id', function(req, res){

    let pagamento = {};
    //pendente -- validar se o pagamento existe
    pagamento.id = req.params.id;
    pagamento.status = PAGAMENTO_CONFIRMADO;

    let dao = criarConexao();

    dao.atualiza(pagamento, function(erro, resultao){

      if(erro){
        res.stauts(500).send(erro);
        return;
      }
      
      res.status(200).send('PAGAMENTO CONFIRMADO: '+pagamento.id);

    });

  });

  app.delete('/pagamentos/pagamento/:id', function(req, res){

    let id = req.params.id;
    let pagamento = {};

    //pendente -- validar se o pagamento existe
    pagamento.id = id;
    pagamento.status = PAGAMENTO_CANCELADO;

    let dao = criarConexao();

    dao.atualiza(pagamento, function(erro, resultao){

      if(erro){
        res.stauts(500).send(erro);
        return;
      }
      

      res.status(204).send();


    });

  });


  function validarDadosPagamento(req){
    req.assert("pagamento.forma_de_pagamento",
    "Forma de pagamento eh obrigatorio").notEmpty();
    req.assert("pagamento.valor", "Valor eh obrigatorio e deve ser um decimal").notEmpty().isFloat();

    let erros = req.validationErrors();
    return erros;
  }

  function jsonSucesso(res, pagamento, cartao){

    let response = {
      dados_do_pagamanto: pagamento,
      cartao: cartao,
      links: [
        {
          href:"http://localhost:3000/pagamentos/pagamento/"
                  + pagamento.id,
          rel:"confirmar",
          method:"PUT"
        },
        {
          href:"http://localhost:3000/pagamentos/pagamento/"
                  + pagamento.id,
          rel:"cancelar",
          method:"DELETE"
        }
      ]
    }
    
      res.location('/pagamentos/pagamento/' + pagamento.id);
      res.status(201).json(response);
  }

  function criarConexao(){
    return new app.persistencia.PagamentoDao(app.persistencia.connectionFactory());
  }

}
