var fs = require('fs');

fs.readFile('sonic.png', function(error, buffer){

    fs.writeFile('sonic2.png', buffer, function(error){
        console.log('Imagem duplicada');
    });

});


fs.createReadStream('sonic.png')
.pipe(fs.createWriteStream('sonic3.png'))
.on('finish', function(){

    console.log('Imagem duplicada');
});