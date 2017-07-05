package unimelb;
import java.sql.*;



public class DBConnect {


private static Connection connection;

private static Statement statement;

private static PreparedStatement prestatment;


public static void main(String[] args)

{







}

// insert
public static void insert()

{

connection = getConnection();

try{


String sql = "insert into SCIM_DB.TEST(username,password) values(\"testname\",\"testpassword\")";

statement = (Statement)connection.createStatement();

int count = statement.executeUpdate(sql);//execute statement

System.out.println("Insert " + count +" records into departments table");

connection.close();


}catch(SQLException e){

System.out.println("insert record failed" + e.getMessage());

}


}

// query
public static void query() {  

        

        connection = getConnection(); //connect to database

        

        try {  

            String sql = "SELECT * FROM SCIM_DB.SCIM_USER";     // select statement   

            statement = (Statement) connection.createStatement(); 

            ResultSet rs = statement.executeQuery(sql);    //execute statement

            System.out.println("Query results are：");  

            while (rs.next()) { // not the last row

          

               

                String username = rs.getString("username");  

                String password = rs.getString("password"); 

                System.out.println(username + "  " + password);   

              

            }  

            

            

            
            connection.close();   

              

        } catch (SQLException e) {  

            System.out.println("Query failed" + e.getMessage());  

        }  

    }  

//delete
public static void delete() {  

 

        connection = getConnection();  

        try {  

            String sql = "delete from SCIM_DB.TEST  where username = 'testname'";

            statement = (Statement) connection.createStatement();  

             

            int count = statement.executeUpdate(sql);

           

             

            System.out.println("delete " + count + " record(s) from departments");  

             

            connection.close();  

             

        } catch (SQLException e) {  

            System.out.println("Delete record failed" + e.getMessage());  

        }  

         

    }  


  public static void update() {  

        connection = getConnection(); 

        try {  

            String sql = "update departments set dept_name ='New_Lab' where dept_no  = 'd010'";

             

            statement = (Statement) connection.createStatement();   

             

            int count = statement.executeUpdate(sql);

             

            System.out.println("update " + count + " records in departments");        

           

            connection.close();   

             

        } catch (SQLException e) {  

            System.out.println("Update failed " + e.getMessage());  

        }  

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



