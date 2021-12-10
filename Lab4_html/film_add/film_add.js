import {getBackendUrl} from '../js/configuration.js';
import {getParameterByName} from '../js/dom_utils.js';

window.addEventListener('load', () => {
    const filmAddForm = document.getElementById('filmAddForm');
    filmAddForm.addEventListener('submit', event => filmAddAction(event));
});


function filmAddAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
    };
    xhttp.open("POST", getBackendUrl() + '/api/directors/' + getParameterByName("director") + "/films", true);

    const request = {
        'title': document.getElementById('title').value,
        'releaseYear': parseInt(document.getElementById('releaseYear').value)
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}