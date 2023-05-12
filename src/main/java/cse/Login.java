package cse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.xdevapi.Statement;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String semail=request.getParameter("username");
		String spwd=request.getParameter("password");
		HttpSession session = request.getSession();		
		RequestDispatcher dispatcher = null;
		
		if(semail == null||semail.equals(""))
		{
			request.setAttribute("status", "invalidEmail");
			dispatcher = request.getRequestDispatcher("Login.jsp");
			dispatcher.forward(request,response);
		}
		
		if(spwd == null||spwd.equals(""))
		{
			request.setAttribute("status", "invalidPassword");
			dispatcher = request.getRequestDispatcher("Login.jsp");
			dispatcher.forward(request,response);
		}
		Connection con = null;
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
			url = "jdbc:mysql://localhost:3306/login";
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,userName,password);
			System.out.println("Database connection established");
			PreparedStatement pst = con.prepareStatement("select * from student where semail = ? and spwd = ?");
			pst.setString(1, semail);
			pst.setString(2,spwd);
			
			rs= pst.executeQuery();
			if(rs.next())
			{
				session.setAttribute("name", rs.getString("sname"));
				dispatcher=request.getRequestDispatcher("index-new.jsp");
			}
			else
			{
				request.setAttribute("status", "failed");
				dispatcher = request.getRequestDispatcher("Login.jsp");
			}
			dispatcher.forward(request,response);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
