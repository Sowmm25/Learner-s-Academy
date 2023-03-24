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
Statement st2=null;
ResultSet rs = null;
ResultSet rs1=null;
st = connection.createStatement();
st2=connection.createStatement();
rs = st.executeQuery("select class_name from class"); 
rs1=st2.executeQuery("select * from teacher order by class_no");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table border=1 align=center style="text-align:center">
      <thead>
          <tr>
             <th>ID</th>
             <th>NAME</th>
             <th>SKILL</th>
          </tr>
      </thead>
      <tbody>

<%while(rs1.next()){%>
<tr>
<td><%=rs1.getString("class_no") %></td>
<td><%=rs1.getString("teacher_name") %></td>
<td><%=rs1.getString("teacher_spec") %></td>
</tr>
</tbody>
<% }%>

        
<form action="addSubject" method="post">
Subject Name<input type="text" name="name" align="right"/><br/>
Subject Code<input type="text" name="id" align="right"/><br>


<select name="class">
<%while(rs.next()){ %>
<option value="<%=rs.getString("class_name")%>"><%=rs.getString("class_name")%></option>
                        <%}%>           
                         </select> 
        
</select>
<input type="submit" value="submit"/>
</form>

</body>
</html>