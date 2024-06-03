package com.emp.springboot.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="Movies")
public class Movie {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	@JsonProperty("Title")
    private String title;
   @JsonProperty("Year")
    private String year;
    @JsonProperty("Genre")
    private String genre;
    @JsonProperty("Director")
    private String director;
    @JsonProperty("Actors")
    private String actors;
   
    @JsonProperty("Language")
    private String language;
   @JsonProperty("Poster")
    private String poster;
    // Add other fields as needed
   @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
   @JoinTable(
       name = "playlist_movie",
       joinColumns = @JoinColumn(name = "movie_id"),
       inverseJoinColumns = @JoinColumn(name = "playlist_id")
   )
   private List<MovieList> lists = new ArrayList<>();
   public void addMovieList(MovieList movieList) {
       lists.add(movieList);
       movieList.getMovies().add(this);
   }
    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

   

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public List<MovieList> getLists() {
		return lists;
	}
	public void setLists(List<MovieList> lists) {
		this.lists = lists;
	}

    // Add other getters and setters as needed
}
