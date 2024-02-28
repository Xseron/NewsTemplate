package security;

import jakarta.servlet.http.HttpServletRequest;
import model.User;

public class Aught {
    public static boolean isAuht(HttpServletRequest request){
        User currentUser = (User) request.getSession().getAttribute("CURRENT_USER");
        return currentUser!=null;
    }

    public static boolean isAdmin(HttpServletRequest request){
        User currentUser = (User) request.getSession().getAttribute("CURRENT_USER");
        if (currentUser==null){
            return false;
        }
        if (currentUser.getRoleId().equals("2")){
            return true;
        }

        return false;
    }
}
