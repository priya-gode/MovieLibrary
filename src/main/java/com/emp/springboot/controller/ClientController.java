package com.emp.springboot.controller;



import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.emp.springboot.model.Customer;
import com.emp.springboot.model.MovieList;
import com.emp.springboot.service.CustomerService;
import com.emp.springboot.service.MovieListService;
import com.emp.springboot.service.MovieService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
@RestController

@Controller
public class ClientController {
	@Autowired
	CustomerService  customerService;
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	MovieService movieService;
	@Autowired
	private MovieListService movieListService;
	public String mail=null;
	
	@GetMapping("/")
	  public ModelAndView cusregistration()
	  {
	    ModelAndView mv=new ModelAndView();
	    mv.setViewName("index");
	    return mv;
	  }
	@GetMapping("home")
	  public ModelAndView home(HttpServletRequest request)
	  {
		 ModelAndView mv = new ModelAndView();
	 		HttpSession session= request.getSession();
	 		 mv.addObject("email",session.getAttribute("email"));
	 		String eemail=(String)session.getAttribute("email");
	 		 System.out.println(eemail+'8');
	  
	    mv.setViewName("Home");
	    return mv;
	  }
	
	@PostMapping("signup")
	  public ModelAndView register(HttpServletRequest request)
	  {
		  
	    @SuppressWarnings("unused")
		String msg=null;
	    ModelAndView mv=new ModelAndView();
	    try
	    {
	      String name = request.getParameter("name");
	       
	        String email = request.getParameter("email");
	        String pwd = request.getParameter("pwd");
	        
	        
	        
	       Customer cus=new Customer();
	          cus.setName(name);
	         
	          cus.setEmail(email);
	          cus.setPassword(pwd);
	          
	          
  msg=customerService.registerCustomer(cus);
//	          
      mv.setViewName("index");
        mv.addObject("message","Registered Successfully");
	    }
	    catch(Exception e)
	    {
	      msg=e.getMessage();
	      
	      mv.setViewName("index");
	      mv.addObject("message","Couldn't Register");
	    }
	    
	    return mv;
	  }
	@PostMapping("checkcustomerlogin")
	  public ModelAndView checkcustomerlogin(HttpServletRequest request) {
	    
	    ModelAndView mv = new ModelAndView();
	    
	    String email = request.getParameter("email");
	       String pwd = request.getParameter("pwd");
	       
	      Customer  cus =  customerService.checkcustomerlogin(email, pwd);
	      
	      //very imp
	      if(cus!=null)
	      {
            HttpSession session= request.getSession();
	          mail=email;
	          session.setAttribute("email", cus.getEmail()); 
	          mv.addObject("email",session.getAttribute("email"));
	          mv.setViewName("redirect:/playlist");
	      }
	      else
	      {
	        mv.setViewName("index");
	        mv.addObject("message", "Login Failed");
	      }
	      return mv;                                                                                                               
	  }
	

	@GetMapping("search")
	public ModelAndView viewbytitle(String name,Model model,HttpServletRequest request) {
		 ModelAndView mv = new ModelAndView();
		HttpSession session= request.getSession();
		 mv.addObject("email",session.getAttribute("email"));

		return movieService.viewbytitle(name, model);
		
	}
	
	
	
	
	    @PostMapping("/save")
	    public MovieList saveMovieList(@RequestBody MovieList movieList) {
	        return movieListService.saveMovieList(movieList);
	    }

	    
	   
	    @PostMapping("/movie-lists/add")
	    public ModelAndView addMovieToList(HttpServletRequest request) {
	        ModelAndView mv = new ModelAndView();
	        HttpSession session = request.getSession();
	        String userEmail = (String) session.getAttribute("email");
	        
	        mv.addObject("email", userEmail);  // Add email to ModelAndView

	        String listName = request.getParameter("name");
	        String title = request.getParameter("movieTitle"); 

	        // Call the service method to add the movie to the list
	        if (movieListService.addMovieToList(listName, title,userEmail)) {
	            mv.setViewName("redirect:/playlist");  // Redirect to playlist page
	            mv.addObject("message", "added to playlist");
	        } else {
	            mv.setViewName("Home");  // Redirect to error page
	            mv.addObject("message", "could not add");
	        }
	        
	        return mv;
	    }

	    

	  
	
	    @PostMapping("/createlist")
	    public ModelAndView create(HttpServletRequest request)
	    
	    {
	    	
	    	String msg=null;
		    ModelAndView mv=new ModelAndView();
		    
			HttpSession session= request.getSession();
			 mv.addObject("email",session.getAttribute("email"));
			 String eemail=(String)session.getAttribute("email");
	 		 
		    try
		    {
		      String name = request.getParameter("name");
		       
		        String pub = request.getParameter("public");
		        boolean ispublic = Boolean.parseBoolean(pub);
		     
		        
		       MovieList mvl=new MovieList();
		          mvl.setName(name);
		         
		          mvl.setPublic(ispublic);
		          mvl.setEmail(eemail);
		          
	  movieListService.create(mvl);
	   String title = request.getParameter("movieTitle"); 
	  movieListService.addMovieToList(name, title,eemail);
//		          
	      mv.setViewName("redirect:/playlist");
//		        mv.addObject("message","Employee Registered Successfully");
		    }
		    catch(Exception e)
		    {
		      msg=e.getMessage();
		      
		      mv.setViewName("Home");
		      mv.addObject("message","Couldn't Create the PlayList");
		    }
		    
		    return mv;
	    }
	
	  
	    @GetMapping("/names")
	    public List<String> getNamesByLoggedInEmail(HttpServletRequest request) {
	        HttpSession session = request.getSession();
	        String email = (String) session.getAttribute("email");
	        
	        if (email != null) {
	            return movieListService.getNamesByEmail(email);
	        } else {
	            // Return an empty list or handle the case where the user is not logged in
	            return Collections.emptyList();
	        }
	    }

	    
	    @GetMapping("/playlist")
	    public ModelAndView viewPlaylists(HttpServletRequest request) {
	        ModelAndView mv = new ModelAndView("playlist");

	        HttpSession session = request.getSession();
	        String email = (String) session.getAttribute("email");

	        if (email != null) {
	            List<MovieList> playlist = movieListService.getPlaylistsByEmail(email); // Fetch playlists by user email
	            mv.addObject("playlist", playlist);
	        } else {
	            mv.setViewName("index");
	            mv.addObject("message", "Please log in first.");
	        }
	        return mv;
	    }

	    @GetMapping("/playlists")
	    public String getAllPlaylists(Model model,HttpServletRequest request) {
	    	 ModelAndView mv = new ModelAndView();
	 		HttpSession session= request.getSession();
	 		 mv.addObject("email",session.getAttribute("email"));
	 		String eemail=(String)session.getAttribute("email");
	 		 System.out.println(eemail+'3');
	        List<MovieList> playlists = movieListService.getAllPlaylists();
	        model.addAttribute("playlists", playlists);
	        return "playlists";
	    }

	    @GetMapping("/playlists/{id}")
	    public ModelAndView getMoviesByPlaylist(@PathVariable Long id,HttpServletRequest request) {
	        ModelAndView modelAndView = new ModelAndView();
	        ModelAndView mv = new ModelAndView();
			HttpSession session= request.getSession();
			 mv.addObject("email",session.getAttribute("email"));
			 String eemail=(String)session.getAttribute("email");
	 		 System.out.println(eemail+'2');
	        Optional<MovieList> optionalMovieList = movieListService.getMovieListById(id);
	        if (optionalMovieList.isPresent()) {
	            MovieList movieList = optionalMovieList.get();
	            modelAndView.addObject("movieList", movieList);
	            modelAndView.addObject("movies", movieList.getMovies());
	            modelAndView.setViewName("playlistMovies");
	        } else {
	            modelAndView.setViewName("error"); // or any error page
	        }
	        return modelAndView;
	    }
	    @PostMapping("/movie-lists/remove")
	    public ModelAndView removeMovieFromPlaylist(@RequestParam Long listId, @RequestParam Long movieId,HttpServletRequest request) {
	    	 ModelAndView mv = new ModelAndView();
	 		HttpSession session= request.getSession();
	 		 mv.addObject("email",session.getAttribute("email"));
	 		
	        movieListService.removeMovieFromList(listId, movieId);
	        ModelAndView modelAndView = new ModelAndView();
	        modelAndView.setViewName("redirect:/playlists/" + listId);
	        return modelAndView;
	    }
	   
	   

	    @GetMapping("/public-playlists")
	    public ModelAndView getPublicPlaylists(HttpServletRequest request) {
	        ModelAndView mv = new ModelAndView("publicplaylist");
	        HttpSession session = request.getSession();
	        String email = (String) session.getAttribute("email");

	        if (email != null) {
	            List<MovieList> publicPlaylists = movieListService.getMovieListsByVisibilityExceptUser(true, email);
	            mv.addObject("publiclists", publicPlaylists);
	            mv.addObject("email", email);
	        } else {
	            mv.setViewName("index");
	            mv.addObject("message", "Please log in first.");
	        }
	        return mv;
	    }
	    @GetMapping("/publicplaylists/{id}")
	    public ModelAndView getPublicMoviesByPlaylist(@PathVariable Long id,HttpServletRequest request) {
	        ModelAndView modelAndView = new ModelAndView();
	        ModelAndView mv = new ModelAndView();
			HttpSession session= request.getSession();
			 mv.addObject("email",session.getAttribute("email"));
			 String eemail=(String)session.getAttribute("email");
	 		 System.out.println(eemail+'2');
	        Optional<MovieList> optionalMovieList = movieListService.getMovieListById(id);
	        if (optionalMovieList.isPresent()) {
	            MovieList movieList = optionalMovieList.get();
	            modelAndView.addObject("movieList", movieList);
	            modelAndView.addObject("movies", movieList.getMovies());
	            modelAndView.setViewName("publicplaylistMovies");
	        } else {
	            modelAndView.setViewName("error"); // or any error page
	        }
	        return modelAndView;
	    }
	    @GetMapping("/publicplaylists")
	    public String getPublicPlaylists(Model model,HttpServletRequest request) {
	    	 ModelAndView mv = new ModelAndView();
	 		HttpSession session= request.getSession();
	 		 mv.addObject("email",session.getAttribute("email"));
	 		String eemail=(String)session.getAttribute("email");
	 		 System.out.println(eemail+'3');
	        List<MovieList> playlists = movieListService.getAllPlaylists();
	        model.addAttribute("playlists", playlists);
	        return "playlists";
	    }
	    
	    


}


