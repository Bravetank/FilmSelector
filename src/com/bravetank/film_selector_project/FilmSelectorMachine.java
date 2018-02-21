package com.bravetank.film_selector_project;

import com.bravetank.film_selector_project.model.Film;
import com.bravetank.film_selector_project.model.FilmList;
import com.bravetank.film_selector_project.model.FilmRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class FilmSelectorMachine {

    private HashMap<String, String> menu;
    private FilmList filmList;
    private BufferedReader reader;
    private Queue<FilmRequest> filmQueue;

    public FilmSelectorMachine(FilmList filmList) {
        this.filmList = filmList;
        reader = new BufferedReader(new InputStreamReader(System.in));
        filmQueue = new ArrayDeque<FilmRequest>();
        menu = new HashMap<String, String>();
        menu.put("add", "Add a new film to the film list");
        System.out.printf("%n");
        menu.put("play", "Play next film in queue");
        System.out.printf("%n");
        menu.put("choose", "Choose film to watch");
        System.out.printf("%n");
        menu.put("quit", "Give up. Exit the program");
    }


    private String promptAction() throws IOException {
        System.out.printf("There is/are %d film(s) in the film list and %d in the queue %n",
                filmList.getFilmCount(),
                filmQueue.size());
        for (Map.Entry<String, String> option : menu.entrySet()) {
            System.out.printf("%s - %s %n",
                    option.getKey(),
                    option.getValue());
        }
        System.out.print("What do you want to do: ");
        System.out.printf("%n");
        String choice = reader.readLine();
        return choice.trim().toLowerCase();
    }

    public void run() {
        String choice = "";
        do {
            try {
                choice = promptAction();
                switch (choice) {
                    case "add":
                        Film film = promptNewFilm();
                        filmList.addFilm(film);
                        System.out.printf("%s added! %n%n", film);
                        break;
                    case "play":
                        playNext();
                        break;
                    case "choose":
                        String viewerName = promptforViewerName();
                        String artist = promptArtist();
                        Film artistFilm = promptFilmForArtist(artist);
                        FilmRequest filmRequest = new FilmRequest(viewerName, artistFilm);
                        if (filmQueue.contains(filmRequest)) {
                            System.out.printf("Whoops %s has already requested %s%n",
                                    viewerName,
                                    artistFilm);
                            break;
                        }
                        filmQueue.add(filmRequest);
                        System.out.printf("You chose: %s %n", artistFilm);
                        break;
                    case "quit":
                        System.out.println("Thanks for playing!");
                        break;
                    default:
                        System.out.printf("Unknown choice: '%s'. Try again. %n%n%n",
                                choice);
                }
            } catch (IOException ioe) {
                System.out.println("Problem with input");
                ioe.printStackTrace();
            }
        } while (!choice.equals("quit"));
    }

    private String promptforViewerName() throws IOException{
        System.out.print("Enter the viewer's name: ");
        return reader.readLine();
    }

    private Film promptNewFilm() throws IOException {
        System.out.print("Enter the lead actor/actress's name: ");
        String artist = reader.readLine();
        System.out.print("Enter the title: ");
        String title = reader.readLine();
        System.out.print("Enter the film IMDB URL: ");
        String filmUrl = reader.readLine();
        return new Film(artist, title, filmUrl);
    }

    private String promptArtist() throws IOException {
        System.out.println("Available artists: ");
        List<String> artists = new ArrayList<>(filmList.getArtists());
        int index = promptForIndex(artists);
        return artists.get(index);
    }

    private Film promptFilmForArtist(String artist) throws IOException {
        List<Film> films = filmList.getFilmsForArtist(artist);
        List<String> filmTitles = new ArrayList<>();
        for (Film film : films) {
            filmTitles.add(film.getTitle());
        }
        System.out.printf("Available fils for %s %n", artist);
        int index = promptForIndex(filmTitles);
        return films.get(index);
    }


    private int promptForIndex(List<String> options) throws IOException {
        int counter = 1;
        for (String option : options) {
            System.out.printf("%d.) %s %n", counter, option);
            counter++;
        }
        System.out.print("Your choice:   ");
        String optionAsString = reader.readLine();
        int choice = Integer.parseInt(optionAsString.trim());
        return choice - 1;
    }

    public void playNext() {
        FilmRequest filmRequest = filmQueue.poll();
        if (filmRequest == null) {
            System.out.println("Sorry there are no films in the queue." +
                    "Use choose from the menu to add some.");
        } else {
            Film film = filmRequest.getFilm();
            System.out.printf("%n%n%n Ready %s? Open %s to watch %s starring %s %n%n%n",
                    filmRequest.getViewerName(),
                    film.getImdbUrl(),
                    film.getTitle(),
                    film.getArtist());
        }
    }


}






