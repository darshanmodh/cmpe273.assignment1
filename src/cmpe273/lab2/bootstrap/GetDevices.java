package cmpe273.lab2.bootstrap;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetDevices
 */
@WebServlet("/deviceList")
public class GetDevices extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetDevices() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {

			List list = new ArrayList();

			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cmpe273", "root", "");

			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from obj_device");
			PrintWriter out = response.getWriter();
			out.println("<html><body><h1>Device List</h1>");
			
			while (resultSet.next()) {
				
				out.println("<br>End Point Name: " + resultSet.getString(1));
				out.println("<br>Manufacturer: " + resultSet.getString(2));
				out.println("<br>Model Number: " + resultSet.getString(3));
				out.println("<br>Serial Number: " + resultSet.getString(4));
				out.println("<br>Device Type: " + resultSet.getString(5));
				out.println("<br>Hardware Version: " + resultSet.getString(6));
				out.println("<br>Software Version: " + resultSet.getString(7));
				out.println("<br>IP address: " + resultSet.getString(8));
				out.println("<br>Current Time: " + resultSet.getString(9));
				out.println("<br>Current Temperature: " + resultSet.getString(10));
				out.println("<br>Current Humidity: " + resultSet.getString(11));
				out.println("<br>============================");
			}
			
			out.println("</body></html>");

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				resultSet.close();
				connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}

	}

}
