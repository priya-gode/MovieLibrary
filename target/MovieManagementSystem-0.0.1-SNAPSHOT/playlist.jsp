<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Playlists</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
.card {
  margin: 10px;
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
      <form class="d-flex" role="search" action="search" style="margin: 0 auto;">
        <input class="form-control me-2" name="name" type="search" placeholder="Search" aria-label="Search" style="width: 300px; height: 40px;">
        <button class="btn btn-outline-success" type="submit" style="height: 40px; background-color:#99e6ff">Search</button>
      </form>
       <a href="/" class="btn btn-outline-success" style="height: 40px;background-color:#99e6ff;margin-left:10px;">Logout</a>
      
    </div>
  </div>
</nav>

  <span class="blink">
        <h3 align=center style="color:red">${message}</h3>
</span>



<div class="container mt-4">
  <div class="row">
    <div class="col-md-12">
      <h3>Public Playlists</h3>
      <div class="row">
        <c:forEach var="playlist" items="${playlist}">
          <c:if test="${ playlist.isPublic()}">
            <div class="col-md-4">
              <div class="card">
                <div class="card-body">
                  <h5 class="card-title">${playlist.name}</h5>
                  <a href="/playlists/${playlist.id}" class="btn btn-primary">View Movies</a>
                </div>
              </div>
            </div>
          </c:if>
        </c:forEach>
      </div>
    </div>
    <div class="col-md-12">
      <h3>Private Playlists</h3>
      <div class="row">
        <c:forEach var="playlist" items="${playlist}">
          <c:if test="${not playlist.isPublic()}">
            <div class="col-md-4">
              <div class="card">
                <div class="card-body">
                  <h5 class="card-title">${playlist.name}</h5>
                  <a href="/playlists/${playlist.id}" class="btn btn-primary">View Movies</a>
                </div>
              </div>
            </div>
          </c:if>
        </c:forEach>
      </div>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
