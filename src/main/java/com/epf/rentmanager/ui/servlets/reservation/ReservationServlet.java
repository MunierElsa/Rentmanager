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
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/rents")
public class ReservationServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	  @Autowired
	  private VehicleService resaService;

	  @Override
	  public void init() throws ServletException {
	     super.init();
	     SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	  }
	
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
