package com.epf.rentmanager.model;

import java.time.LocalDate;
import java.util.List;

import com.epf.rentmanager.exception.ContrainteException;
import com.epf.rentmanager.exception.ServiceException;

public class Reservation {
	private int id;
	private int client_id;
	private int vehicle_id;
	private LocalDate debut;
	private LocalDate fin;
	
	public Reservation() {
		
	}
	
	public Reservation(int id, int client_id, int vehicle_id, LocalDate debut, LocalDate fin) {
		this.id = id;
		this.client_id = client_id;
		this.vehicle_id = vehicle_id;
		this.debut = debut;
		this.fin = fin;
	}
	
	public Reservation(int client_id, int vehicle_id, LocalDate debut, LocalDate fin) {
		this.client_id = client_id;
		this.vehicle_id = vehicle_id;
		this.debut = debut;
		this.fin = fin;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", client_id=" + client_id + ", vehicle_id=" + vehicle_id + ", debut=" + debut
				+ ", fin=" + fin + "]";
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public int getVehicle_id() {
		return vehicle_id;
	}

	public void setVehicle_id(int vehicle_id) {
		this.vehicle_id = vehicle_id;
	}

	public LocalDate getDebut() {
		return debut;
	}

	public void setDebut(LocalDate debut) {
		this.debut = debut;
	}

	public LocalDate getFin() {
		return fin;
	}

	public void setFin(LocalDate fin) {
		this.fin = fin;
	}
	
	public static boolean resaLegal(Reservation reservation, List<Reservation> liste) throws ContrainteException, ServiceException {
		//La voiture ne peut pas être réservée 2 fois le même jour
		for (Reservation resa : liste) {
			if(reservation.getId() != resa.getId()) {
				if (reservation.getVehicle_id() == resa.getVehicle_id()) {
					if (reservation.getDebut().isAfter(resa.getDebut()) && reservation.getDebut().isBefore(resa.getFin())) {
						return false;
					}
					if (reservation.getDebut().isBefore(resa.getDebut()) && reservation.getFin().isAfter(resa.getDebut())) {
						return false;
					}
					if (reservation.getDebut().equals(resa.getDebut())) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public static boolean resa7DaysLegal(Reservation reservation) throws ContrainteException{
		//La voiture ne peut pas être réservée plus de 7 jours de suite par le même utilisateur
		if(reservation.getDebut().compareTo(reservation.getFin()) < -7) {
			return false;
		} else return true;
	}
	
}
