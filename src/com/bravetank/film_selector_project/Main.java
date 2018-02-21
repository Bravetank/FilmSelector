package com.bravetank.film_selector_project;

import com.bravetank.film_selector_project.model.FilmList;

public class Main {

    public static void main(String[] args) {
        FilmList filmList = new FilmList();
        filmList.importFrom("films.txt");
        FilmSelectorMachine machine = new FilmSelectorMachine(filmList);
        machine.run();
        System.out.println("Saving list.....");
        filmList.exportTo("films.txt");
    }
}
