package com.mapping;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/classstudentMapping")
public class classstudentMapping extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	public void init(ServletConfig config) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			ServletContext context=config.getServletContext();
			String dburl = context.getInitParameter("dburl");
			String dbuser = context.getInitParameter("dbuser");
			String dbpass = context.getInitParameter("dbpassword");
			connection=DriverManager.getConnection(dburl,dbuser,dbpass);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generatedcatch block
			e.printStackTrace();
			}
	}  
    public classstudentMapping() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		ResultSet rs1=null;
		Statement st=null;
		Statement st2=null;
		PrintWriter out=response.getWriter();
		String id=request.getParameter("class");
		System.out.println(id);
		try {
			st=connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		//int id=Integer.parseInt(class_no);
		response.setContentType("text/html");
		String s="Class 2";
		int n = 0;
		//String sql="select class_no from class where class_name="+s;
		
		try {
			//String sql="select class_no from class where class_name='s'";
			rs=st.executeQuery("select * from class");
			while(rs.next()) {
				String no=rs.getString("class_no");
				int n1=Integer.parseInt(no);
				String name=rs.getString("class_name");
				out.println(name);
				rs1=st.executeQuery("select * from student where class_no='"+n+"';");
				String sname=rs.getString("name");
				String usn=rs.getString("usn");
				out.println(sname);
				out.println(usn);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
