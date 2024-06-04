<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Sofia">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<meta charset="UTF-8">
<title>Home Page</title>
<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
.poster-image {
  width: 100%;
  height: auto;
  max-height: 500px; /* Adjust the maximum height as needed */
}
.column {
  float: left;
  width: 23%;
  padding: 10px;
  height: 537px;
  position: relative;
}
.row:after {
  content: "";
  display: table;
  clear: both;
}
.column h2, .column p, .column h3, .column h4 {
  width: 100%;
  text-align: left;
  margin: 0;
  padding: 5px 0;
}
.add-icon {
  position: absolute;
  right: 10px;
  cursor: pointer;
  color: black;
  z-index: 1; /* Ensure the icon is above other content */
  font-size: 24px; /* Increase icon size */
}
.blink {
  font-size: 20px;
  font-weight: bold;
  color: red;
  text-align: center;
  animation: blink-animation 1s steps(5, start) infinite;
  -webkit-animation: blink-animation 1s steps(5, start) infinite;
}

@keyframes blink-animation {
  to {
    visibility: hidden;
  }
}
</style>
</head>
<body style="background-image:url('https://png.pngtree.com/thumb_back/fh260/background/20191113/pngtree-blue-film-technology-film-background-image_322042.jpg');justify-content: center;background-repeat: no-repeat; background-size:100%;margin: 0; padding: 0;">

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
      <form class="d-flex" role="search" action="search" style="margin: 0 auto;">
        <input class="form-control me-2" name="name" type="search" placeholder="Search" aria-label="Search" style="width: 300px; height: 40px;">
        <button class="btn btn-outline-success" type="submit" style="height: 40px; background-color:#99e6ff">Search</button>
      </form>
      <a href="/" class="btn btn-outline-success" style="height: 40px;background-color:#99e6ff;margin-left:10px;">Logout</a>
    </div>
  </div>
</nav>
<div class="row" style="justify-content: center; margin-top: 100px;">
  <div class="column" style="background-color:white; position: relative;justify-content: center;">
    <img src="${poster}" alt="Poster Image" class="poster-image">
  </div>
  <div class="column" style="background-color:white; ">
  <br/>
  <br/>
    <h4>Title</h4>
    <h5>${title}</h5>
    <h4>Actors</h4>
    <h5>${actors}</h5>
    <h4>Director</h4>
    <h5>${director}</h5>
    <h4>Genre</h4>
    <h5>${genre}</h5>
    <h4>Language</h4>
    <h5>${language}</h5>
    <span class="blink">
      <h3 align=center style="color:red">${message}</h3>
    </span>
    <!-- Icon triggering modal -->
    <i class="fas fa-plus-circle add-icon" id="addToListIcon"></i>
    <!-- Hidden inputs to pass movie information to the modal -->
    <input type="hidden" id="movieTitle" value="${title}">
    <input type="hidden" id="movieActors" value="${actors}">
    <input type="hidden" id="movieDirector" value="${director}">
    <input type="hidden" id="movieGenre" value="${genre}">
    <input type="hidden" id="movieLanguage" value="${language}">
  </div>
</div>

<!-- Modal -->
<div class="modal fade" id="addToListModal" tabindex="-1" aria-labelledby="addToListModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="addToListModalLabel">Add Movie to List</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <!-- Initial Form -->
        <form id="initial-form" method="post" action="/movie-lists/add">
          <!-- Select list -->
          <div class="mb-3">
            <label for="listSelect" class="form-label">Select List</label>
            <select id="nameDropdown" name="name" class="form-select">
              <option name="name">Loading...</option>
            </select>
          </div>
          <!-- Buttons -->
          <div class="mb-3">
            <button type="submit" class="btn btn-primary">Add to List</button>
            <button type="button" class="btn btn-secondary" id="createNewListButton">Create New List</button>
          </div>
          <input type="hidden" name="movieTitle" value="${title}">
        </form>
        <!-- New List Form -->
        <form id="new-list-form" style="display: none;" method="post" action="createlist">
          <!-- Create new list -->
          <div class="mb-3">
            <label for="newListName" class="form-label">New List Name</label>
            <input type="text" class="form-control" id="newListName" name="name" placeholder="New List Name">
          </div>
          <!-- Public/Private Toggle -->
          <div class="mb-3">
            <label for="listVisibility" class="form-label">Visibility</label>
            <select class="form-select" id="listVisibility" name="public">
              <option value="true">Public</option>
              <option value="false">Private</option>
            </select>
          </div>
          <input type="hidden" name="movieTitle" value="${title}">
          <button type="submit" class="btn btn-primary">Create and Add to List</button>
        </form>
      </div>
    </div>
  </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
document.getElementById('addToListIcon').addEventListener('click', function() {
  // Trigger the modal to show
  var myModal = new bootstrap.Modal(document.getElementById('addToListModal'));
  myModal.show();
});

$(document).ready(function(){
    $.ajax({
        url: "/names", // Adjust the URL to match your endpoint
        type: "GET",
        success: function(data) {
            var dropdown = $('#nameDropdown');
            dropdown.empty();
            // Assuming data is an array of strings
            data.forEach(function(name) {
                dropdown.append($('<option></option>').attr('value', name).text(name));
            });
        },
        error: function(xhr, status, error) {
            console.error("Error fetching names:", error);
            $('#nameDropdown').html('<option>Error fetching names</option>');
        }
    });
});

document.getElementById('createNewListButton').addEventListener('click', function() {
  document.getElementById('initial-form').style.display = 'none';
  document.getElementById('new-list-form').style.display = 'block';
});
</script>
</body>
</html>
