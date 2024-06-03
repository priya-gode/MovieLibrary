package com.emp.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.emp.springboot.model.MovieList;



@Repository
public interface MovieListRepository extends JpaRepository<MovieList, Long> {
   
    @Query("SELECT name FROM MovieList")
    List<String> getAllNames();
  
    @Query("SELECT m FROM MovieList m WHERE m.name = ?1")
    MovieList findByName( String name);
    @Query("SELECT m FROM MovieList m WHERE m.isPublic = ?1")
	List<MovieList> findByIsPublic(boolean isPublic);
    
    @Query("SELECT m FROM MovieList m WHERE m.email = :email")
	List<MovieList> findByUserEmail(String email);
    @Query("SELECT m FROM MovieList m WHERE m.isPublic = :b AND m.email NOT IN :email")
	List<MovieList> findByVisibilityAndUserEmailNot(boolean b, String email);
    @Query("SELECT m FROM MovieList m where m.email= :userEmail AND m.name=:listname")
	MovieList findByNameAndUserEmail(String listname, String userEmail);
    @Query("SELECT ml.name FROM MovieList ml WHERE ml.email = :email")
	List<String> findNamesByEmail(String email);
	
}
