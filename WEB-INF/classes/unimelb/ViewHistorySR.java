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




  
public class ViewHistorySR extends HttpServlet {  
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


             sql = "SELECT * FROM SCIM_DB.SCIM_REPORT where scim_id="+usercode+" order by updateTime desc;";      // select statement   

            statement = (Statement) connection.createStatement(); 

            ResultSet rs = statement.executeQuery(sql);    //execute statement

         JSONObject jsonObject= new JSONObject();
         JSONObject finalObject= new JSONObject();

         JSONArray jsonArray = new JSONArray();

            while (rs.next()) { // not the last row

                try {
                   jsonObject.put("Q1", rs.getString("feeding"));
                    jsonObject.put("Q2A", rs.getString("upper_body_bathing"));
                    jsonObject.put("Q2B", rs.getString("lower_body_bathing"));
                    jsonObject.put("Q3A", rs.getString("upper_body_dressing"));
                    jsonObject.put("Q3B", rs.getString("lower_body_dressing"));
                    jsonObject.put("Q4", rs.getString("grooming"));
                    jsonObject.put("Q5", rs.getString("respiration"));
                    jsonObject.put("Q6", rs.getString("bladder_management"));
                    jsonObject.put("Q7", rs.getString("bowel_management"));
                    jsonObject.put("Q8", rs.getString("toileting"));
                    jsonObject.put("Q9", rs.getString("mobility_bed"));
                    jsonObject.put("Q10", rs.getString("bed_to_chair_transfer"));
                    jsonObject.put("Q11", rs.getString("toilet_transfer"));
                    jsonObject.put("Q12", rs.getString("mobility_indoors"));
                    jsonObject.put("Q13", rs.getString("mobility_moderate_distances"));
                    jsonObject.put("Q14", rs.getString("mobility_outdoors"));
                    jsonObject.put("Q15", rs.getString("stairs"));
                    jsonObject.put("Q16", rs.getString("chair_car_transfer"));
                    jsonObject.put("Q17", rs.getString("ground_chair_transfer"));
                    jsonObject.put("updateTime", rs.getString("updateTime"));

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
