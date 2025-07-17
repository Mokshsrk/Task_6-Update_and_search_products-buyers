package servlets;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import utils.DBConnection;

public class SearchProductServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    response.setContentType("text/html"); // ✅ Tells browser to render HTML
    PrintWriter out = response.getWriter();

    out.println("<html><body>"); // ✅ Start HTML body

    try {
        int id = Integer.parseInt(request.getParameter("id"));

        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM products WHERE id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            out.println("<h2>Product Found:</h2>");
            out.println("<p><strong>ID:</strong> " + rs.getInt("id") + "<br>");
            out.println("<strong>Name:</strong> " + rs.getString("name") + "<br>");
            out.println("<strong>Price:</strong> " + rs.getDouble("price") + "</p>");
        } else {
            out.println("<h3>Product Not Found.</h3>");
        }

        conn.close();

    } catch (Exception e) {
        out.println("<h3>Error: " + e.getMessage() + "</h3>");
        e.printStackTrace(out); // ✅ Print stack trace to browser
    }

    out.println("</body></html>"); // ✅ Close HTML
}

}
