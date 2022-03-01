package com.epf.rentmanager.ui.servlets.user;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/createUsers")
public class AddUsersServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private ClientService clientService;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	Client useradd = new Client();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
		
			request.getRequestDispatcher("./WEB-INF/views/users/create.jsp").forward(request, response);
				
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
			
			String nom = request.getParameter("last_name");
			String prenom = request.getParameter("first_name");
			String email = request.getParameter("email");
			String naissance = request.getParameter("naissance");
			
			LocalDate date_naissance = LocalDate.parse(naissance);
											
			useradd.setNom(nom.toUpperCase());
			useradd.setPrenom(prenom);
			useradd.setEmail(email);
			useradd.setNaissance(date_naissance);
			
			
			try {
				verifException();
				int id = 0;
				for(int i = 0; i < clientService.findAll().size(); ++i) {
					if(id < clientService.findAll().get(i).getId()) {
						id = clientService.findAll().get(i).getId();
					} else ;
				}
				useradd.setId(id);
				request.setAttribute("CreateUsers",this.clientService.create(useradd));	
				
			} catch (ServiceException e1) {
				e1.printStackTrace();
			}
			
			doGet(request,response);
			
	}
	
	private void verifException() throws ServiceException {
		if (useradd.getNom() == null || useradd.getPrenom() == null) {
			throw new ServiceException();
		}
	}
}