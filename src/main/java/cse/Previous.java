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

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Statement;

/**
 * Servlet implementation class Previous
 */
@WebServlet("/Previous")
public class Previous extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 *
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("name");
		
		
		
		RequestDispatcher dispatcher = null;
		Connection con = null;
		
		
		String userName;
		String url;
		String password;
		Statement stmt;
		
		PreparedStatement pstmt;
		con = null;
		stmt = null;
		//rs = null;
		pstmt = null;
		
		try {
			//con = null;
			userName = "root";
			password = "root";
			url = "jdbc:mysql://localhost:3306/questions";
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,userName,password);
			System.out.println("Database connection established");
			PreparedStatement pst = con.prepareStatement("select * from question where name = ?");
			pst.setString(1, name);
			
			/*
			 * ResultSet rset = pst.executeQuery(); if(rset.next())
			 * out.print("Qustion Number: "+rset.getInt("Q_id")+"\nStudent Name : "+rset.
			 * getString("name")+"\nQuestion:\n "+rset.getString("squestion")+"\n"); else
			 * out.print("\nThis Question doesn't Exists.\n");
			 */
			
			
			  //out.print("<table width=75% border=1>");
			  //out.print("<caption>Question Number : </caption>");
			  
			ResultSet rs = pst.executeQuery();
			  
			 // java.sql.ResultSetMetaData rsmd = rs.getMetaData();
				/*
				 * int totalColumn =rsmd.getColumnCount(); out.print("<tr>"); for(int i=1;
				 * i<=totalColumn; i++) { out.print("<th>"+rsmd.getColumnName(i)+"</th>"); }
				 */
			  //out.print("<tr>");
			  
			  if(rs.next()) {
				  //out.print("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td></tr>");
				  session.setAttribute("Question", rs.getString("squestion"));
				  dispatcher = request.getRequestDispatcher("Questions-show.jsp");
			  } 
			  else
			  {
				  request.setAttribute("status", "failed");
				  dispatcher = request.getRequestDispatcher("Questions-new.jsp");
			  }
			  //out.print("</table>");
			 
			
			dispatcher.forward(request, response);
			
		}catch(Exception e) {
			//out.print(e);
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
