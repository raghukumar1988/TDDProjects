package org.sapient.udemy1;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sapient.model.Movie;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

class MovieStoreTest {
    MovieStore movieStore = new MovieStore();
    private final Movie titanic = new Movie("Titanic", "James Cameron",2017);
    private final Movie harryPotter = new Movie("Harry Potter", "Steven",2018);
    private final Movie harryPotter2 = new Movie("Harry Potter 2", "John Smith",2023);

    @BeforeEach
     void setUp() {

        movieStore.addNewMovie(titanic);
        movieStore.addNewMovie(harryPotter);
        movieStore.addNewMovie(harryPotter2);
    }

    @Test
    @DisplayName("Test 1")
    public void returnsNoResultsWhenNoTitlesPartiallyMatchSearch() {
        MovieStore movieStore = new MovieStore();
        List<Movie> movies = movieStore.findByPartialTitle("abc");
        assertEquals(0, movies.size());
    }

    /*@Test
    @DisplayName("Test 2 ")
    public void findMoviesWhenTitlesArePartiallyMatchCaseInsensitive() {
        MovieStore movieStore = new MovieStore();
        Movie titanic = new Movie("Titanic", "James Cameron");
        Movie harryPotter = new Movie("Harry Potter", "James Cameron");
        Movie harryPotter2 = new Movie("Harry Potter 2", "James Cameron");

        movieStore.addNewMovie(titanic);
        movieStore.addNewMovie(harryPotter);
        movieStore.addNewMovie(harryPotter2);
        List<Movie> movies = movieStore.findByPartialTitle("harry");
        assertEquals(2, movies.size());
        assertThat(movies, containsInAnyOrder(harryPotter2,harryPotter));
    }

    @Test
    @DisplayName("Test 3")
    void findsMoviesWhenDirectorExactlyMatches() {
        MovieStore movieStore = new MovieStore();
        Movie titanic = new Movie("Titanic","James Cameron");
        Movie harryPotter = new Movie("Harry Potter", "Steven");
        Movie harryPotter2 = new Movie("Harry Potter 2", "Smith Harry");
        movieStore.addNewMovie(titanic);
        movieStore.addNewMovie(harryPotter);
        movieStore.addNewMovie(harryPotter2);
        List<Movie> movies = movieStore.findByDirectorName("James Cameron");
        assertEquals(1, movies.size());
        assertEquals("Titanic",movies.get(0).getTitle());
        assertThat(movies,Matchers.contains(titanic)); // This will  not work if I return hardcoded list

    }*/

    // Above method were refactored by adding setup() method and instance variables

    @Test
    @DisplayName("Test 2 ")
    public void findMoviesWhenTitlesArePartiallyMatchCaseInsensitive() {
        List<Movie> movies = movieStore.findByPartialTitle("harry");
        assertEquals(2, movies.size());
        assertThat(movies, Matchers.containsInAnyOrder(harryPotter2, harryPotter));
    }

    @Test
    @DisplayName("Test 3")
    void findsMoviesWhenDirectorExactlyMatches() {
        List<Movie> movies = movieStore.findByDirectorName("James Cameron");
        assertEquals(1, movies.size());
        assertEquals("Titanic",movies.get(0).getTitle());
        assertThat(movies,Matchers.contains(titanic)); // This will  not work if I return hardcoded list
    }

    @Test
    @DisplayName("Test 4")
    void findsMoviesReleasedBetweenGivenYears() {
        List<Movie> movies = movieStore.findByReleaseYear(2016,2022);
        assertEquals(2, movies.size());
        assertEquals("Titanic",movies.get(0).getTitle());
        assertEquals("Harry Potter",movies.get(1).getTitle());
        assertThat(movies,Matchers.contains(titanic,harryPotter));
    }

}