<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%
Class.forName("com.mysql.cj.jdbc.Driver");
Connection connection = 
DriverManager.getConnection("jdbc:mysql://localhost/learneracademy","root","root");
Statement st = null;
Statement st1=null;
Statement st2=null;
Statement st3=null;
ResultSet rs = null;
ResultSet rs1=null;
ResultSet rs2=null;
ResultSet rs3=null;
st = connection.createStatement();
st2=connection.createStatement();
st1=connection.createStatement();
st3=connection.createStatement();
rs = st.executeQuery("select * from class"); 

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
        .GFG {
            background-color: white;
            border: 2px solid black;
            color: green;
            padding: 10px 10px;
            text-align: center;
            display: inline-block;
            font-size: 20px;
            margin: 10px 30px;
            cursor: pointer;
        }
    </style>
    <a href='index.html'>
        <button class="GFG">
           Home
        </button>
    </a>
<h1 align=center style="text-align:center">Welcome to Learner's Academy</h1>
<h1 align=center style="text-align:center">Class Report</h1>
</head>
<body style='background-color:beige;'>

<table border=2 align=center style="text-align:center">
      <thead>
          <tr>
             <th>Class Number</th>
             <th>Class name</th>
             <th>Class Count</th>
             <th>Teacher Name</th>
             <th>Subject Handeled</th>
          </tr>
      </thead>
      <tbody>
<% 


		try {
			while(rs.next()){
				out.println("<tr>");
				String Countrow=""; 
			String s=rs.getString("class_no");
			String sn=rs.getString("Class_name");
            int n=Integer.parseInt(s);
			out.println("<td>"+n+"</td>");
			out.println("<td>"+sn+"</td>");
			
			rs1=st1.executeQuery("select count(name) as count from students where class_no='"+n+"';"); 
			while(rs1.next()){
			     Countrow = rs1.getString(1);
			     }  
			     out.println("<td>"+Countrow+"</td>");
			     System.out.println(Countrow);
			     
			 rs2=st2.executeQuery("select teacher_name from teacher where class_no='"+n+"';");
			 while(rs2.next()){
				 String name=rs2.getString("teacher_name");
				 out.println("<td>"+name+"</td>");
				 System.out.println(name);
				 
			 }
			
			 rs3=st3.executeQuery("select sub_name from subject where class_no='"+n+"';");
			 while(rs3.next()){
	            String name1=rs3.getString("sub_name");
				 out.println("<td>"+name1+"</td>");
				 out.println("</tr>");
				 } 
			}
			rs.close();
			rs1.close();
			rs2.close();
			rs3.close();
			st.close();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
}

	
%>
</tbody>
</table>


</body>
</html>