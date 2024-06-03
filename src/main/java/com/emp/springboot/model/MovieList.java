package com.emp.springboot.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="playlist")
public class MovieList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private boolean isPublic;
    private String email;

    @ManyToMany(mappedBy = "lists", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Movie> movies = new ArrayList<>();

   
    public void addMovie(Movie movie) {
    	if (!movies.contains(movie)) {
            movies.add(movie);
            movie.getLists().add(this);
        }
    }
    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
public void removeMovie(Movie movie) {
        movies.remove(movie);
        movie.getLists().remove(this);
    }
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
}
