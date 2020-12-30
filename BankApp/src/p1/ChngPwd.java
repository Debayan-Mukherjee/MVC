package p1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChngPwd extends HttpServlet 
{
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		String pw = request.getParameter("np");
		Model m = new Model();
		
		System.out.println(email);
		m.setEmail(email);
		m.setNpwd(pw);
		boolean status = m.forgotPwd();
		
		if(status == false)
			response.sendRedirect("/BankApp/fail.html");
		else
			response.sendRedirect("/BankApp/success.html");

	}
}
