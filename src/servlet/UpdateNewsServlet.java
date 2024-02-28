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

@WebServlet("/updateNews")
public class UpdateNewsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!Aught.isAdmin(request)) {
            response.sendRedirect("/");
            return;
        }
        request.getRequestDispatcher("/updateNews.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!Aught.isAdmin(request)) {
            response.sendRedirect("/");
            return;
        }
        String redirect = "/";

        try {
            int newsId = Integer.parseInt(request.getParameter("newsId"));
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            int categoryId = Integer.parseInt(request.getParameter("category_id"));

            // Assuming you have a method in DbManager to update news
            News updatedNews = new News();
            updatedNews.setId(newsId);
            updatedNews.setTitle(title);
            updatedNews.setContent(content);
            updatedNews.setCategoryId(categoryId);
            DbManager.updateNews(updatedNews);
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            redirect = "/newsList?error";
        }

        response.sendRedirect(redirect);
    }
}