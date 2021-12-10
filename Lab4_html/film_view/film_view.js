import {
    getParameterByName,
    setTextNode
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';


window.addEventListener('load', () => {
    fetchAndDisplayFilm();
});


function fetchAndDisplayFilm() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayFilm(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/directors/' + getParameterByName('director') +
        '/films/' + getParameterByName('film'), true);
    xhttp.send();
}


/**
 * @param {{id: number, title: string, releaseYear:number }} film
 */
function displayFilm(film) {
    setTextNode('id', film.id.toString());
    setTextNode('title', film.title);
    setTextNode('releaseYear', film.releaseYear.toString());
}