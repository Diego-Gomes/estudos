class Patient {
    
    constructor(nome, peso, altura, gordura){
        this.name       = nome;
        this.weight     = peso;
        this.height     = altura;
        this.fat        = gordura;
        this.imc        = this.calculteIMC(peso, altura);
    
    } 

    calculteIMC() {
        this.imc = this.weight / (this.height * this.height);
        return this.imc.toFixed(2) ;
    }

    validate() {
        
        let weightIsValid = true;
        let heightIsValid = true;
    
        if (this.weight <= 0 || this.weight >= 1000) {
            weightIsValid = false;
           
            console.log(this.trPatient);
        }
        if (this.height <= 0 || this.height >= 3.00) {
            heightIsValid = false;
        }
        return { weightIsValid, heightIsValid};
    }

}

let listTrPatients = document.querySelectorAll('.patient');

listTrPatients.forEach(trPatient => {
    
    let patient = getPatient(trPatient);

    let { weightIsValid, heightIsValid} = patient.validate();

    if(weightIsValid && heightIsValid){
        let tdImc  = trPatient.querySelector('.info-imc');
        tdImc.textContent = patient.calculteIMC();
    }else {
        trPatient.classList.toggle("paciente-invalido");
    }

});


function getPatient(trPatient) {
    let name    = trPatient.querySelector('.info-name')     .textContent;
    let weight  = trPatient.querySelector('.info-weight')   .textContent;
    let height  = trPatient.querySelector('.info-height')   .textContent;
    let fat     = trPatient.querySelector('.info-fat')      .textContent;
   
    let patient = new Patient(name, weight, height, fat);

    return patient;
}








