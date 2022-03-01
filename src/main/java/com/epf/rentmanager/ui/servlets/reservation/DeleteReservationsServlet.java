package com.epf.rentmanager.ui.servlets.reservation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/deleteReservations")
public class DeleteReservationsServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	Reservation resadelete = new Reservation();

	@Autowired
	private VehicleService vehicleService;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
		
			
			request.getRequestDispatcher("./WEB-INF/views/rents/delete.jsp").forward(request, response);
				
	}
	protected void doGet2(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
		
			try {
				request.setAttribute("listReservartions",this.vehicleService.findAll());
				request.getRequestDispatcher("./WEB-INF/views/rents/list.jsp").forward(request, response);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
				
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
		
			String delete_id = request.getParameter("id");
			resadelete.setId(Integer.parseInt(delete_id));
			
			try {				
				request.setAttribute("DeleteReservations",this.vehicleService.deleteResa(resadelete));
			} catch (ServiceException e1) {
				e1.printStackTrace();
			}
			doGet2(request,response);
			
	}
}
