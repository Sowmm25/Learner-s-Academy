<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false" 
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
rs = st.executeQuery("select sub_name from subject"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form action="addStudent" method="post">
Student name<input type="text" name="name"/>
Student usn<input type="text" name="usn"/>
Subject  : <select name ="subject">
<%while(rs.next()){ %>
<option value="<%=rs.getString("sub_name")%>"><%=rs.getString("sub_name")%></option>
                        <%}%>           
                         </select> 
        </select>
<input type="submit" value="submit"/></form>

</form>

</body>
</html>