var botao = document.getElementById("calcula-imcs");
botao.onclick = function(){alert1(); alert2();};

function alert1(){
    alert("1");
}
function alert2(){
    alert("2");
}

function calculaTodosImcs(){
    
    var pacientes = document.getElementsByClassName("paciente");

    percorreArray(pacientes, function(trDoPaciente){
                                var trDoImc = trDoPaciente.getElementsByClassName("info-imc")[0];
                                var nome = trDoPaciente.getElementsByClassName("info-nome")[0].textContent;
                                var altura = trDoPaciente.getElementsByClassName("info-altura")[0].textContent;
                                var peso = trDoPaciente.getElementsByClassName("info-peso")[0].textContent;

                                var paciente = montaPaciente(nome,peso,altura);
                                var imc = paciente.pegaImc();
                                trDoImc.textContent = imc;
                                console.log(imc);

                            }); 

}



function montaPaciente(nome,peso,altura){
    
    var paciente = {
        nome : nome,
        altura: altura,
        peso : peso,
        pegaImc : function(){
            return peso/(altura*altura);
        }
    }
    return paciente;
}

function percorreArray(pacientes, comportamento){

    for(var posicaoAtual = 0; posicaoAtual <= pacientes.length -1; posicaoAtual++){
        var trDoPaciente = pacientes[posicaoAtual];
        
        comportamento(trDoPaciente)
    }
} 