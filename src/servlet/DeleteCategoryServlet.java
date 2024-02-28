package servlet;

import db.DbManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.News;
import security.Aught;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteCategory")
public class DeleteCategoryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!Aught.isAdmin(request)) {
            response.sendRedirect("/");
            return;
        }
        try {
            // Retrieve the category ID from the form parameter
            long categoryId = Long.parseLong(request.getParameter("categoryId"));

            // Assuming you have a method in DbManager to delete a news category by ID
            DbManager.deleteNewsCategory(categoryId);
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            // Handle exceptions as needed, e.g., redirect to an error page
        }

        response.sendRedirect("/categories");
    }
}