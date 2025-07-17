package servlets;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import utils.DBConnection;

public class UpdateProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE products SET name=?, price=? WHERE id=?");
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setInt(3, id);
            int i = ps.executeUpdate();
            
            PrintWriter out = response.getWriter();
            if (i > 0) {
                out.println("Product Updated Successfully.");
            } else {
                out.println("Product Not Found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
