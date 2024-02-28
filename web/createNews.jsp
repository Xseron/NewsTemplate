<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Create News</title>
  <%@include file="head.jsp"%>
</head>
<body>
<%@include file="navbar.jsp"%>
<div style="min-height: 500px;">

  <div class="row mt-3">

    <div class="col-6 mx-auto">

      <%

        String error = request.getParameter("error");

        if(error != null){

      %>

      <div class="alert alert-danger alert-dismissible fade show" role="alert">

        An error occurred while creating news!

        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>

      </div>

      <%

        }

      %>

      <form action="/createNews" method="post">
        <div class="row">
          <div class="col-12">
            <label>TITLE</label>
          </div>
        </div>
        <div class="row mt-2">
          <div class="col-12">
            <input type="text" class="form-control" required placeholder="Title" name="title">
          </div>
        </div>
        <div class="row mt-3">
          <div class="col-12">
            <label>CONTENT</label>
          </div>
        </div>
        <div class="row mt-2">
          <div class="col-12">
            <textarea class="form-control" required placeholder="Content" name="content"></textarea>
          </div>
        </div>
        <div class="row mt-3">
          <div class="col-12">
            <label>CATEGORY ID</label>
          </div>
        </div>
        <div class="row mt-2">
          <div class="col-12">
            <input type="text" class="form-control" required placeholder="Category" name="category_id">
          </div>
        </div>
        <div class="row mt-3">
          <div class="col-12">
            <button class="btn btn-success">CREATE NEWS</button>
          </div>
        </div>
      </form>
    </div>
  </div>
</div>
<%@include file="footer.jsp"%>
</body>
<%@include file="foot.jsp"%>
</html>

