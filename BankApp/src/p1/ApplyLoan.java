package p1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ApplyLoan extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		String accno = (String) session.getAttribute("accno");
		
		Model m = new Model();
		m.setAccno(accno);
		boolean status = m.applyLoan();
		if(status == true)
		{
			session.setAttribute("name", m.getName());
			session.setAttribute("email", m.getEmail());
			response.sendRedirect("/BankApp/successLoan.jsp");
		}
		else
		{
			response.sendRedirect("/BankApp/failLoan.html");
		}
		
	}

}
