package com.emp.springboot.service;


import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.emp.springboot.model.MovieList;


public interface MovieService {
	public ModelAndView viewbytitle(String name,Model model);

	 
	 

	
}
