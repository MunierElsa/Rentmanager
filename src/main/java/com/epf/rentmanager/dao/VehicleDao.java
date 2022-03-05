package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import com.epf.rentmanager.service.VehicleService;

@Repository
public class VehicleDao {
	
	private VehicleDao() {}
	
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, nb_places) VALUES(?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle;";
	private static final String EDIT_VEHICLES_QUERY = "UPDATE Vehicle SET constructeur = ?, nb_places = ? WHERE id = ?;";
	private static final String COUNT_VEHICLES_QUERY = "SELECT COUNT(id) AS count FROM Vehicle;";
	
	@Autowired
	private VehicleService vehicleService;
	
	public long create(Vehicle vehicle) throws DaoException {
		long n = 0;
		try {
			try {
				Connection conn = ConnectionManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(CREATE_VEHICLE_QUERY);
				pstmt.setString(1,vehicle.getConstructeur());
				pstmt.setInt(2,vehicle.getNb_places());
				verifException(vehicle);
				n = pstmt.executeUpdate();
				conn.close();
			} catch (ServiceException e) {
				e.printStackTrace();
			} 
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return n;
	}

	public long delete(Vehicle vehicle) throws DaoException {
		long n = 0;
		try {
			
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(DELETE_VEHICLE_QUERY);
			pstmt.setInt(1,vehicle.getId());
			suppressionResa(vehicle.getId());
			n = pstmt.executeUpdate();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return n;
	}

	public Optional<Vehicle> findById(int id) throws DaoException {
		
		try {
			
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_VEHICLE_QUERY);
			pstmt.setInt(1,id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			int vehicleId = rs.getInt("id");
			String vehicleConstructeur = rs.getString("constructeur");
			short vehicleNbPlaces = rs.getShort("nb_places");
			
			Vehicle vehicle = new Vehicle(vehicleId,vehicleConstructeur,vehicleNbPlaces);
			conn.close();
			return Optional.of(vehicle);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return Optional.empty();
	}

	public List<Vehicle> findAll() throws DaoException {
		List<Vehicle> vehicles = new ArrayList<>();
		
		try {
			
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_VEHICLES_QUERY);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int vehicleId = rs.getInt("id");
				String vehicleConstructeur = rs.getString("constructeur");
				short vehicleNbPlaces = rs.getShort("nb_places");
				Vehicle vehicle = new Vehicle(vehicleId,vehicleConstructeur,vehicleNbPlaces);
				vehicles.add(vehicle);
			}
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return vehicles;
	}
	
	public long edit(Vehicle vehicle) throws DaoException {
		long n = 0;
		try {
			try {
				Connection conn = ConnectionManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(EDIT_VEHICLES_QUERY);
				pstmt.setString(1,vehicle.getConstructeur());
				pstmt.setInt(2,vehicle.getNb_places());
				pstmt.setInt(3,vehicle.getId());
				verifException(vehicle);
				n = pstmt.executeUpdate();
				conn.close();
			} catch (ServiceException e) {
				e.printStackTrace();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return n;
	}
	
	public int count() throws DaoException {
		int n = 0;
		try {
			
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(COUNT_VEHICLES_QUERY);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			n = rs.getInt("count");
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return n;
	}
	
	private void verifException(Vehicle vehicle) throws ServiceException {
		if (vehicle.getConstructeur().equals("") || vehicle.getNb_places() < 1) {
			throw new ServiceException();
		}
	}
	
	//Suppression des réservations associées au véhicule supprimé
		private void suppressionResa(int delete_id) {
			try {
				for (Reservation resa : this.vehicleService.findAllResa()) {
					if (delete_id == resa.getVehicle_id()) {
						this.vehicleService.deleteResa(resa);
					}
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
	

}
