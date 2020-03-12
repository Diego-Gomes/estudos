function desenhar() {
    
    var dadosJson = $.ajax({
        url: 'http://localhost:8080/bitcoin/',
        dataType: 'json',
        async: false,
    }).responseText;

    console.log(dadosJson);
}