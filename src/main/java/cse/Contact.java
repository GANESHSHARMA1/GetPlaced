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

import com.mysql.cj.xdevapi.Statement;

/**
 * Servlet implementation class Contact
 */
@WebServlet("/Contact")
public class Contact extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String sname = request.getParameter("Name");
		String semail = request.getParameter("Gmail-id");
		String smobile = request.getParameter("Phone");
		String sdesignation = request.getParameter("Designation");
		String smessage = request.getParameter("Message");
		
		RequestDispatcher dispatcher = null;
		Connection con = null;
		if(sname == null||sname.equals(""))
		{
			request.setAttribute("status", "invalidName");
			dispatcher = request.getRequestDispatcher("Contact-new.jsp");
			dispatcher.forward(request,response);
		}
		
		if(semail == null||semail.equals(""))
		{
			request.setAttribute("status", "invalidEmail");
			dispatcher = request.getRequestDispatcher("Contact-new.jsp");
			dispatcher.forward(request,response);
		}
		
		if(smobile == null||smobile.equals(""))
		{
			request.setAttribute("status", "invalidMobile");
			dispatcher = request.getRequestDispatcher("Contact-new.jsp");
			dispatcher.forward(request,response);
		}else if(smobile.length()!=10)
		{
			request.setAttribute("status", "invalidMobileLength");
			dispatcher = request.getRequestDispatcher("Contact-new.jsp");
			dispatcher.forward(request,response);
		}
		if(smessage == null||smessage.equals(""))
		{
			request.setAttribute("status", "invalidmessage");
			dispatcher = request.getRequestDispatcher("Contact-new.jsp");
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
			url = "jdbc:mysql://localhost:3306/contact";
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,userName,password);
			System.out.println("Database connection established");
			PreparedStatement pst = con.prepareStatement("insert into feedback(sname,semail,smobile,sdesignation,smessage) values(?,?,?,?,?) ");
			pst.setString(1, sname);
			pst.setString(2, semail);
			pst.setString(3, smobile);
			pst.setString(4, sdesignation);
			pst.setString(5, smessage);
			
			int rowCount = pst.executeUpdate();
			
			if(rowCount > 0)
			{
				/* dispatcher = request.getRequestDispatcher("Contact-new.html"); */
				request.setAttribute("status", "success");
				out.print("Your Feedback Has been sent Successfully. Thanks For your feedback.");
			}
			else
			{
				dispatcher = request.getRequestDispatcher("Contact-new.jsp");
				request.setAttribute("status", "failed");
				
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
