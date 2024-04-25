package com.arifhoque.wsdproject.service;

import com.arifhoque.wsdproject.model.Movie;
import com.arifhoque.wsdproject.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * This is userService class where all the business logic for users are written
 *
 * @author Ariful Hoque
 */
@Service
public class UserService {
    private final List<User> users = new ArrayList<>();
    private final MovieService movieService;

    public UserService(MovieService movieService) {
        this.movieService = movieService;
    }

    /**
     * To create user
     *
     * @param user will be taken as object and create a user
     */
    public void registerUser(User user) {
        users.add(user);
    }

    /**
     * To get all user
     *
     * @return list of users
     */
    public List<User> getAllUsers() {
        return users;
    }

    /**
     * It'll return user corresponding to the unique email
     *
     * @param email to identify the user
     * @return the identified user
     */
    public User getUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Users will be able to add movies to their favorites
     *
     * @param email   to identify user
     * @param movieId they want to add in their favorites
     */
    public void addMoviesToFavourites(String email, Integer movieId) {
        Movie movie = movieService.getMovieById(movieId);
        User user = users.stream().filter(u -> u.getEmail().equals(email)).findFirst().orElse(null);
        if (user != null) {
            user.getFavoriteMovies().add(movie);
        } else
            throw new RuntimeException("User not found");
    }

    /**
     * Remove from favorite movies
     *
     * @param email   to identify user
     * @param movieId to identify which movie from favorites should be removed
     */
    public void removeMoviesFromFavourites(String email, Integer movieId) {
        Movie movie = movieService.getMovieById(movieId);
        users.stream().filter(u -> u.getEmail().equals(email)).findFirst().ifPresent(user -> user.getFavoriteMovies().remove(movie));
    }

    /**
     * Get a list of favorite movies
     *
     * @param email to identify user
     * @return list of favorite movies
     */
    public List<Movie> getFavouriteMovies(String email) {
        User user = users.stream().filter(u -> u.getEmail().equals(email)).findFirst().orElse(null);
        if (user != null) {
            return user.getFavoriteMovies();
        }
        throw new RuntimeException("User not found");
    }
}
