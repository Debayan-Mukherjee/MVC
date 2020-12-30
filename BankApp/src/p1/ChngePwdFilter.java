package p1;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
public class ChngePwdFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String temp = request.getParameter("np");
		String temp2 = request.getParameter("cnp");
		if(temp.equals(temp2)==false)
		{
			((HttpServletResponse) response).sendRedirect("/BankApp/errorChngPwd.jsp");
		}
		else {
			chain.doFilter(request, response);
		}
	}

}
