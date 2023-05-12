package cse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.xdevapi.Statement;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String sname = request.getParameter("name");
		String semail = request.getParameter("email");
		String spwd = request.getParameter("pass");
		String srpwd = request.getParameter("re_pass");
		String smobile = request.getParameter("contact");
		int otpvalue = 0;
		RequestDispatcher dispatcher = null;
		Connection con = null;
		if(sname == null||sname.equals(""))
		{
			request.setAttribute("status", "invalidName");
			dispatcher = request.getRequestDispatcher("register.jsp");
			dispatcher.forward(request,response);
		}
		
		if(semail == null||semail.equals(""))
		{
			request.setAttribute("status", "invalidEmail");
			dispatcher = request.getRequestDispatcher("register.jsp");
			dispatcher.forward(request,response);
		}
		
		if(spwd == null||spwd.equals(""))
		{
			request.setAttribute("status", "invalidPassword");
			dispatcher = request.getRequestDispatcher("register.jsp");
			dispatcher.forward(request,response);
		}else if(!spwd.equals(srpwd)) {
			request.setAttribute("status", "invalidConfirm");
			dispatcher = request.getRequestDispatcher("register.jsp");
			dispatcher.forward(request,response);
		}
		
		if(smobile == null||smobile.equals(""))
		{
			request.setAttribute("status", "invalidMobile");
			dispatcher = request.getRequestDispatcher("register.jsp");
			dispatcher.forward(request,response);
		}else if(smobile.length()!=10)
		{
			request.setAttribute("status", "invalidMobileLength");
			dispatcher = request.getRequestDispatcher("register.jsp");
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
			url = "jdbc:mysql://localhost:3306/login";
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,userName,password);
			System.out.println("Database connection established");
			PreparedStatement pst = con.prepareStatement("insert into student(sname,spwd,semail,smobile) values(?,?,?,?) ");
			pst.setString(1, sname);
			pst.setString(2, spwd);
			pst.setString(3, semail);
			pst.setString(4, smobile);
			
			int rowCount = pst.executeUpdate();
			HttpSession mySession = request.getSession();
			if(rowCount > 0)
			{
				
				if(semail!=null || !semail.equals("")) {
					// sending otp
					Random rand = new Random();
					otpvalue = rand.nextInt(1255650);

					String to = semail;// change accordingly
					// Get the session object
					Properties props = new Properties();
					props.put("mail.smtp.host", "smtp.gmail.com");
					props.put("mail.smtp.socketFactory.port", "465");
					props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
					props.put("mail.smtp.auth", "true");
					props.put("mail.smtp.port", "465");
					Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication("ganeshkithana@gmail.com", "dqlardbmgtfpoydf");
						}
					});
					// compose message
					try {
						MimeMessage message = new MimeMessage(session);
						message.setFrom(new InternetAddress(semail));// change accordingly
						message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
						message.setSubject("GetPlaced Verification");
						message.setText("Welcome Dear User!"
								+ "You have Registered to GetPlaced."
								+ "Enter OTP to Verify: " + otpvalue);
						// send message
						Transport.send(message);
						System.out.println("message sent successfully");
					}

					catch (MessagingException e) {
						throw new RuntimeException(e);
					}
					dispatcher = request.getRequestDispatcher("Verification.jsp");
					request.setAttribute("message","OTP is sent to your email id");
					//request.setAttribute("connection", con);
					mySession.setAttribute("otp",otpvalue); 
					mySession.setAttribute("email",semail); 
					dispatcher.forward(request, response);
					//request.setAttribute("status", "success");
				}
				else {
					PreparedStatement pstt = con.prepareStatement("delete from student where sname = ? and semail = ?");
					pstt.setString(1, sname);
					pstt.setString(2, semail);
				}
				dispatcher = request.getRequestDispatcher("Login.jsp");
				request.setAttribute("status", "success");
				
			}
			else
			{
				dispatcher = request.getRequestDispatcher("register.jsp");
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
