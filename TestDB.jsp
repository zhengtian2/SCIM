<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="java.sql.*" %>
<HTML>
<BODY>
<% 

String driver="com.mysql.jdbc.Driver";
String url="jdbc:mysql://localhost/SCIM_DB"; 

String userid="admin"; // 用户 
String passwd="scim"; // 密码 


try{
Class.forName(driver);
}
catch(Exception e){
out.println(" Cannot "+driver+" driver !");
e.printStackTrace();
}


try {
Connection con=DriverManager.getConnection(url,userid,passwd);
if(!con.isClosed())
out.println(" success !");
con.close();
}
catch(SQLException SQLe){
out.println(" fail !");
}

%>
</BODY>
</HTML>