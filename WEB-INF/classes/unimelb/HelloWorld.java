package unimelb;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HelloWorld extends HttpServlet {

public void doGet(HttpServletRequest request, HttpServletResponse response)
throws IOException, ServletException
{
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println("<html>");
    out.println("<body>");
    out.println("<head>");
    out.println("<title>First Example</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>Hello World! This is a page from servelet </h1>");
    out.println("</body>");
    out.println("</html>");
}
}