package com.arifhoque.wsdproject.service;

import com.arifhoque.wsdproject.model.Movie;
import com.arifhoque.wsdproject.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final List<User> users;

    public UserService(List<User> users) {
        this.users = users;
    }

    public void registerUser(User user) {
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public void addMoviesToFavourites(String email, Movie movie) {
            User user = users.stream().filter(u -> u.getEmail().equals(email)).findFirst().orElse(null);
            if (user != null) {
                user.getFavoriteMovies().add(movie);
            } else
                throw new RuntimeException("User not found");
    }
}
