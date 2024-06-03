package com.emp.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.emp.springboot.model.Movie;
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
	  @Query("SELECT m FROM Movie m WHERE m.title = ?1")
	 Movie findByTitle(String title);
	  @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM Movie m WHERE m.title = :title")
	  boolean existsByTitle(@Param("title") String title);
}
