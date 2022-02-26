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

@WebServlet("/createReservations")
public class AddReservationsServlet extends HttpServlet{
	
	VehicleService vehicleService = VehicleService.getInstance();
	ClientService clientService = ClientService.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
		
			try {
				request.setAttribute("listVehicles",this.vehicleService.findAll());
				request.setAttribute("listUsers",this.clientService.findAll());
				request.getRequestDispatcher("./WEB-INF/views/rents/create.jsp").forward(request, response);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				
	}

}
