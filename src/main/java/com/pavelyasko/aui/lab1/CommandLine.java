package com.pavelyasko.aui.lab1;

import com.pavelyasko.aui.lab1.director.service.DirectorService;
import com.pavelyasko.aui.lab1.film.entity.Film;
import com.pavelyasko.aui.lab1.film.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CommandLine implements CommandLineRunner {
    private FilmService filmService;

    private DirectorService directorService;

    @Autowired
    public CommandLine(FilmService filmService, DirectorService directorService) {
        this.filmService = filmService;
        this.directorService = directorService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scan = new Scanner(System.in);
        boolean running = true;

        System.out.println();
        listCommands();

        while (running) {
            String command = scan.nextLine();

            switch (command) {
                case "COMMANDS":
                    listCommands();
                    break;
                case "FILMS":
                    listFilms();
                    break;
                case "DIRECTORS":
                    listDirectors();
                    break;
                case "ADD_FILM":
                    addFilm();
                    break;
                case "DEL_FILM":
                    deleteFilm();
                    break;
                case "EXIT":
                    running = false;
                    break;
                default:
                    System.out.println("Wrong command, please try again\n");
            }
        }
    }

    private void listCommands() {
        System.out.println("============== List of available commands ============");
        System.out.println("COMMANDS - list available commands");
        System.out.println("FILMS - list all films");
        System.out.println("DIRECTORS - list all directors");
        System.out.println("ADD_FILM - add film");
        System.out.println("DEL_FILM - delete existing director");
        System.out.println("EXIT - stop the application");
        System.out.println();
    }

    private void listFilms() {
        System.out.println("============== List of all films =====================");
        filmService.findAll().forEach(System.out::println);
        System.out.println();
    }

    private void listDirectors() {
        System.out.println("============== List of all directors =================");
        directorService.findAll().forEach(System.out::println);
        System.out.println();
    }

    private void addFilm() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter title of the film");
        String title = scan.nextLine();

        System.out.println("Enter release year");
        int releaseYear = scan.nextInt();

        listDirectors();
        System.out.println("Enter id of the director");
        Long id = scan.nextLong();

        directorService.find(id).ifPresentOrElse(
                director -> {
                    filmService.save(Film.builder()
                            .title(title)
                            .releaseYear(releaseYear)
                            .director(director)
                            .build());
                    System.out.println("Film was added successfully\n");
                },
                () -> System.out.println("There is no director with such id, please try add film again\n"));
    }

    private void deleteFilm() {
        Scanner scan = new Scanner(System.in);

        listFilms();
        System.out.println("Enter id of the film");
        Long id = scan.nextLong();

        filmService.find(id).ifPresentOrElse(
                film -> {
                    filmService.delete(film.getId());
                    System.out.println("Film was deleted successfully\n");
                },
                () -> System.out.println("There is no film with such id, please try delete film again\n"));
    }
}
