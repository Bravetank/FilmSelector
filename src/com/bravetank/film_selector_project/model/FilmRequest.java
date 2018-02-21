package com.bravetank.film_selector_project.model;

public class FilmRequest {
    private String viewerName;
    private Film film;

    public FilmRequest(String viewerName, Film film) {
        this.viewerName = viewerName;
        this.film = film;
    }

    public String getViewerName() {
        return viewerName;
    }

    public void setViewerName(String singerName) {
        this.viewerName = singerName;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    @Override
    public String toString() {
        return "FilmRequest{" +
                "viewerName='" + viewerName + '\'' +
                ", film=" + film +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilmRequest that = (FilmRequest) o;

        if (!viewerName.equals(that.viewerName)) return false;
        return film.equals(that.film);
    }

    @Override
    public int hashCode() {
        int result = viewerName.hashCode();
        result = 31 * result + film.hashCode();
        return result;
    }
}
