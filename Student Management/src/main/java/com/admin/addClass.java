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


@WebServlet("/addClass")
public class addClass extends HttpServlet {
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
       
   
    public addClass() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		ResultSet rs1=null;
		Statement st=null;
		Statement st2=null;
		PrintWriter out=response.getWriter();
		String name=request.getParameter("name");
		String no=request.getParameter("no");
		out.println(name);
		int n1=Integer.parseInt(no);
		int n=0;
		boolean t=false;
		try {
			st=connection.createStatement();
			response.setContentType("text/html");
			rs=st.executeQuery("select * from class");
			while(rs.next()) {
				String s=rs.getString("class_no");
				n=Integer.parseInt(s);
				if(n1==n) {
					out.println("class already exists");
					t=true;
					break;
				}
			}
			if(t==false) {
				st.execute("insert into class values('"+n1+"','"+name+"')");
				out.println("class added");
				RequestDispatcher rd=request.getRequestDispatcher("/adminindex.jsp");
				rd.forward(request,response);
		
				
			}
	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();

}
	}
}
