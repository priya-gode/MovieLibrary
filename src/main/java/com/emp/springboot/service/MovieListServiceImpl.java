package com.emp.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emp.springboot.model.Movie;
import com.emp.springboot.model.MovieList;
import com.emp.springboot.repository.MovieListRepository;
import com.emp.springboot.repository.MovieRepository;
@Service
public class MovieListServiceImpl implements MovieListService {
	@Autowired
	MovieListRepository movieListRepository;
	@Autowired
	MovieRepository movieRepository;


	@Override
	 public MovieList saveMovieList(MovieList movieList) {
        return movieListRepository.save(movieList);
    }

	
	@Override
	public List<String> getAllNames() {
		return movieListRepository.getAllNames();
	}
	@Transactional
	@Override
	public boolean addMovieToList(String listname, String movieTitle, String userEmail) {
	    try {
	       
	        MovieList movieList = movieListRepository.findByNameAndUserEmail(listname, userEmail);

	    
	        if (movieList != null) {
	           
	            Movie movie = movieRepository.findByTitle(movieTitle);

	            
	            if (movie != null) {
	                // Check if the movie is not already in the playlist
	                if (!movieList.getMovies().contains(movie)) {
	                    // Add the movie to the movie list
	                    movieList.addMovie(movie);

	                    // Save the updated movie list (movie will also be updated due to cascade)
	                    movieListRepository.save(movieList);

	                    return true; // Movie successfully added to the list
	                } else {
	                    // Movie is already in the playlist
	                    return false;
	                }
	            } else {
	                // Movie with the provided title does not exist
	                return false;
	            }
	        } else {
	            // Playlist does not exist or is not associated with the logged-in user's email
	            return false;
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); // Print the stack trace for debugging
	        return false;
	    }
	}



	
	public void create(MovieList mlist) {
		 movieListRepository.save(mlist);
		
	}
	@Override
	public List<MovieList> getAllPlaylists() {
	    return movieListRepository.findAll(); // Assuming your repository has this method
	}
	@Override
	 public Optional<MovieList> getMovieListById(Long id) {
        return movieListRepository.findById(id);
    }
	@Override
	public void removeMovieFromList(Long listId, Long movieId) {
	    // Retrieve the movie list
	    Optional<MovieList> optionalMovieList = movieListRepository.findById(listId);
	    if (optionalMovieList.isPresent()) {
	        MovieList movieList = optionalMovieList.get();

	        // Retrieve the movie by its ID
	        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
	        if (optionalMovie.isPresent()) {
	            Movie movie = optionalMovie.get();

	            // Remove the movie from the list
	            movieList.removeMovie(movie);
	            movieListRepository.save(movieList);
	        } else {
	            throw new IllegalArgumentException("Movie not found with ID: " + movieId);
	        }
	    } else {
	        throw new IllegalArgumentException("Movie list not found with ID: " + listId);
	    }
	}
	@Override
	public List<MovieList> getMovieListsByVisibility(boolean isPublic) {
		 return movieListRepository.findByIsPublic(isPublic);
	}
	
	@Override
	public List<MovieList> getPlaylistsByEmail(String email) {
		 return movieListRepository.findByUserEmail(email);
	}
	@Override
	public List<MovieList> getMovieListsByVisibilityExceptUser(boolean b, String email) {
		 return movieListRepository.findByVisibilityAndUserEmailNot(b, email);
	}
	@Override
	public List<String> getNamesByEmail(String email) {
		return movieListRepository.findNamesByEmail(email);
	}

	
	
}
