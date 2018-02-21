package com.bravetank.film_selector_project.model;

public class Film {

    protected String leadActor;
    protected String title;
    protected String imdbUrl;

    public Film(String leadActor, String title, String imdbUrl) {
        this.leadActor = leadActor;
        this.title = title;
        this.imdbUrl = imdbUrl;
    }

    public String getArtist() {
        return leadActor;
    }

    public String getTitle() {
        return title;
    }

    public String getImdbUrl() {
        return imdbUrl;
    }

    @Override
    public String toString() {
        return String.format("%s starring %s", title, leadActor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Film film = (Film) o;

        if (leadActor != null ? !leadActor.equals(film.leadActor) : film.leadActor != null) return false;
        if (title != null ? !title.equals(film.title) : film.title != null) return false;
        return imdbUrl != null ? imdbUrl.equals(film.imdbUrl) : film.imdbUrl == null;
    }

    @Override
    public int hashCode() {
        int result = leadActor != null ? leadActor.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (imdbUrl != null ? imdbUrl.hashCode() : 0);
        return result;
    }
}
