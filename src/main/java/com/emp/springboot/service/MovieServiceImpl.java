package com.emp.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.emp.springboot.model.Movie;
import com.emp.springboot.model.MovieList;
import com.emp.springboot.repository.MovieListRepository;
import com.emp.springboot.repository.MovieRepository;


@Service
public class MovieServiceImpl implements MovieService{
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	MovieRepository movieRepository ;
	@Autowired
	MovieListRepository movieListRepository;

	@Override
	public ModelAndView viewbytitle(String name, Model model) {

	    String apiurl = "http://www.omdbapi.com/?i=tt3896198&apikey=647dd9e4&t=" + name;

	    // Check if the movie with the given title already exists in the database
	    if (!movieRepository.existsByTitle(name)) {
	        // If the movie does not exist, fetch it from the API
	        Movie obj = restTemplate.getForObject(apiurl, Movie.class);

	        // Extract movie details
	        String poster = obj.getPoster();
	        String title = obj.getTitle();
	        String actors = obj.getActors();
	        String director = obj.getDirector();
	        String genre = obj.getGenre();
	        String language = obj.getLanguage();
	        String year = obj.getYear();

	        // Add movie details to the model
	        model.addAttribute("poster", poster);
	        model.addAttribute("title", title);
	        model.addAttribute("year", year);
	        model.addAttribute("actors", actors);
	        model.addAttribute("director", director);
	        model.addAttribute("genre", genre);
	        model.addAttribute("language", language);

	        // Save the movie object to the database
	        movieRepository.save(obj);
	    } else {
	        // If the movie already exists, fetch it from the database
	        Movie obj = movieRepository.findByTitle(name);

	        // Extract movie details
	        String poster = obj.getPoster();
	        String title = obj.getTitle();
	        String actors = obj.getActors();
	        String director = obj.getDirector();
	        String genre = obj.getGenre();
	        String language = obj.getLanguage();
	        String year = obj.getYear();

	        // Add movie details to the model
	        model.addAttribute("poster", poster);
	        model.addAttribute("title", title);
	        model.addAttribute("year", year);
	        model.addAttribute("actors", actors);
	        model.addAttribute("director", director);
	        model.addAttribute("genre", genre);
	        model.addAttribute("language", language);
	    }

	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("Home");
	    return mv;
	}



	
    
	

	

}
