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




  
public class ViewHistoryQoL extends HttpServlet {  
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


             sql = "SELECT * FROM SCIM_DB.SCIM_QOL where USERID="+usercode+" order by  UPDATETIME desc;";      // select statement   

            statement = (Statement) connection.createStatement(); 

            ResultSet rs = statement.executeQuery(sql);    //execute statement

         JSONObject jsonObject= new JSONObject();
         JSONObject finalObject= new JSONObject();

         JSONArray jsonArray = new JSONArray();

            while (rs.next()) { // not the last row

                try {
                   jsonObject.put("Q1", rs.getString("QOL_GENERAL"));
                    jsonObject.put("Q2", rs.getString("QOL_PHISICAL"));
                    jsonObject.put("Q3", rs.getString("QOL_PSYCHOLOGY"));
                    jsonObject.put("updateTime", rs.getString("UPDATETIME"));

                    jsonArray.put(jsonObject);
                    jsonObject=new JSONObject();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    finalObject.put("result", jsonArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                
                feedback=finalObject.toString();
               

              

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
