<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Playlist Movies</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .card {
            height: 100%;
        }

        .card-img-top {
            height: 400px; /* Adjust the height as needed */
            object-fit: cover; /* Ensure the image covers the entire space */
        }
    </style>

</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid" style="background-image:url('https://png.pngtree.com/thumb_back/fh260/background/20210916/pngtree-film-simple-light-effect-blue-gradient-background-image_901053.jpg'); height:60px; display: flex; justify-content: center; align-items: center; padding:0px; background-repeat: no-repeat; background-size:100%">
    <a class="navbar-brand" style="color:white">Moviez</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/playlist" style="color:white">Playlists</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/public-playlists" style="color:white">Public Playlists</a>
        </li>
      </ul>
    
       <a href="/" class="btn btn-outline-success" style="height: 40px;background-color:#99e6ff;margin-left:10px;">Logout</a>
      
    </div>
  </div>
</nav>
<div class="container mt-5">
    <h1>${movieList.name}</h1>
    <div class="row row-cols-1 row-cols-md-4 g-4">
        <c:forEach var="movie" items="${movies}">
            <div class="col-3">
                <div class="card h-100">
                    <img src="${movie.poster}" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">${movie.title}</h5>
                        <p class="card-text">Director: ${movie.director}</p>
                        <p class="card-text">Genre: ${movie.genre}</p>
                        <p class="card-text">Language: ${movie.language}</p>
  <form action="/movie-lists/remove" method="POST"> <!-- Change method to DELETE -->
    <input type="hidden" name="_method" value="DELETE">
    <input type="hidden" name="listId" value="${movieList.id}">
    <input type="hidden" name="movieId" value="${movie.id}">
    <button type="submit" class="btn btn-danger">Remove</button>
</form>




                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>
