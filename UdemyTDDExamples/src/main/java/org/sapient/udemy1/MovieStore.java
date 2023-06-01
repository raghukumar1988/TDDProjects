package org.sapient.udemy1;

import org.sapient.model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MovieStore {

    // Test 1 Failed
/*    public List<Movie> findByPartialTitle(String partialTitle) {
        return null;
    }*/

    // Test 1 passed ; Test 2  failed
    /*public List<Movie> findByPartialTitle(String partialTitle) {
        return new ArrayList<>();
    }*/

    // Test 2 passed ; Test 3  failed
    /*List<Movie> movies = new ArrayList<>();

    public List<Movie> findByPartialTitle(String partialTitle) {
        return movies.stream()
                .filter(movie -> movie.getTitle().toUpperCase().contains(partialTitle.toUpperCase()))
                .collect(Collectors.toList());
    }

    public void addNewMovie(Movie movie) {
        movies.add(movie);
    }

    public List<Movie> findByDirectorName(String director) {
        return null;
    }*/

    // Test 2 passed ; Test 3  failed

    /*List<Movie> movies = new ArrayList<>();

    public List<Movie> findByPartialTitle(String partialTitle) {
        return movies.stream()
                .filter(movie -> movie.getTitle().toUpperCase().contains(partialTitle.toUpperCase()))
                .collect(Collectors.toList());
    }

    public void addNewMovie(Movie movie) {
        movies.add(movie);
    }

    public List<Movie> findByDirectorName(String director) {
       // return List.of(new Movie("Titanic", "James Cameron"));
       return movies.stream()
                .filter(movie -> movie.getDirector().equals(director))
                .collect(Collectors.toList());
    }

    public List<Movie> findByReleaseYear(int fromYear, int toYear) {
        return null;
    }*/

    // Test 4 passed ; Refactor the entire code
   /* List<Movie> movies = new ArrayList<>();

    public List<Movie> findByPartialTitle(String partialTitle) {
        return movies.stream()
                .filter(movie -> movie.getTitle().toUpperCase().contains(partialTitle.toUpperCase()))
                .collect(Collectors.toList());
    }

    public void addNewMovie(Movie movie) {
        movies.add(movie);
    }

    public List<Movie> findByDirectorName(String director) {
        // return List.of(new Movie("Titanic", "James Cameron"));
        return movies.stream()
                .filter(movie -> movie.getDirector().equals(director))
                .collect(Collectors.toList());
    }

    public List<Movie> findByReleaseYear(int fromYear, int toYear) {
        return  movies.stream()
                .filter(movie -> movie.getReleaseYear() > fromYear && movie.getReleaseYear()< toYear)
                .collect(Collectors.toList());

    }*/

    List<Movie> movies = new ArrayList<>();

    public void addNewMovie(Movie movie) {
        movies.add(movie);
    }

    public List<Movie> findByPartialTitle(String partialTitle) {
        return findBy(movie -> movie.getTitle().toUpperCase().contains(partialTitle.toUpperCase())); // Passing Predicate
    }


    public List<Movie> findByDirectorName(String director) {
        // return List.of(new Movie("Titanic", "James Cameron"));
        return findBy(movie -> movie.getDirector().equals(director));
    }

    public List<Movie> findByReleaseYear(int fromYear, int toYear) {
        return  findBy(movie -> movie.getReleaseYear() > fromYear && movie.getReleaseYear()< toYear);

    }

    private List<Movie> findBy(Predicate<Movie> predicate) {
        return movies.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

}
