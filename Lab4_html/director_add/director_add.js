import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const directorAddForm = document.getElementById('directorAddForm');
    directorAddForm.addEventListener('submit', event => directorAddAction(event));
});


function directorAddAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {};
    xhttp.open("POST", getBackendUrl() + '/api/directors', true);

    const request = {
        'name': document.getElementById('name').value,
        'surname': document.getElementById('surname').value,
        'dateOfBirth': document.getElementById('dateOfBirth').value
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}