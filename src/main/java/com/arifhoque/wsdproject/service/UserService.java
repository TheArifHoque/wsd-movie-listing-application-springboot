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
     * Users will be able to add movies to their favorites
     *
     * @param email to identify user
     * @param movie they want to add in their favorites
     */
    public void addMoviesToFavourites(String email, Movie movie) {
        User user = users.stream().filter(u -> u.getEmail().equals(email)).findFirst().orElse(null);
        if (user != null) {
            user.getFavoriteMovies().add(movie);
        } else
            throw new RuntimeException("User not found");
    }

    /**
     * Remove from favorite movies
     *
     * @param email to identify user
     * @param id    to identify which movie from favorites should be removed
     */
    public void removeMoviesFromFavourites(String email, Integer id) {
        users.stream().filter(u -> u.getEmail().equals(email)).findFirst().ifPresent(user -> user.getFavoriteMovies().remove(id));
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
