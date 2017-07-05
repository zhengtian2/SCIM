package unimelb;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;




  
public class Login extends HttpServlet {  
    private static Connection connection;
    private static Statement statement;

  
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  

        PrintWriter out = response.getWriter();  

            // get the user unique code for substantiate identity
        String usercode = request.getParameter("usercode");  
          String feedback= "";

            connection= getConnection();
                  try {  

            String sql = "SELECT USER_TYPE FROM SCIM_DB.SCIM_USER where user_id=\""+usercode+"\";";      // select statement   

            statement = (Statement) connection.createStatement(); 

            ResultSet rs = statement.executeQuery(sql);    //execute statement


            while (rs.next()) { // not the last row

          

               feedback=rs.getString("user_type"); 

                // String userid = rs.getString("user_id");  

                // String user_type = rs.getString("user_type"); 

                // System.out.println(username + "  " + password);   

              

            }  

            
            connection.close();   

              

        } catch (SQLException e) {  

            System.out.println("Query failed" + e.getMessage());  

        }  
            out.write(feedback);   
            out.flush();  
            out.close();
       // userName = new String(userName.getBytes("ISO8859-1"),"UTF-8");  


        
            //响应登陆成功的信息  
           
       
          
    }  
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        this.doGet(request, response);  
    }

    public static Connection getConnection()

{

Connection c = null;


String mysqlurl="jdbc:mysql://localhost:3306/SCIM_DB";

String username="root";

String password="123";


try{

Class.forName("com.mysql.jdbc.Driver");

c = DriverManager.getConnection(mysqlurl,username,password);

System.out.println("Connect Success");


}

catch(ClassNotFoundException cnfex){

System.out.println("Failed to load JDBC driver.");

cnfex.printStackTrace();

System.exit(1);

}catch(SQLException sqlex){

System.err.println("Unable to connect");

sqlex.printStackTrace();

}

return c;

}  
}
