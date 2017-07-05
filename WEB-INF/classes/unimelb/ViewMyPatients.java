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




  
public class ViewMyPatients extends HttpServlet {  
    private static Connection connection;
    private static Statement statement;

  
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  

        PrintWriter out = response.getWriter();  

            // get the user unique code for substantiate identity
        String usercode = request.getParameter("usercode");  
        String feedback= "";
         String sql="";

            connection= getConnection();
                  try {  


             sql = "SELECT * FROM SCIM_DB.SCIM_PATIENTS where PATIENT_DOCTOR='"+usercode+"';";      // select statement   

            statement = (Statement) connection.createStatement(); 

            ResultSet rs = statement.executeQuery(sql);    //execute statement

         JSONObject jsonObject= new JSONObject();
         JSONObject finalObject= new JSONObject();

         JSONArray jsonArray = new JSONArray();
         int count=0;

            while (rs.next()) { // not the last row

                try {
                   jsonObject.put(count+"", rs.getString("patient_id"));
                   
                count++;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
               
                
                feedback=count+jsonObject.toString();
               

              

            }  

            
            connection.close();   

              

        } catch (SQLException e) {  

            System.out.println("Query failed" + e.getMessage());  

        }  
            out.write(feedback);   
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
