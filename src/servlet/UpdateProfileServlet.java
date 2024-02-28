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

@WebServlet("/updateProfile")
public class UpdateProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Aught.isAuht(request)){
            request.getRequestDispatcher("/updateProfile.jsp").forward(request, response);
        } else {
            response.sendRedirect("/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!Aught.isAuht(request)) {
            response.sendRedirect("/");
        }
        String redirect = "/profile"; // Redirect to profile page by default

        User currentUser = (User) request.getSession().getAttribute("CURRENT_USER");

        // Retrieve user data from the form parameters
        int userId = Integer.parseInt(request.getParameter("userId"));
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("rePassword");
        String fullName = request.getParameter("fullName");

        if (password.equals(rePassword)) {
            User updatedUser = new User(userId, email, password, fullName, currentUser.getRoleId());

            try {
                DbManager.updateUserProfile(updatedUser);
                request.getSession().setAttribute("CURRENT_USER", updatedUser);
            } catch (SQLException e) {
                e.printStackTrace();
                redirect = "/profile?error";
            }
        } else {
            redirect = "/profile?passwordMismatch";
        }

        response.sendRedirect(redirect);
    }
}