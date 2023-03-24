package com.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class addTeachers
 */
@WebServlet("/addTeachers")
public class addTeachers extends HttpServlet {
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
    public addTeachers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		ResultSet rs1=null;

		Statement st=null;
		Statement st2=null;
		PrintWriter out=response.getWriter();
		String name=request.getParameter("name");
		String id=request.getParameter("id");
		String spec=request.getParameter("spec");
		String class_name=request.getParameter("class");
		System.out.println(spec);
		int n=0;
		int n1=0;
		System.out.println(n1);
		boolean t=false;
		try {
			st=connection.createStatement();
			st2=connection.createStatement();
			response.setContentType("text/html");
			rs=st.executeQuery("select class_no from class where class_name='"+class_name+"';");
			
			if(rs.next()) {
			  String ne=rs.getString("class_no");
			  n=Integer.parseInt(ne);
			  System.out.println(n);
			  out.println(n);
		}
			rs1=st.executeQuery("select class_no from teacher");
			while(rs1.next()) {
			String nee=rs1.getString("class_no");
			n1=Integer.parseInt(nee);
			System.out.println(n1);
			if(n==n1) {
				out.println("class is already allocated");
				t=true;
				break;
			}
		}
		if(t==false) {
			st.execute("insert into teacher values('"+n+"','"+name+"','"+id+"','"+spec+"')");
			RequestDispatcher rd=request.getRequestDispatcher("/adminindex.jsp");
			rd.forward(request,response);
			out.println("Student added");
			
		}
			} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
