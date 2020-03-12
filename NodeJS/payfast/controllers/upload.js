var fs = require('fs');
    
module.exports = function(app){
    
    app.post('/upload/imagem', function(req, res) {

        let filename = req.headers.filename;
        console.log(filename);

        req.pipe(fs.createWriteStream('files/'+filename))
            .on('finish', function(){
                console.log('Arquivo recebido');

                res.status(201).send('Arquivo criado');

        });

    });

}