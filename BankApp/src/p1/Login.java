package p1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet 
{

	private String accno;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{
			String custid = request.getParameter("custid");
			String pw = request.getParameter("pw");
			Model m = new Model();
			m.setCustid(custid);
			m.setPw(pw);
			boolean status = m.login();
			if(status == true)
			{
				accno = m.getAccno();
				HttpSession session = request.getSession(true);
				session.setAttribute("accno", accno);
				response.sendRedirect("/BankApp/home.jsp");
			}
			else
			{
				response.sendRedirect("/BankApp/failureLogin.html");
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
