package com.epf.rentmanager.ui.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/cars")
public class VehiclesServlet extends HttpServlet{
	
		VehicleService vehicleService = VehicleService.getInstance();

		private static final long serialVersionUID = 1L;
		
		protected void doGet(HttpServletRequest request, HttpServletResponse
				response) throws ServletException, IOException {
			
			try {
				request.setAttribute("listVehicles",this.vehicleService.findAll());
				request.getRequestDispatcher("./WEB-INF/views/vehicles/list.jsp").forward(request, response);
			
			}catch (ServiceException e) {
				e.printStackTrace();
			}
			
		}	
		
	
}
