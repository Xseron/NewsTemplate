<%@ page import="model.User" %><%

  User currentUser = (User) session.getAttribute("CURRENT_USER");

%>



  <nav class="navbar navbar-expand-lg navbar-dark" style="background-color: rgba(35,67,105,0.85);">
    <div class="container-fluid">
      <a class="navbar-brand" href="/">NEWS</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
              data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
              aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <%
            if(currentUser!=null){
          %>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button"
               data-bs-toggle="dropdown" aria-expanded="false">
              <%=currentUser.getFullName()%>
            </a>
            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
              <li><a class="dropdown-item" href="/profile">Profile</a></li>
              <li><a class="dropdown-item" href="/updateProfile">Settings</a></li>
              <li><hr class="dropdown-divider"></li>
              <li><a class="dropdown-item" href="/logout">Logout</a></li>
            </ul>
          </li>
            <%
              if(currentUser.getRoleId().equals("2")){
            %>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                Admin Functions
              </a>
              <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="/createNews">Create News</a></li>
                <li><a class="dropdown-item" href="/categories">Categories</a></li>
              </ul>

            </li>
            <%
              }
            %>
          <%
          }else{
          %>
          <li class="nav-item">
            <a class="nav-link" href="/">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/register">Register</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/login">Login</a>
          </li>
          <%
            }
          %>
        </ul>
      </div>
    </div>
  </nav>
