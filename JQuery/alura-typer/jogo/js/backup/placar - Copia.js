$("#botao-sync").click(sincronizarPlacar);
$("#botao-placar").click(mostraPlacar);

function mostraPlacar() {
    var modal = $('.modal');
    var instance = M.Modal.getInstance(modal);
    instance.open();
}

function inserePlacar() {
    var corpoTabela = $(".placar").find("tbody");
    var usuario = $("#usuarios").val();
    var numPalavras = $("#contador-palavras").text();

    var linha = novaLinha(usuario, numPalavras);
    linha.find(".botao-remover").click(removeLinha);

    corpoTabela.append(linha);

}

function novaLinha(usuario, palavras) {
    var linha = $("<tr>");
    var colunaUsuario = $("<td>").text(usuario);
    var colunaPalavras = $("<td>").text(palavras);
    var colunaRemover = $("<td>");

    var link = $("<a>").addClass("botao-remover").attr("href", "#");
    var icone = $("<i>").addClass("small").addClass("material-icons").text("delete");

    link.append(icone);

    colunaRemover.append(link);

    linha.append(colunaUsuario);
    linha.append(colunaPalavras);
    linha.append(colunaRemover);

    return linha;
}

function removeLinha() {
    event.preventDefault();
    var linha = $(this).parent().parent();

    linha.fadeOut(1000);
    setTimeout(function () {
        linha.remove();
    }, 1000);
}

function sincronizarPlacar() {

    var placar = [];
    var linhas = $("tbody>tr");

    linhas.each(function () {
        var usuario = $(this).find("td:nth-child(1)").text();
        var palavras = $(this).find("td:nth-child(2)").text();

        var score = {
            usuario: usuario,
            pontos: palavras
        };

        placar.push(score);

    });
    //novo
    var dados = {
        placar: placar
    };

    $.post("http://localhost:3000/placar", dados, function () {
        M.Toast.dismissAll();
        M.toast({html: 'Sync success.', classes: 'rounded'});
    }).fail(function () {
        M.toast({html: 'Sync error.', classes: 'rounded'});
    }).always(function () {
     
    });
}

function atualizaPlacar(){
    $.get("http://localhost:3000/placar",function(data){
        $(data).each(function(){
            var linha = novaLinha(this.usuario, this.pontos);

            linha.find(".botao-remover").click(removeLinha);

            $("tbody").append(linha);
        });
    });
}