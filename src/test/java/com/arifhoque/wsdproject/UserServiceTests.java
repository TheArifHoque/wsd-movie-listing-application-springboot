package com.arifhoque.wsdproject;

import com.arifhoque.wsdproject.model.Movie;
import com.arifhoque.wsdproject.model.User;
import com.arifhoque.wsdproject.service.MovieService;
import com.arifhoque.wsdproject.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private MovieService movieService;
    private UserService userService;

    @BeforeEach
    void setUp() {
        User testUser = new User();
        testUser.setEmail("test@test.com");
        testUser.setName("test user");
        testUser.setFavoriteMovies(new ArrayList<>());

        userService = new UserService(movieService);
        userService.registerUser(testUser);
    }

    @Test
    @DisplayName("Add movies to favorite list test")
    void addMoviesToFavoriteListTest() {
        //Given
        Movie testMovie = new Movie();
        testMovie.setId(1);
        testMovie.setTitle("Fast & Furious");
        testMovie.setCast(List.of("Vin Desel", "Paul Walker", "Michelle Rodriguez"));
        testMovie.setCategory("Action");
        testMovie.setReleaseDate("2010");
        testMovie.setBudget(5.8f);

        //Stubbing
        when(movieService.getMovieById(anyInt())).thenReturn(testMovie);

        //When
        userService.addMoviesToFavourites("test@test.com",1);

        //Verify
        assertTrue(userService.getFavouriteMovies("test@test.com").contains(testMovie));
    }
}
