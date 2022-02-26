package com.epf.rentmanager.ui.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/home")
public class HomeServlet extends HttpServlet{
	
private static final long serialVersionUID = 1L;

		ClientService clientService = ClientService.getInstance();
		VehicleService vehicleService = VehicleService.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
			try {
				request.setAttribute("listUsers",this.clientService.findAll().size());
				request.setAttribute("listVehicles",this.vehicleService.findAll().size());
				request.setAttribute("listReservations",this.vehicleService.findAllResa().size());
				request.getRequestDispatcher("./WEB-INF/views/home.jsp").forward(request, response);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
			
			}
	
}
