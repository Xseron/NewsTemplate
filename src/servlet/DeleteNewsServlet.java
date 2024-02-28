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

@WebServlet("/deleteNews")
public class DeleteNewsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!Aught.isAdmin(request)) {
            response.sendRedirect("/");
            return;
        }
        String redirect = "/";
        try {
            int newsId = Integer.parseInt(request.getParameter("newsId"));
            // Assuming you have a method in DbManager to delete news by ID
            DbManager.deleteNews(newsId);
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            redirect = "/?error";
        }
        response.sendRedirect(redirect);
    }
}
