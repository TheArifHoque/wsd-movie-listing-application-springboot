package com.arifhoque.wsdproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This is a movie model class. A movie will have these attributes
 *
 * @author Ariful Hoque
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Movie {
    private Integer id;
    private String title;
    private List<String> cast;
    private String category;
    private String releaseDate;
    private float budget;
}
