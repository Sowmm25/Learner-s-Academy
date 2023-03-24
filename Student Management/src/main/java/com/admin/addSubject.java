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


@WebServlet("/addSubject")
public class addSubject extends HttpServlet {
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
    public addSubject() {
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
		String class_name=request.getParameter("class");
		int n=0;
		int n1=0;
		boolean t=false;
		try {
			st=connection.createStatement();
			st2=connection.createStatement();
			response.setContentType("text/html");
			rs=st.executeQuery("select class_no from class where class_name='"+class_name+"';");
			
			if(rs.next()) {
			  String ne=rs.getString("class_no");
			  n=Integer.parseInt(ne);
			  out.println(n);
		}
			rs1=st.executeQuery("select class_no from subject");
			while(rs1.next()) {
			String ne=rs1.getString("class_no");
			n1=Integer.parseInt(ne);
			if(n==n1) {
				out.println("class is already allocated with subject");
				t=true;
				break;
			}
		}
		if(t==false) {
			st.execute("insert into subject values('"+n+"','"+name+"','"+id+"')");
			out.println("Subject added");
			RequestDispatcher rd=request.getRequestDispatcher("/adminindex.jsp");
			rd.forward(request,response);
			
			
		}
			} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	}


