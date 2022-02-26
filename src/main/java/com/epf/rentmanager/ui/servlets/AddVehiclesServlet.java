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

@WebServlet("/createVehicles")
public class AddVehiclesServlet extends HttpServlet{
	
	VehicleService vehicleService = VehicleService.getInstance();
	Vehicle vehiculeadd = new Vehicle();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
		
			
			request.getRequestDispatcher("./WEB-INF/views/vehicles/create.jsp").forward(request, response);
				
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
		
			String constructeur = request.getParameter("manufacturer");
			String nb_places = request.getParameter("seats");
			
			vehiculeadd.setConstructeur(constructeur);
			vehiculeadd.setNb_places(Short.valueOf(nb_places));
			
			try {
				vehiculeadd.setId(vehicleService.findAll().get(vehicleService.findAll().size()-1).getId()+1);
				request.setAttribute("CreateVehicles",this.vehicleService.create(vehiculeadd));	
				
				request.setAttribute("DeleteVehicles",this.vehicleService.delete(vehicleService.findById(1)));
			} catch (ServiceException e1) {
				e1.printStackTrace();
			}
			doGet(request,response);
			
	}


}
