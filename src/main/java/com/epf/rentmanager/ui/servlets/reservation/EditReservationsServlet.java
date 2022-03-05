package com.epf.rentmanager.ui.servlets.reservation;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ContrainteException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/editReservations")
public class EditReservationsServlet extends HttpServlet{

	
	private static final long serialVersionUID = 1L;

	Reservation reservationedit = new Reservation();
	
	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private ClientService clientService;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
		
			try {
				request.setAttribute("listUsers",this.clientService.findAll());
				request.setAttribute("listVehicles",this.vehicleService.findAll());
				request.getRequestDispatcher("./WEB-INF/views/rents/edit.jsp").forward(request, response);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
	}
	
	protected void doGet2(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
		
			try {
				request.setAttribute("listReservartions",this.vehicleService.findAllResa());
				request.getRequestDispatcher("./WEB-INF/views/rents/list.jsp").forward(request, response);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
				
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String voiture = request.getParameter("car");
		String client = request.getParameter("client");
		String debut = request.getParameter("begin");
		String fin = request.getParameter("end");
		
		LocalDate debut_date = LocalDate.parse(debut);
		LocalDate fin_date = LocalDate.parse(fin);
		
		reservationedit.setId(Integer.parseInt(id));
		reservationedit.setClient_id(Integer.parseInt(client));
		reservationedit.setVehicle_id(Integer.parseInt(voiture));
		reservationedit.setDebut(debut_date);
		reservationedit.setFin(fin_date);
						
			try {
				request.setAttribute("EditReservations",this.vehicleService.editResa(reservationedit));	
			} catch (ServiceException e1) {
				e1.printStackTrace();
			}
			doGet2(request,response);
			
	}
	
}
