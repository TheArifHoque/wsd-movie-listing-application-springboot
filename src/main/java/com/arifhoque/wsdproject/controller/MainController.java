package com.arifhoque.wsdproject.controller;

import com.arifhoque.wsdproject.model.Movie;
import com.arifhoque.wsdproject.model.User;
import com.arifhoque.wsdproject.service.MovieService;
import com.arifhoque.wsdproject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This is main controller where endpoints are configured
 *
 * @author Ariful Hoque
 */
@RestController
public class MainController {
    private final UserService userService;
    private final MovieService movieService;

    public MainController(UserService userService, MovieService movieService) {
        this.userService = userService;
        this.movieService = movieService;
    }

    /**
     * Register user in system
     *
     * @param user information is provided
     * @return created status will be given
     */
    @PostMapping("/users/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Get all user endpoint
     *
     * @return list of users
     */
    @GetMapping("/users/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    /**
     * Get individual user details by their unique email
     *
     * @param email to identify the user
     * @return will return a user if found else not found status will be given
     */
    @GetMapping("/users/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        if (userService.getUserByEmail(email) != null) {
            return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * user can add movies to their favorites list
     *
     * @param email   to identify user
     * @param movieId to add existing movie
     * @return if operation is successful it'll return OK, if movie is not found it'll return no content and if user is not found it'll return not found
     */
    @PostMapping("/users/{email}/add-fav-movie")
    public ResponseEntity<?> addFavMovie(@PathVariable String email, @RequestParam Integer movieId) {
        if (userService.getUserByEmail(email) != null) {
            Movie movie = movieService.getMovieById(movieId);
            if (movie != null) {
                userService.addMoviesToFavourites(email, movieId);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * user can remove movies to their favorites list
     *
     * @param email   to identify user
     * @param movieId to add existing movie
     * @return if operation is successful it'll return OK, if movie is not found it'll return no content and if user is not found it'll return not found
     */
    @PostMapping("/users/{email}/rem-fav-movie")
    public ResponseEntity<?> removeFavMovie(@PathVariable String email, @RequestParam Integer movieId) {
        if (userService.getUserByEmail(email) != null) {
            Movie movie = movieService.getMovieById(movieId);
            if (movie != null) {
                userService.removeMoviesFromFavourites(email, movieId);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * List of favorite movie(s) for individual user
     *
     * @param email to identify user
     * @return list of movie(s)
     */
    @GetMapping("/users/movies/{email}")
    public ResponseEntity<List<Movie>> getFavMovieByEmail(@PathVariable String email) {
        if (userService.getUserByEmail(email) != null) {
            return new ResponseEntity<>(userService.getFavouriteMovies(email), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Endpoint to add new movie
     *
     * @param movie attributes
     * @return created status
     */
    @PostMapping("/movies/add")
    public ResponseEntity<?> addMovie(@RequestBody Movie movie) {
        movieService.addMovie(movie);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Get all movies
     *
     * @return List of movies
     */
    @GetMapping("/movies/all")
    public ResponseEntity<List<Movie>> getMovies() {
        return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);
    }

    /**
     * Search movie by its title/cast/category
     *
     * @param query for title/cast/category
     * @return list of movies if available
     */
    @GetMapping("/movies/search")
    public ResponseEntity<List<Movie>> searchMovies(@RequestParam("query") String query) {
        if (query == null || query.isEmpty()) {
            return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);
        }
        if (movieService.searchMovies(query).isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(movieService.searchMovies(query), HttpStatus.OK);
    }

    /**
     * User can search movie by its movieId
     *
     * @param movieId to get Movie
     * @return movie if found else return not found status
     */
    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") Integer movieId) {
        if (movieService.getMovieById(movieId) != null) {
            return new ResponseEntity<>(movieService.getMovieById(movieId), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
