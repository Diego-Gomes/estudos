var botao = document.querySelector("#adicionar-paciente");
botao.addEventListener("click", function(event){

    event.preventDefault();
    

    var paciente = montaPaciente(   document.querySelector("#campo-nome").value,
                                    document.querySelector("#campo-peso").value,
                                    document.querySelector("#campo-altura").value
                                );
    
    var novoPaciente = "<tr class='paciente'>"+
                            "<td class='info-nome'>"+paciente.nome+"</td>"+
                            "<td class='info-peso' id='peso-2'>"+paciente.peso+"</td>"+
                            "<td class='info-altura' id='altura-2'>"+paciente.altura+"</td>"+
                            "<td class='info-imc' id='imc-2'>"+paciente.pegaImc()+"</td>"+
                        "</tr>";

    var tabela = document.querySelector("table");
    tabela.innerHTML = tabela.innerHTML + novoPaciente;
    
});