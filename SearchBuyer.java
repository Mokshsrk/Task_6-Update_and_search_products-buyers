package servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import utils.DBConnection;

public class SearchBuyerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM buyers WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            PrintWriter out = response.getWriter();
            if (rs.next()) {
                 out.println("<div style='border:1px solid #000; padding:10px; width:300px; font-family:sans-serif;'>");
                 out.println("<h3>Buyer Found:</h3>");
                 out.println("<p><strong>ID:</strong> " + rs.getInt("id") + "</p>");
                 out.println("<p><strong>Name:</strong> " + rs.getString("name") + "</p>");
                 out.println("<p><strong>Email:</strong> " + rs.getString("email") + "</p>");
                 out.println("</div>");
            } else {
                out.println("Buyer Not Found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
