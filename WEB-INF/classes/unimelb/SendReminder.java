package unimelb;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.*;




  
public class SendReminder extends HttpServlet {  
    private static Connection connection;
    private static Statement statement;

  
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  

        PrintWriter out = response.getWriter();  

            // get the user unique code for substantiate identity
        String jsonString = request.getParameter("json");  
        String feedback= "";
         String sql="";
        

            connection= getConnection();
                  try {  
                      JSONObject jsonObject= new JSONObject(jsonString);
     sql="Insert into  PullService  values (id,'"+jsonObject.getString("FromDoc")+"','"+jsonObject.getString("ToPatient")+"','YOUR NEW MSG!','Y');";
     statement = (Statement) connection.createStatement(); 
     statement.executeUpdate(sql);    //execute statement



  

           
            connection.close();   

              

        } catch (Exception e) {  

            e.printStackTrace();
        }  
            out.write(sql);   
            out.flush();  
            out.close();
       // userName = new String(userName.getBytes("ISO8859-1"),"UTF-8");  


        
           
       
          
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
