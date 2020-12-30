package p1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangePwd extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		String accno = (String) session.getAttribute("accno");
		String npwd =  request.getParameter("npwd");
		Model m = new Model();
		m.setAccno(accno);
		m.setNpwd(npwd);
		boolean status = m.changePwd();
		if(status==true)
		{
			response.sendRedirect("/BankApp/successChange.html");
		}
		else
		{
			response.sendRedirect("/BankApp/failChange.html");
		}
	}

}
