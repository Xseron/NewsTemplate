<%--
  Created by IntelliJ IDEA.
  User: David
  Date: 28.02.2024
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Profile</title>
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
                An error occurred while updating the profile!
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <%
                }
            %>
            <%
                String passwordMismatch = request.getParameter("passwordMismatch");
                if(passwordMismatch != null){
            %>
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                Passwords do not match!
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <%
                }
            %>
            <form action="/updateProfile" method="post">
                <input type="hidden" name="userId" value="<%= currentUser.getId() %>">

                <div class="row">
                    <div class="col-12">
                        <label>EMAIL</label>
                    </div>
                </div>
                <div class="row mt-2">
                    <div class="col-12">
                        <input type="email" class="form-control" required placeholder="Email" name="email" value="<%= currentUser.getEmail() %>">
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-12">
                        <label>PASSWORD</label>
                    </div>
                </div>
                <div class="row mt-2">
                    <div class="col-12">
                        <input type="password" class="form-control" required placeholder="Password" name="password">
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-12">
                        <label>REPEAT PASSWORD</label>
                    </div>
                </div>
                <div class="row mt-2">
                    <div class="col-12">
                        <input type="password" class="form-control" required placeholder="Repeat password" name="rePassword">
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-12">
                        <label>FULL NAME</label>
                    </div>
                </div>
                <div class="row mt-2">
                    <div class="col-12">
                        <input type="text" class="form-control" required placeholder="Full name" name="fullName" value="<%= currentUser.getFullName() %>">
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-12">
                        <button class="btn btn-success">UPDATE PROFILE</button>
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
