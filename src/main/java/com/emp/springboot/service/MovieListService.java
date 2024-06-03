package com.emp.springboot.service;

import java.util.List;
import java.util.Optional;

import com.emp.springboot.model.MovieList;

public interface MovieListService {
	
	 public MovieList saveMovieList(MovieList movieList);
	
	  public List<String> getAllNames();
	  boolean addMovieToList(String listname, String movieTitle,String email);
	
	public void create(MovieList mov);
	public List<MovieList> getAllPlaylists();
	public Optional<MovieList> getMovieListById(Long id);
	
	void removeMovieFromList(Long listId, Long movieId);
	public List<MovieList> getMovieListsByVisibility(boolean b);

	
	public List<MovieList> getPlaylistsByEmail(String email);
	public List<MovieList> getMovieListsByVisibilityExceptUser(boolean b, String email);
	public List<String> getNamesByEmail(String email);

}
