package com.arifhoque.wsdproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Movie {
    private String title;
    private List<String> cast;
    private String category;
    private String releaseDate;
    private double budget;
}
