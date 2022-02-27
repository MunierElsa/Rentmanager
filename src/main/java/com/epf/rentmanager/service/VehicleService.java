package com.epf.rentmanager.service;

import java.util.List;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;

public class VehicleService {

	private VehicleDao vehicleDao;
	private ReservationDao reservationDao;
	public static VehicleService instance;
	
	private VehicleService() {
		this.vehicleDao = VehicleDao.getInstance();
		this.reservationDao = ReservationDao.getInstance();
	}
	
	public static VehicleService getInstance() {
		if (instance == null) {
			instance = new VehicleService();
		}
		
		return instance;
	}
	
	
	public long create(Vehicle vehicle) throws ServiceException {
		// TODO: créer un véhicule
		try {
			return this.vehicleDao.create(vehicle);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	public long delete(Vehicle vehicle) throws ServiceException {
		// TODO: supprimer un véhicule
		try {
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
	
	public long edit(Vehicle vehicle) throws ServiceException {
		// TODO: éditer un véhicule
		try {
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
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	/*Partie Reservation*/
	
	public long createResa(Reservation reservation) throws ServiceException {
		// TODO: créer une reservation
		try {
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
	
	public long countResa() throws ServiceException {
		// TODO: compter les reservations
		try {
			return this.reservationDao.count();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
}
