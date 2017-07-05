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




  
public class GraphQoL extends HttpServlet {  
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


             sql = "SELECT T1.* FROM SCIM_QOL AS T1 LEFT JOIN SCIM_QOL AS T2 ON T1.userid = T2.userid AND T1.updatetime <T2.updatetime WHERE T2.id IS NULL";      // select statement   

            statement = (Statement) connection.createStatement(); 

            ResultSet rs = statement.executeQuery(sql);    //execute statement

         JSONObject jsonObject= new JSONObject();
         JSONObject finalObject= new JSONObject();

         int count=0;
         int count0_3=0;
         int count4_6=0;
         int count7_10=0;
         
         int countQOL_GENERAL0_3=0;
         int countQOL_GENERAL4_6=0;
         int countQOL_GENERAL7_10=0;

         
         int countQOL_PHISICAL0_3=0;
         int countQOL_PHISICAL4_6=0;
         int countQOL_PHISICAL7_10=0;
         
         int countQOL_PSYCHOLOGY0_3=0;
         int countQOL_PSYCHOLOGY4_6=0;
         int countQOL_PSYCHOLOGY7_10=0;




            while (rs.next()) { // not the last row
                int QOL_GENERAL = rs.getInt("QOL_GENERAL");
                int QOL_PHISICAL= rs.getInt("QOL_PHISICAL");
                int QOL_PSYCHOLOGY=rs.getInt("QOL_PSYCHOLOGY");
                //if value between 0-3
                if(QOL_GENERAL>=0&&QOL_GENERAL<=3)
                {
                    countQOL_GENERAL0_3++;
                }
                else if(QOL_GENERAL>=4&&QOL_GENERAL<=6)
                {
                    countQOL_GENERAL4_6++;
                }
                else if (QOL_GENERAL>=7&&QOL_GENERAL<=10)
                {
                    countQOL_GENERAL7_10++;
                }
                
                
                // if(QOL_PHISICAL>=0&&QOL_PHISICAL<=3)
                // {
                //     countQOL_PHISICAL0_3++;
                // }
                // else if(QOL_PHISICAL>=4&&QOL_PHISICAL<=6)
                // {
                //     countQOL_PHISICAL4_6++;
                // }
                // else if (QOL_PHISICAL>=7&&QOL_PHISICAL<=10)
                // {
                //     countQOL_PHISICAL7_10++;
                // }
                
                
                
                
                // if(QOL_PSYCHOLOGY>=0&&QOL_PSYCHOLOGY<=3)
                // {
                //     countQOL_PSYCHOLOGY0_3++;
                // }
                // else if(QOL_PSYCHOLOGY>=4&&QOL_PSYCHOLOGY<=6)
                // {
                //     countQOL_PSYCHOLOGY4_6++;
                // }
                // else if (QOL_PSYCHOLOGY>=7&&QOL_PSYCHOLOGY<=10)
                // {
                //     countQOL_PSYCHOLOGY7_10++;
                // }

             
               
                
               

              

            }  
            try
            {
            jsonObject.put("countQOL_GENERAL0_3", countQOL_GENERAL0_3+"");
            jsonObject.put("countQOL_GENERAL4_6", countQOL_GENERAL4_6+"");
            jsonObject.put("countQOL_GENERAL7_10", countQOL_GENERAL7_10+"");

            }
            catch(Exception e)
            {
                System.out.println("json  failed" + e.getMessage());  

                
            }
                feedback= jsonObject.toString();

            
            connection.close();   

              

        } catch (SQLException e) {  

            System.out.println("Query failed" + e.getMessage());  

        }  
            out.write(feedback);   
            out.flush();  
            out.close();


        
           
       
          
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
