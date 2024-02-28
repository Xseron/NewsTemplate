<%--
  Created by IntelliJ IDEA.
  User: David
  Date: 27.02.2024
  Time: 23:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <%@include file="head.jsp"%>
</head>
<body>
<%@include file="navbar.jsp"%>
<div style="min-height: 500px;">
    <div class="row mt-3">
        <div class="col-12">
            <h3 class="text-center">PROFILE PAGE OF <%=(currentUser!=null?currentUser.getFullName():"")%></h3>
        </div>
    </div>
    <div class="row mt-3">
        <div class="col-12 text-center">
            <a href="/updateProfile" class="btn btn-primary">Update Profile</a>
        </div>
    </div>
</div>
<%@include file="footer.jsp"%>
</body>
<%@include file="foot.jsp"%>
</html>
