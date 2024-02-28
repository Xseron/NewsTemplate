package servlet;

import db.DbManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import security.Aught;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/createCategory")
public class CreateCategoryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!Aught.isAdmin(request)) {
            response.sendRedirect("/");
            return;
        }
        String redirect = "/categories";

        try {
            String categoryName = request.getParameter("categoryName");
            DbManager.createNewsCategory(categoryName);
        } catch (SQLException e) {
            e.printStackTrace();
            redirect = "/categories?error";
        }
        response.sendRedirect(redirect);
    }
}