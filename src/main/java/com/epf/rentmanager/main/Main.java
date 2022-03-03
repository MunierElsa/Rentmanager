package com.epf.rentmanager.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.epf.rentmanager.configuration.AppConfiguration;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

public class Main {

	public static void main(String[] args) {
		/*
		 * try {
		 * 
		 * System.out.println(ClientService.getInstance().findById(1));
		 * 
		 * for (int i = 0; i < ClientService.getInstance().findAll().size(); i++) {
		 * System.out.println(ClientService.getInstance().findAll().get(i)); }
		 * 
		 * System.out.println(VehicleService.getInstance().findById(1));
		 * 
		 * for (int i = 0; i < VehicleService.getInstance().findAll().size(); i++) {
		 * System.out.println(VehicleService.getInstance().findAll().get(i)); }
		 * 
		 * Création d'un client LocalDate date = LocalDate.of(2000, 12, 25); Client
		 * nicolas = new Client(5, "Beque", "Nicolas", "nicolas.beque@epfedu.fr", date);
		 * 
		 * Ajout du client //
		 * System.out.println(ClientService.getInstance().create(nicolas)); for (int i =
		 * 0; i < ClientService.getInstance().findAll().size(); i++) {
		 * System.out.println(ClientService.getInstance().findAll().get(i)); }
		 * 
		 * Suppression du client //
		 * System.out.println(ClientService.getInstance().delete(nicolas)); for (int i =
		 * 0; i < ClientService.getInstance().findAll().size(); i++) {
		 * System.out.println(ClientService.getInstance().findAll().get(i)); }
		 * 
		 * Création d'un véhicule Vehicle vehicledeNicolas = new Vehicle(5, "Jaguar",
		 * (short) 18);
		 * 
		 * Ajout du vehicule //
		 * System.out.println(VehicleService.getInstance().create(vehicledeNicolas));
		 * for (int i = 0; i < VehicleService.getInstance().findAll().size(); i++) {
		 * System.out.println(VehicleService.getInstance().findAll().get(i)); }
		 * 
		 * Suppression du vehicule //
		 * System.out.println(VehicleService.getInstance().delete(vehicledeNicolas));
		 * for (int i = 0; i < VehicleService.getInstance().findAll().size(); i++) {
		 * System.out.println(VehicleService.getInstance().findAll().get(i)); }
		 * 
		 * for (int i = 0; i < VehicleService.getInstance().findAllResa().size(); i++) {
		 * System.out.println(VehicleService.getInstance().findAllResa().get(i)); }
		 * 
		 * 
		 * // Création d'une reservation LocalDate debut = LocalDate.of(2000, 06, 25);
		 * LocalDate fin = LocalDate.of(2000, 07, 03); Reservation NicolasvaaNarbonne =
		 * new Reservation(1,1,1,debut,fin);
		 * 
		 * // Ajout d'une reservation
		 * //System.out.println(VehicleService.getInstance().createResa(
		 * NicolasvaaNarbonne)); for(int i = 0; i <
		 * VehicleService.getInstance().findAllResa().size();i++) {
		 * System.out.println(VehicleService.getInstance().findAllResa().get(i)); }
		 * 
		 * //Suppression d'une reservation
		 * //System.out.println(VehicleService.getInstance().deleteResa(
		 * NicolasvaaNarbonne)); for(int i = 0; i <
		 * VehicleService.getInstance().findAllResa().size();i++) {
		 * System.out.println(VehicleService.getInstance().findAllResa().get(i)); }
		 * 
		 * 
		 * } catch (ServiceException e) { e.printStackTrace(); }
		 */
		
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
		ClientService clientService = context.getBean(ClientService.class);
		VehicleService vehicleService = context.getBean(VehicleService.class);
		

	}

}
