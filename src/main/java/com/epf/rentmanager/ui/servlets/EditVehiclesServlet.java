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

@WebServlet("/editVehicles")
public class EditVehiclesServlet extends HttpServlet{
	
	VehicleService vehicleService = VehicleService.getInstance();
	Vehicle vehiculeedit = new Vehicle();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
		
			
			request.getRequestDispatcher("./WEB-INF/views/vehicles/edit.jsp").forward(request, response);
				
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
		
			String id = request.getParameter("id");
			String constructeur = request.getParameter("manufacturer");
			String nb_places = request.getParameter("seats");
			
			vehiculeedit.setConstructeur(constructeur);
			vehiculeedit.setNb_places(Short.valueOf(nb_places));
			vehiculeedit.setId(Integer.valueOf(id));
			
			
			try {
				request.setAttribute("EditVehicles",this.vehicleService.edit(vehiculeedit));	
			} catch (ServiceException e1) {
				e1.printStackTrace();
			}
			doGet(request,response);
			
	}

}
