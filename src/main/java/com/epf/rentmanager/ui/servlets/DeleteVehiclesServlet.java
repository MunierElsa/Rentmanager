package com.epf.rentmanager.ui.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/deleteVehicles")
public class DeleteVehiclesServlet extends HttpServlet{
	
	VehicleService vehicleService = VehicleService.getInstance();
	Vehicle vehiculeadd = new Vehicle();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
		
			
			request.getRequestDispatcher("./WEB-INF/views/vehicles/delete.jsp").forward(request, response);
				
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
		
			String delete_id = request.getParameter("id");
			
			try {
				
				request.setAttribute("DeleteVehicles",this.vehicleService.delete(vehicleService.findById(Short.parseShort(delete_id))));
			} catch (ServiceException e1) {
				e1.printStackTrace();
			}
			doGet(request,response);
			
	}

}
