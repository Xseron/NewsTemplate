package servlet;

import db.DbManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.News;
import model.User;
import security.Aught;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(value = "/")
public class IndexServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(Aught.isAuht(request)) {
            List<News> news = null;
            try {
                news = DbManager.getAllNews();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            request.setAttribute("news_list", news);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }else{
            response.sendRedirect("/login");
        }
    }
}