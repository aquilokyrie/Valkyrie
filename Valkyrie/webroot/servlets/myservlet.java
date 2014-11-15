package 编译用;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class MyServlet implements Servlet{
	public void init(ServletConfig config) throws ServletException{
		System.out.println("servlet init");
	}
	public void service(ServletRequest request,ServletResponse response)throws ServletException,IOException{
		System.out.println("servlet service");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("Hello World!");
		out.println("<h2>Simple Servlet</h2>");
	}
	public void destroy(){
		System.out.println("Servlet destroy");
	}
	public String getServletInfo(){
		return "最简单的servlet";
	}
	public ServletConfig getServletConfig(){
		return null;
	}
}
