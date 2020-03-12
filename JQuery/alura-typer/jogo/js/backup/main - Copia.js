
var tempoInicial = $("#tempo-digitacao").text();
var campo = $(".campo-digitacao");

//$(document).ready();
$(function(){
    atualizaTamanhoFrase();
    inicializaContadores();
    inicializaCronometro();
    inicializaMarcadores();
    $("#botao-reiniciar").click(reiniciaJogo);
    $('#usuarios').selectize({
        create: true,
        sortField: 'text'
    });
    atualizaPlacar();
    $('.modal').modal();
    $('.tooltipped').tooltip();
    $('.tap-target').tapTarget();
    $('.parallax').parallax();
});


function atualizaTamanhoFrase() {
    var frase = $(".frase").text();
    var numPalavras  = frase.split(" ").length;
    var tamanhoFrase = $("#tamanho-frase");
    tamanhoFrase.text(numPalavras);
}

function inicializaContadores() {
    campo.on("input", function() {
        var conteudo = campo.val();

        var qtdPalavras = conteudo.split(/\S+/).length - 1;
        $("#contador-palavras").text(qtdPalavras);

        var qtdCaracteres = conteudo.length;
        $("#contador-caracteres").text(qtdCaracteres);

    });
}

function inicializaCronometro() {
    campo.one("focus", function() {
        var tempoRestante = $("#tempo-digitacao").text();
        $("#botao-reiniciar").attr("disabled",true);
        var cronometroID = setInterval(function() {
            tempoRestante--;
            var tempoFormatado = tempoRestante;
            if(tempoRestante < 10){
                tempoFormatado = '0'+tempoRestante;
            }
            $("#tempo-digitacao").text(tempoFormatado);
            if (tempoRestante < 1) {
                clearInterval(cronometroID);
                finalizaJogo();
                
            }
        }, 1000);
    });
}

function finalizaJogo() {
    campo.attr("disabled", true);
    campo.toggleClass("campo-desativado");

    $("#botao-reiniciar").toggleClass("pulse");
    $("#botao-reiniciar").attr("disabled", false);
    inserePlacar();

    M.toast({html: 'Time is over', classes: 'rounded  pink lighten-1', completeCallback: mostraPlacar, inDuration: 10, outDuration: 10})

}

function reiniciaJogo(){
    campo.attr("disabled",false);
    campo.val("");
    $("#contador-palavras").text("0");
    $("#contador-caracteres").text("0");
    $("#tempo-digitacao").text(tempoInicial);
    inicializaCronometro(); //novo
    campo.toggleClass("campo-desativado"); 
    campo.removeClass("borda-vermelha");
    campo.removeClass("borda-verde"); 
    $("#botao-reiniciar").toggleClass("pulse");
    $(this).attr("disabled",true);
}

function inicializaMarcadores() {
    campo.on("input", function() {
        var frase = $(".frase").text();
        var digitado = campo.val();
        var comparavel = frase.substr(0 , digitado.length);

        if(digitado == comparavel) {
            campo.addClass("borda-verde");
            campo.removeClass("borda-vermelha");
        } else {
            campo.addClass("borda-vermelha");
            campo.removeClass("borda-verde");
       
        }
    });
}


function atualizaTempoInicial(tempo) {
    
    if(tempo < 10){
        tempo = '0'+tempo;
    }

    tempoInicial = tempo;



    $("#tempo-digitacao").text(tempo);
}