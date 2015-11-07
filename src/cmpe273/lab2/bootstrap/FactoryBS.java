package cmpe273.lab2.bootstrap;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/default")
public class FactoryBS extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection connection = null;
	private PreparedStatement ps = null;

    public FactoryBS() {
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Date date = new Date();
			PrintWriter out = response.getWriter();
			out.println("<html><body>");
			
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cmpe273","root", "");
			
			ps = connection.prepareStatement("insert into obj_device values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, "refrigerator01");
			ps.setString(2, "General Electric");
			ps.setString(3, "GEREFMOD01");
			ps.setString(4, "GEREFSER01");
			ps.setString(5, "Refrigerator");
			ps.setString(6, "GEREFHW01");
			ps.setString(7, "GEREFSW01");
			ps.setString(8, "74.110.20.12");
			ps.setString(9, date.toString());
			ps.setInt(10, 11);
			ps.setInt(11, 52);
			ps.executeUpdate();
			
			out.println("<p> <h3> Default Values are added to Client Database </h3>");
			out.println("<br><br>Check Data in client's database - <a href='http://localhost:8089/LWM2M/deviceList'>click here</a>");
			out.println("</body></html>");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}

}
