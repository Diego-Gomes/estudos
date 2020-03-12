let buttonAddPatient = document.querySelector('#add-patient');

buttonAddPatient.addEventListener('click', function(event){

    event.preventDefault();
    let formAdd = document.querySelector('#form-add');
    let patient = getPatient(formAdd);

    let trPatient = createTrPatient(patient);
  
    var tdBodyPatients = document.querySelector('#table-patients');
    tdBodyPatients.appendChild(trPatient);

    formAdd.reset()

});

function createTrPatient(patient) {
    let trPatient       = document.createElement('tr');
    trPatient.classList.add('patient');

    let infosTD = [ {class: 'info-name'     , text: patient.name}, 
                    {class: 'info-weight'   , text: patient.weight},
                    {class: 'info-height'   , text: patient.height},
                    {class: 'info-fat'      , text: patient.fat}, 
                    {class: 'info-imc'      , text: patient.imc}
                ];

    infosTD.forEach(info => {
        let td =  document.createElement('td');
        td.classList.add(info.class);
        td.textContent = info.text;
        trPatient.appendChild(td);

    });

    return trPatient;
}

function getPatient(form) {
    let patient = new Patient(  form.nome.value, form.peso.value, 
                                form.altura.value, form.gordura.value);
    return patient;
}

