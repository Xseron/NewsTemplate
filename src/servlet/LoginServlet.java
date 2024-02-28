package servlet;

import db.DbManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import security.Aught;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(Aught.isAuht(request)){
            response.sendRedirect("/");
        }else {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect = "/login?emailerror";
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = null;
        try {
            user = DbManager.getUser(email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(user!=null){
            redirect = "/login?passworderror";
            if(user.getPassword().equals(password)){
                redirect = "/profile";
                request.getSession().setAttribute("CURRENT_USER", user);
            }

        }
        response.sendRedirect(redirect);
    }
}