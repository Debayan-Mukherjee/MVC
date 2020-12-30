package p1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Transfer extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		String accno = (String) session.getAttribute("accno");
		String saccno = request.getParameter("saccno");
		String amt = request.getParameter("amt");
		
		Model m = new Model();
		m.setSaccno(saccno);
		m.setAmt(amt);
		m.setAccno(accno);
		boolean status = m.transfer();
		if(status==true)
			response.sendRedirect("/BankApp/successTransfer.html");
		else
			response.sendRedirect("/BankApp/failTransfer.html");
		
	}

}
