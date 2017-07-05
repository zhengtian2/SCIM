package unimelb;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;
  
public class SaveQoLToDB extends HttpServlet {  
  
    private static Connection connection;
    private static Statement statement;
  
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        
        PrintWriter out = response.getWriter();  
        String jsonString = request.getParameter("json");  
        String feedback= "";
        String sql="";

 try {
     connection= getConnection();
     JSONObject json= new JSONObject(jsonString);
     //feedback=json.get("Q1").toString();     
        sql ="Insert into  SCIM_DB.SCIM_QOL values(id,'";
        sql+=json.getString("user_id")+"','";
        sql+=json.getString("Q1")+"','";
        sql+=json.getString("Q2")+"','";
        sql+=json.getString("Q3")+"',Now())";
     

     
     try {
         
        statement = (Statement) connection.createStatement();
        statement.executeUpdate(sql);
        
    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } 
     
              
        
    } catch (JSONException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }  

             
            out.write("Success");   
            out.flush();  
            out.close();  


            //response.getOutputStream().print("Server  coming");
       
          
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
