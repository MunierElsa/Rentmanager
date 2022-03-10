package com.epf.rentmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.ContrainteException;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;

@Service
public class VehicleService {

	private VehicleDao vehicleDao;
	private ReservationDao reservationDao;
	
	private VehicleService(VehicleDao vehicleDao, ReservationDao reservationDao) {
		this.vehicleDao = vehicleDao;
		this.reservationDao = reservationDao;
	}
	
	
	public long create(Vehicle vehicle) throws ServiceException, ContrainteException {
		// TODO: créer un véhicule
		try {
			verifContrainte(vehicle);
			verifException(vehicle);
			return this.vehicleDao.create(vehicle);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	public long delete(Vehicle vehicle) throws ServiceException {
		// TODO: supprimer un véhicule
		try {
			suppressionResa(vehicle.getId());
			return this.vehicleDao.delete(vehicle);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	

	public Vehicle findById(int id) throws ServiceException {
		// TODO: récupérer un véhicule par son id
		try {
			return this.vehicleDao.findById(id).get();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

	public List<Vehicle> findAll() throws ServiceException {
		// TODO: récupérer tous les clients
		try {
			return this.vehicleDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public long edit(Vehicle vehicle) throws ServiceException, ContrainteException {
		// TODO: éditer un véhicule
		try {
			verifContrainte(vehicle);
			verifException(vehicle);
			return this.vehicleDao.edit(vehicle);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	public long count() throws ServiceException {
		// TODO: compter les véhicules
		try {
			return this.vehicleDao.count();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return 0;
		
	}
	
	/*Partie Reservation*/
	
	public long createResa(Reservation reservation) throws ServiceException, ContrainteException {
		// TODO: créer une reservation
		try {
			verifContrainte(reservation);
			return this.reservationDao.create(reservation);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	public long deleteResa(Reservation reservation) throws ServiceException {
		// TODO: supprimer une reservation
		try {
			return this.reservationDao.delete(reservation);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	public List<Reservation> findResaByClientId(int clientId) throws ServiceException {
		// TODO: récupérer tous les clients
		try {
			return this.reservationDao.findResaByClientId(clientId);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public List<Reservation> findResaByVehicleId(int vehicleId) throws ServiceException {
		// TODO: 
		try {
			return this.reservationDao.findResaByVehicleId(vehicleId);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public List<Reservation> findAllResa() throws ServiceException {
		// TODO: récupérer toutes les réservations
		try {
			return this.reservationDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public long editResa(Reservation reservation) throws ServiceException, ContrainteException {
		// TODO: éditer un véhicule
		try {
			verifContrainte(reservation);
			return this.reservationDao.edit(reservation);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	public long countResa() throws ServiceException {
		// TODO: compter les reservations
		try {
			return this.reservationDao.count();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	private void verifException(Vehicle vehicle) throws ServiceException {
		if (vehicle.getConstructeur().equals("") || vehicle.getNb_places() < 1) {
			throw new ServiceException();
		}
	}
	
	//Suppression des réservations associées au véhicule supprimé
	private void suppressionResa(int delete_id) {
		try {
			for (Reservation resa : this.findAllResa()) {
				if (delete_id == resa.getVehicle_id()) {
					this.deleteResa(resa);
				}
			}
		} catch (NumberFormatException | ServiceException e) {
			e.printStackTrace();
		}
	}
	
	private void verifContrainte(Reservation reservation) throws ContrainteException, ServiceException {
		
		//La voiture ne peut pas être réservée 2 fois le même jour
		if (!Reservation.resaLegal(reservation,this.findAllResa())) {
				throw new ContrainteException("La voiture ne peut pas etre reservee 2 fois le meme jour");
		}
		
		//La voiture ne peut pas être réservée plus de 7 jours de suite par le même utilisateur
		if(!Reservation.resa7DaysLegal(reservation)) {
			throw new ContrainteException("La voiture ne peut pas etre reservee plus de 7 jours de suite par le meme utilisateur");
		}
	}
	private void verifContrainte(Vehicle vehicle) throws ContrainteException, ServiceException {
		//La voiture doit avoir entre 2 et 9 places
		if(!Vehicle.nbPlacesLegal(vehicle)) {
			throw new ContrainteException("La voiture doit avoir entre 2 et 9 places");
		}
	}
	
}
