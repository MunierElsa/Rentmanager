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
				int id = 0;
				for(int i = 0; i < vehicleService.findAll().size(); ++i) {
					if(id < vehicleService.findAll().get(i).getId()) {
						id = vehicleService.findAll().get(i).getId();
					} else ;
				}
				vehiculeadd.setId(id);
				request.setAttribute("CreateVehicles",this.vehicleService.create(vehiculeadd));	
				
			} catch (ServiceException e1) {
				e1.printStackTrace();
			}
			doGet(request,response);
			
	}


}
