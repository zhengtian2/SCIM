package unimelb;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ServletTest extends HttpServlet {

	

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
                
response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println("<html>");
    out.println("<body>");
    out.println("<head>");
    out.println("<title>First Example</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>Hello World! This is a page from post </h1>");
    out.println("</body>");
    out.println("</html>");
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

				doGet(request,response);

	}

}
