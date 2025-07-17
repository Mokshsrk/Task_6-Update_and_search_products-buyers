package servlets;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import utils.DBConnection;

public class UpdateBuyerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE buyers SET name=?, email=? WHERE id=?");
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setInt(3, id);
            int i = ps.executeUpdate();

            PrintWriter out = response.getWriter();
            if (i > 0) {
                out.println("Buyer Updated Successfully.");
            } else {
                out.println("Buyer Not Found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
