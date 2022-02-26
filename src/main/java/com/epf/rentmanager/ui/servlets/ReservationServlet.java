package com.epf.rentmanager.ui.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/rents")
public class ReservationServlet extends HttpServlet{
	
	VehicleService resaService = VehicleService.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
		
		try {
			request.setAttribute("listReservations",this.resaService.findAllResa());
			request.getRequestDispatcher("./WEB-INF/views/rents/list.jsp").forward(request, response);
		
		}catch (ServiceException e) {
			e.printStackTrace();
		}
		
	}	
}
