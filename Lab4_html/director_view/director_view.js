import {
    getParameterByName,
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    setTextNode
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    let addFilmAnchor = document.getElementById("addFilmAnchor");
    addFilmAnchor.href = '../film_add/film_add.html?director=' + getParameterByName('director');

    fetchAndDisplayDirector();
    fetchAndDisplayFilms();
});


function fetchAndDisplayFilms() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayFilms(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/directors/' + getParameterByName('director') + '/films', true);
    xhttp.send();
}

/**
 * @param {{films: {id: number, title:string}[]}} films
 */
function displayFilms(films) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    films.films.forEach(film => {
        tableBody.appendChild(createTableRow(film));
    })
}

/**
 * @param {{id: number, title: string}} film
 */
function createTableRow(film) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(film.title));
    tr.appendChild(createLinkCell('view', '../film_view/film_view.html?director='
        + getParameterByName('director') + '&film=' + film.id));
    tr.appendChild(createLinkCell('edit', '../film_edit/film_edit.html?director='
        + getParameterByName('director') + '&film=' + film.id));
    tr.appendChild(createButtonCell('delete', () => deleteFilm(film.id)));
    return tr;
}

/**
 * @param {number} filmID
 */
function deleteFilm(filmID) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayFilms();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/directors/' + getParameterByName('director')
        + '/films/' + filmID, true);
    xhttp.send();
}


function fetchAndDisplayDirector() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayDirector(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/directors/' + getParameterByName('director'), true);
    xhttp.send();
}

/**
 * @param {{id: number, name: string, surname:string, dateOfBirth:string }} director
 */
function displayDirector(director) {
    setTextNode('id', director.id.toString());
    setTextNode('name', director.name);
    setTextNode('surname', director.surname);
    setTextNode('dateOfBirth', director.dateOfBirth);
}