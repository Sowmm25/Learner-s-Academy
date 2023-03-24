<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
    <%
Class.forName("com.mysql.jdbc.Driver");
Connection connection = 
DriverManager.getConnection("jdbc:mysql://localhost/learneracademy","root","root");
Statement st = null;
ResultSet rs = null;
st = connection.createStatement();
rs = st.executeQuery("select class_name from class"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="addTeachers" method="post">
Name<input type="text" name="name"/><br>
Teacher ID<input type="text" name="id"/><br>
Specialization<input type="text" name="spec"/><br>
<select name="class">
<%while(rs.next()){ %>
<option value="<%=rs.getString("class_name")%>"><%=rs.getString("class_name")%></option>
                        <%}%>           
                         </select> 
        
</select>
<input type="submit" value="submit"/></form>



</body>
</html>