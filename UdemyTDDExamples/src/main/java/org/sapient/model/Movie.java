package org.sapient.model;

public class Movie {

    private final String title;
    private final String director;
    private final int releaseYear;

    public Movie(String title, String director, int releaseYear) {
        this.title = title;
        this.director= director;
        this.releaseYear=releaseYear;

    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public int getReleaseYear() {
        return releaseYear;
    }
}
