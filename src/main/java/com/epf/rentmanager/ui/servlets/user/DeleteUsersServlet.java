package com.epf.rentmanager.ui.servlets.user;

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
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/deleteUsers")
public class DeleteUsersServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	String delete_id;

	@Autowired
	private ClientService clientService;
	@Autowired
	private VehicleService vehicleService;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
		
			try {
				request.setAttribute("listUsers",this.clientService.findAll());
				request.getRequestDispatcher("./WEB-INF/views/users/delete.jsp").forward(request, response);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
			
				
	}
	
	protected void doGet2(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
		
			try {
				request.setAttribute("listUsers",this.clientService.findAll());
				request.getRequestDispatcher("./WEB-INF/views/users/list.jsp").forward(request, response);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
				
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
		
			delete_id = request.getParameter("id");
			
			try {	
				suppressionResa(Integer.parseInt(delete_id));
				request.setAttribute("DeleteUsers",this.clientService.delete(clientService.findById(Short.parseShort(delete_id))));
			} catch (ServiceException e1) {
				e1.printStackTrace();
			}
			doGet2(request,response);
			
	}
	
	//Suppression des réservations associées au client supprimé
	private void suppressionResa(int delete_id) {
		try {
			for (Reservation resa : this.vehicleService.findAllResa()) {
				if (delete_id == resa.getClient_id()) {
					this.vehicleService.deleteResa(resa);
				}
			}
		} catch (NumberFormatException | ServiceException e) {
			e.printStackTrace();
		}
	}
	
	

}