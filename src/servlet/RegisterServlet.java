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

@WebServlet(value = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Aught.isAuht(request)){
            response.sendRedirect("/");
        } else {
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect = "/register?emailerror";
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("re_password");
        String fullName = request.getParameter("full_name");
        User checkUser = null;
        try {
            checkUser = DbManager.getUser(email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (checkUser==null){
            redirect = "/register?passworderror";
            if(password.equals(rePassword)) {
                User user = new User();
                user.setEmail(email);
                user.setPassword(password);
                user.setFullName(fullName);
                if (DbManager.addUser(user)) {
                    redirect = "/";
                }
            }
        }

        response.sendRedirect(redirect);
    }
}
