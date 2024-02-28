<%@ page import="model.News" %>
<%@ page import="java.util.List" %>
<%@ page import="db.DbManager" %>
<%@ page import="security.Aught" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>News</title>
  <%@include file="head.jsp"%>
</head>
<body>
<%@include file="navbar.jsp"%>
<h1 class="text-center">News</h1>
<div class="row">
  <%
    List<News> news_list = (List<News>) request.getAttribute("news_list");
    for (News news : news_list) {
      String shortContent = news.getContent();
      if (shortContent.length() > 10){
        shortContent = shortContent.substring(0, 200);
      }
  %>
  <div class="col-md-6">
    <div class="card">
      <div class="card-body text-center">
        <h4 class="card-title"><%=news.getTitle()%></h4>
        <h5 class="card-title">Category: <%=DbManager.getCategoryNameId(news.getCategoryId())%></h5>
        <p class="card-text"><%=shortContent%></p>
        <a href="/newsDetails?newsId=<%=news.getId()%>" class="btn btn-primary btn-block">Detaisl</a>
        <% if(Aught.isAdmin(request)) {%>
        <form action="/deleteNews?newsId=<%=news.getId()%>" method="post">
        <button type="submit" class="btn btn-danger btn-block">Delete</button>
        </form>
        <% } %>
      </div>
    </div>
  </div>
  <% } %>
</div>
<%@include file="footer.jsp"%>
</body>
<%@include file="foot.jsp"%>
</html>
