package com.arifhoque.wsdproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private String email;
    private String name;
    private List<Movie> favoriteMovies;
}
