package com.epf.rentmanager.ui.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;

@WebServlet("/users")
public class UserServlet extends HttpServlet{
	
	ClientService clientService = ClientService.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
		
		try {
			request.setAttribute("listUsers",this.clientService.findAll()); //Liste client
			request.getRequestDispatcher("./WEB-INF/views/users/list.jsp").forward(request, response);
		
		}catch (ServiceException e) {
			e.printStackTrace();
		}
		
	}
}
