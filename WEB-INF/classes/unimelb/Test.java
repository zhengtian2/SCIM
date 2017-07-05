package unimelb;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
  
public class Test extends HttpServlet {  
  

  
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        //获取请求的参数值  
        PrintWriter out = response.getWriter();  
        String userName = request.getParameter("param");  
        String psd =request.getParameter("psd");
          
        System.out.println("在没有转码之前的"+userName);  
        //GET方式的请求乱码处理  
       // userName = new String(userName.getBytes("ISO8859-1"),"UTF-8");  

    String feedback= "this is from server==>"+userName+"&"+psd  ;

        
            //响应登陆成功的信息  
            out.write(feedback);   
            out.flush();  
            out.close();  


            //response.getOutputStream().print("Server  coming");
       
          
    }  
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        this.doGet(request, response);  
    }  
}
