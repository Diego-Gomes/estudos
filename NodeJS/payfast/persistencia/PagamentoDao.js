function PagamentoDao(connection) {
    this._connection = connection;
}

PagamentoDao.prototype.salva = function(pagamento,callback) {
    this._connection.query('INSERT INTO pagamentos SET ?', pagamento, callback);
    this._connection.end();
}


PagamentoDao.prototype.atualiza = function(pagamento,callback) {
    this._connection.query('UPDATE pagamentos SET status = ? WHERE id = ?', 
                                [pagamento.status, pagamento.id], callback);
    this._connection.end();
}

PagamentoDao.prototype.lista = function(callback) {
    this._connection.query('select * from pagamentos',callback);
    this._connection.end();
}

PagamentoDao.prototype.buscaPorId = function (id,callback) {
    this._connection.query("select * from pagamentos where id = ?",[id],callback);
    this._connection.end();
}

module.exports = function(){
    return PagamentoDao;
};
