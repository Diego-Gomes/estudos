$("#botao-frase-id").click(buscaFrase);
$("#botao-frase").click(fraseAleatoria);

function fraseAleatoria() {

    $("#spinner").toggle();

    $.get("http://localhost:3000/frases", trocaFraseAleatoria)
        .fail(function () {
            $("#erro").toggle();
            setTimeout(function () {
                $("#erro").toggle();
            }, 1500);
        })
        .always(function () { // novo, escondendo o spinner
            $("#spinner").toggle();
        });
}

function trocaFraseAleatoria(data) {
    var frase = $(".frase");
    var numeroAleatorio = Math.floor(Math.random() * data.length);

    frase.text(data[numeroAleatorio].texto);

    atualizaTamanhoFrase();

    atualizaTempoInicial(data[numeroAleatorio].tempo);
}

function buscaFrase() {

    $("#spinner").toggle();
    var fraseId = $("#frase-id").val();

    //criacao do objeto JS que guarda a id
    var dados = {id : fraseId}; 

    //passando objecto como segundo parametro
    $.get("http://localhost:3000/frases", dados, trocaFrase)
    .fail(function(){
        $("#erro").toggle();
        setTimeout(function(){
            $("#erro").toggle();
    },2000);
    })
    .always(function(){
        $("#spinner").toggle();
    });
}

//arquivo frase.js
function trocaFrase(data) {

    console.log(data);

    var frase = $(".frase");
    frase.text(data.texto); //cuidado, texto com "o" no final 
    atualizaTamanhoFrase();
    atualizaTempoInicial(data.tempo);
}