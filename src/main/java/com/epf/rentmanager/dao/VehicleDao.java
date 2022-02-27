package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;

public class VehicleDao {
	
	private static VehicleDao instance = null;
	private VehicleDao() {}
	public static VehicleDao getInstance() {
		if(instance == null) {
			instance = new VehicleDao();
		}
		return instance;
	}
	
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, nb_places) VALUES(?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle;";
	private static final String EDIT_VEHICLES_QUERY = "UPDATE Vehicle SET constructeur = ?, nb_places = ? WHERE id = ?;";
	private static final String COUNT_VEHICLES_QUERY = "SELECT COUNT(id) AS count FROM Vehicle;";
	
	public long create(Vehicle vehicle) throws DaoException {
		long n = 0;
		try {
			
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(CREATE_VEHICLE_QUERY);
			pstmt.setString(1,vehicle.getConstructeur());
			pstmt.setInt(2,vehicle.getNb_places());
			n = pstmt.executeUpdate();
			conn.close();
			
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
			
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(EDIT_VEHICLES_QUERY);
			pstmt.setString(1,vehicle.getConstructeur());
			pstmt.setInt(2,vehicle.getNb_places());
			pstmt.setInt(3,vehicle.getId());
			n = pstmt.executeUpdate();
			conn.close();
			
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
			n= rs.getInt("count");
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return n;
	}
	

}
