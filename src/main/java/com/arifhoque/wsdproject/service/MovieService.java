package com.arifhoque.wsdproject.service;

import com.arifhoque.wsdproject.model.Movie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * This is movieService class where all the business logic for movies are written
 *
 * @author Ariful Hoque
 */
@Service
public class MovieService {
    private final List<Movie> movies = new ArrayList<>();

    public MovieService() {
        initializeMovies();
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void removeMovie(Integer id) {
        movies.removeIf(movie -> movie.getId().equals(id));
    }

    public List<Movie> getAllMovies() {
        List<Movie> result = new ArrayList<>(movies);
        result.sort(Comparator.comparing(Movie::getTitle));
        return result;
    }

    public Movie getMovieById(Integer id) {
        for (Movie movie : movies) {
            if (movie.getId().equals(id)) {
                return movie;
            }
        }
        return null;
    }

    public List<Movie> searchMovies(String query) {
        List<Movie> results = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getTitle().toLowerCase().contains(query.toLowerCase())) {
                results.add(movie);
            } else if (movie.getCategory().toLowerCase().contains(query.toLowerCase())) {
                results.add(movie);
            } else if (movie.getCast().contains(query)) {
                results.add(movie);
            }
        }
        return results;
    }

    public void initializeMovies() {
        Movie movie1 = new Movie(1, "The Shawshank Redemption", Arrays.asList("Tim Robbins", "Morgan Freeman"), "Drama", "1994-10-14", 25000000);
        Movie movie2 = new Movie(2, "The Godfather", Arrays.asList("Marlon Brando", "Al Pacino"), "Crime", "1972-03-24", 6000000);
        Movie movie3 = new Movie(5, "The Dark Knight", Arrays.asList("Christian Bale", "Heath Ledger"), "Action", "2008-07-18", 185000000);
        Movie movie4 = new Movie(4, "Pulp Fiction", Arrays.asList("John Travolta", "Uma Thurman"), "Crime", "1994-10-14", 8000000);
        Movie movie5 = new Movie(3, "Forrest Gump", Arrays.asList("Tom Hanks", "Robin Wright"), "Drama", "1994-07-06", 55000000);
        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);
        movies.add(movie5);
    }
}
