package servlet;

import db.DbManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Comment;
import model.News;
import security.Aught;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/newsDetails")
public class NewsDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!Aught.isAuht(request)) {
            response.sendRedirect("/login");
            return;
        }
        try {
            // Retrieve the news ID from the request parameter
            int newsId = Integer.parseInt(request.getParameter("newsId"));

            // Assuming you have a method in DbManager to get the details of a news article
            News news = DbManager.getNewsById(newsId);

            // Assuming you have a method in DbManager to get all comments for a news article
            List<Comment> comments = DbManager.getAllCommentsFromNews(newsId);

            // Set the news and comments in request attributes
            request.setAttribute("news", news);
            request.setAttribute("comments", comments);

            // Forward to the news details JSP page
            request.getRequestDispatcher("/newsDetails.jsp").forward(request, response);
        } catch (SQLException | NumberFormatException | IOException e) {
            e.printStackTrace();
            // Handle exceptions as needed, e.g., redirect to an error page
            response.sendRedirect("/error");
        }
    }
}
