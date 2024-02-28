package servlet;

import db.DbManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.NewsCategory;
import security.Aught;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/categories")
public class CategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!Aught.isAdmin(request)) {
            response.sendRedirect("/");
            return;
        }
        List<NewsCategory> categories = null;

        try {
            // Assuming you have a method in DbManager to get all news categories
            categories = DbManager.getAllNewsCategories();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/categoryList.jsp").forward(request, response);
    }
}
