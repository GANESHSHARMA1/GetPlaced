package cse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.xdevapi.Statement;

/**
 * Servlet implementation class Add
 */
@WebServlet("/Add")
public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("Name");
		String squestion = request.getParameter("Question");
		/* String name = String.valueOf(session.getAttribute("name")); */
	
		RequestDispatcher dispatcher = null;
		Connection con = null;
		
		if(name == null||name.equals(""))
		{
			request.setAttribute("status", "invalidname");
			dispatcher = request.getRequestDispatcher("Add.jsp");
			dispatcher.forward(request,response);
		}
		
		if(squestion == null||squestion.equals(""))
		{
			request.setAttribute("status", "invalidQuestions");
			dispatcher = request.getRequestDispatcher("Add.jsp");
			dispatcher.forward(request,response);
		}
		String userName;
		String url;
		String password;
		Statement stmt;
		ResultSet rs;
		PreparedStatement pstmt;
		con = null;
		stmt = null;
		rs = null;
		pstmt = null;
		
		try {
			con = null;
			userName = "root";
			password = "root";
			url = "jdbc:mysql://localhost:3306/questions";
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,userName,password);
			System.out.println("Database connection established");
			PreparedStatement pst = con.prepareStatement("insert into question(name,squestion) values(?,?) ");
			pst.setString(1, name);
			pst.setString(2, squestion);
			
			int rowCount = pst.executeUpdate();
			
			if(rowCount > 0)
			{
				dispatcher = request.getRequestDispatcher("Questions-new.jsp");
				request.setAttribute("status", "success");
				//out.print("Your Question Has been added Successfully. Thanks For your Contribution.");
			}
			else
			{
				dispatcher = request.getRequestDispatcher("Add.jsp");
				request.setAttribute("status", "failed");
				//out.print("Your Feedback Has been sent Successfully. Thanks For your feedback.");
			}
			
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
