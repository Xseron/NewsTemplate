package servlet;

import db.DbManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Comment;
import security.Aught;

import java.io.IOException;

@WebServlet("/addComment")
public class AddCommentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!Aught.isAuht(request)) {
            response.sendRedirect("/login");
            return;
        }

        try {
            Comment comment = new Comment();

            comment.setCommentText(request.getParameter("commentText"));
            comment.setUserId(Integer.parseInt(request.getParameter("userId")));
            comment.setNewsId(Integer.parseInt(request.getParameter("newsId")));

            DbManager.addComment(comment);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/newsDetails?newsId=" + request.getParameter("newsId"));
    }
}