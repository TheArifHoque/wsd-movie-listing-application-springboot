package com.arifhoque.wsdproject.service;

import com.arifhoque.wsdproject.model.Movie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class MovieService {
    private final List<Movie> movies;

    public MovieService(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getMovies() {
        List<Movie> result = new ArrayList<>(movies);
        result.sort(Comparator.comparing(Movie::getTitle));
        return result;
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
}
