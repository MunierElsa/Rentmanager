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

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/createReservations")
public class AddReservationsServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	Reservation reservationadd = new Reservation();

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
					request.getRequestDispatcher("./WEB-INF/views/rents/create.jsp").forward(request, response);
				} catch (ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
		
			String voiture = request.getParameter("car");
			String client = request.getParameter("client");
			String debut = request.getParameter("begin");
			String fin = request.getParameter("end");
			
			LocalDate debut_date = LocalDate.parse(debut);
			LocalDate fin_date = LocalDate.parse(fin);
			
			reservationadd.setClient_id(Integer.parseInt(client));
			reservationadd.setVehicle_id(Integer.parseInt(voiture));
			reservationadd.setDebut(debut_date);
			reservationadd.setFin(fin_date);
			
			try {
				int id = 0;
				for(int i = 0; i < vehicleService.findAllResa().size(); ++i) {
					if(id < vehicleService.findAllResa().get(i).getId()) {
						id = vehicleService.findAllResa().get(i).getId();
					} else ;
				}
				reservationadd.setId(id);
				request.setAttribute("CreateReservations",this.vehicleService.createResa(reservationadd));	
				
			} catch (ServiceException e1) {
				e1.printStackTrace();
			}
			doGet(request,response);
			
	}

}
