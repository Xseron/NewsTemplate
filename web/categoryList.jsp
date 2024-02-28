<%@ page import="model.NewsCategory" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>News Categories</title>
    <%@include file="head.jsp"%>
</head>
<body>
<%
    List<NewsCategory> categories = (List<NewsCategory>) request.getAttribute("categories");
%>
<%@include file="navbar.jsp"%>
<div style="min-height: 500px;">
    <div class="row mt-3">
        <div class="col-12">
            <h3 class="text-center">News Categories</h3>
        </div>
    </div>
    <div class="row mt-3">
        <div class="col-6 mx-auto">
            <ul class="list-group">
                <% for(NewsCategory category : categories) { %>
                <li class="list-group-item">
                    id - <%= category.getId() %> name - <%= category.getName() %>
                    <form action="/deleteCategory" method="post" class="float-end">
                        <input type="hidden" name="categoryId" value="<%= category.getId() %>">
                        <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                    </form>
                </li>
                <% } %>
            </ul>
            <div class="dropdown">
                <button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false" data-bs-auto-close="outside">
                    create Category
                </button>
                <form class="dropdown-menu p-4" action="/createCategory" method="post">
                    <div class="mb-3">
                        <label for="categoryName" class="form-label">Category Name</label>
                        <input type="text" class="form-control" name="categoryName" id="categoryName" placeholder="Sport">
                    </div>
                    <button type="submit" class="btn btn-primary">Save</button>
                </form>
            </div>
        </div>
    </div>
</div>

<%@include file="footer.jsp"%>
</body>
<%@include file="foot.jsp"%>
</html>
