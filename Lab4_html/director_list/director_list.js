import {clearElementChildren, createLinkCell, createButtonCell, createTextCell} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';


window.addEventListener('load', () => {
    fetchAndDisplayDirectors();
});


function fetchAndDisplayDirectors() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayDirectors(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/directors', true);
    xhttp.send();
}

/**
 * @param {{directors: {id: number, name:string, surname:string}[]}} directors
 */
function displayDirectors(directors) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    directors.directors.forEach(director => {
        tableBody.appendChild(createTableRow(director));
    })
}


/**
 * @param {{id: number, name: string, surname:string}} director
*/
function createTableRow(director) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(director.name + " " + director.surname));
    tr.appendChild(createLinkCell('view', '../director_view/director_view.html?director=' + director.id));
    tr.appendChild(createLinkCell('edit', '../director_edit/director_edit.html?director=' + director.id));
    tr.appendChild(createButtonCell('delete', () => deleteDirector(director)));
    return tr;
}

/**
 * @param {{id: number, name: string, surname:string}} director
 */
function deleteDirector(director) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayDirectors();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/directors/' + director.id, true);
    xhttp.send();
}