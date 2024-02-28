<%@ page import="model.News" %>
<%@ page import="model.Comment" %>
<%@ page import="java.util.List" %>
<%@ page import="db.DbManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>News Details</title>
    <%@include file="head.jsp"%>
</head>
<body>
<%@include file="navbar.jsp"%>
<%
    News news = (News) request.getAttribute("news");
    List<Comment> comments = (List<Comment>) request.getAttribute("comments");
%>
<div style="min-height: 500px;">
    <div class="row mt-3">
        <div class="col-8 mx-auto">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title"><%= news.getTitle() %></h5>
                    <p class="card-text"><%= news.getContent() %></p>
                </div>
            </div>
        </div>
    </div>
    <div class="row mt-3">
        <div class="col-8 mx-auto">
            <h5 class="mb-3">Comments</h5>
            <ul class="list-group">
                <% for(Comment comment : comments) {
                    User commentSendet = DbManager.getCommentSender(comment.getUserId());
                %>
                <li class="list-group-item"><%=commentSendet.getFullName()%>: <%= comment.getCommentText() %></li>
                <% } %>

                <% if(comments.isEmpty()) {%>
                    Empty
                <% } %>
            </ul>
        </div>
    </div>

    <div class="row mt-3">
        <div class="col-8 mx-auto">
            <h5 class="mb-3">Add Comment</h5>
            <form action="/addComment" method="post">
                <input type="hidden" name="userId" value="<%= currentUser.getId() %>">
                <input type="hidden" name="newsId" value="<%= news.getId() %>">
                <div class="mb-3">
                    <label for="commentText" class="form-label">Comment:</label>
                    <textarea class="form-control" id="commentText" name="commentText" required></textarea>
                </div>
                <button type="submit" class="btn btn-primary">Add Comment</button>
            </form>
        </div>
    </div>
</div>
<%@include file="footer.jsp"%>
</body>
<%@include file="foot.jsp"%>
</html>