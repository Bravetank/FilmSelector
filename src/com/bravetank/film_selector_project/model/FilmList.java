package com.bravetank.film_selector_project.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class FilmList {

    //Will hold a List of Film objects. Initialised in constructor.
    private List<Film> films;

    public FilmList() {
        films = new ArrayList<Film>();
    }

    public void exportTo(String fileName) {
        try (
                FileOutputStream fos = new FileOutputStream(fileName);
                PrintWriter writer = new PrintWriter(fos);
        ) {
            for (Film film : films) {
                writer.printf("%s|%s|%s%n",
                        film.getArtist(),
                        film.getTitle(),
                        film.getImdbUrl());
            }
        } catch (IOException ioe) {
            System.out.printf("Problem saving %s %n", fileName);
            ioe.printStackTrace();
        }
    }

    public void importFrom(String fileName) {
        try (
                FileInputStream fis = new FileInputStream(fileName);
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] args = line.split("\\|");
                addFilm(new Film(args[0], args[1], args[2]));
            }
        } catch (IOException ioe) {
            System.out.printf("Problem loading %s %n", fileName);
            ioe.printStackTrace();
        }
    }


    public void addFilm(Film film) {
        films.add(film);
    }

    public int getFilmCount() {
        return films.size();
    }

    //FIXME: This should be cached
    private Map<String, List<Film>> byArtist() {
        Map<String, List<Film>> byArtist = new TreeMap<>();
        for (Film film : films) {
            List<Film> artistFilms = byArtist.get(film.getArtist());
            if (artistFilms == null) {
                artistFilms = new ArrayList<>();
                byArtist.put(film.getArtist(), artistFilms);
            }
            artistFilms.add(film);
        }
        return byArtist;
    }

    public Set<String> getArtists() {
        return byArtist().keySet();
    }

    public List<Film> getFilmsForArtist(String artistName) {
        List<Film> films = byArtist().get(artistName);
        films.sort(new Comparator<Film>() {

            @Override
            public int compare(Film film1, Film film2) {
                if (film1.equals(film2)) {
                    return 0;
                }
                return film1.title.compareTo(film2.title);
            }
        });
        return films;
    }
}

