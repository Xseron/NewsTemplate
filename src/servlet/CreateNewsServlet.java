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

@WebServlet("/createNews")
public class CreateNewsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Aught.isAdmin(request)){
            request.getRequestDispatcher("/createNews.jsp").forward(request, response);
        } else {
            response.sendRedirect("/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!Aught.isAdmin(request)) {
            response.sendRedirect("/");
            return;
        }
        String redirect = "/createNews?error";

        // Retrieve news details from the form parameters
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        int categoryId = Integer.parseInt(request.getParameter("category_id")); // Assuming you have a category_id parameter

        // You may want to perform additional validation on the input data

        // Create a News object with the provided details
        News news = new News();
        news.setTitle(title);
        news.setContent(content);
        news.setCategoryId(categoryId);

        // Add the news entry to the database
        DbManager.addNews(news);

        // Redirect to a success page or back to the form with a success message
        response.sendRedirect("/newsList");
    }
}
