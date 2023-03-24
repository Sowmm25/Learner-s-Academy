package com.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username =request.getParameter("username");
		String password=request.getParameter("password");
		response.setContentType("text/html");
	   System.out.println(username);
	   System.out.println(password);
		PrintWriter out=response.getWriter();
		if(!isvalidinput(username,false) || !isvalidinput(password,false)) {
			out.println("<h1>please Enter Valid Input</h1>");
			return;
			}
		if(username.equals("root")&&password.equals("root")) {
			out.println("<p> user  found </p>");
			RequestDispatcher rd=request.getRequestDispatcher("adminindex.jsp");
			rd.include(request, response);
		}
		else {
			out.println("<p> user not found</p>");
		}
		
	}
	private boolean isvalidinput(String inputvalue,boolean checkforno) {
		if(inputvalue==null || inputvalue.isBlank() || inputvalue.isEmpty()) {
			return false;
			}else if(checkforno) {
				try {
					Integer.parseInt(inputvalue);
					return true;
					}catch(Exception e) {
						return false;
						}
				}else {
					return true;
				}
		}


}
