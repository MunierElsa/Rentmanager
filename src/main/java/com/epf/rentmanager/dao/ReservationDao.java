package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.persistence.ConnectionManager;

@Repository
public class ReservationDao {

	private ReservationDao() {}
	
	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
	private static final String COUNT_RESERVATIONS_QUERY = "SELECT COUNT(id) AS count FROM Reservation;";
		
	public long create(Reservation reservation) throws DaoException {
		long n = 0;
		try {
			
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(CREATE_RESERVATION_QUERY);
			pstmt.setInt(1,reservation.getClient_id());
			pstmt.setInt(2,reservation.getVehicle_id());
			pstmt.setString(3,reservation.getDebut().toString());
			pstmt.setString(4,reservation.getFin().toString());
			n = pstmt.executeUpdate();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return n;
	}
	
	public long delete(Reservation reservation) throws DaoException {
		long n = 0;
		try {
			
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(DELETE_RESERVATION_QUERY);
			pstmt.setInt(1,reservation.getId());
			n = pstmt.executeUpdate();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return n;

	}

	
	public List<Reservation> findResaByClientId(int clientId) throws DaoException {
		List<Reservation> resasByClientId = new ArrayList<>();
		try {
			
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);
			pstmt.setInt(1,clientId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
			int resaId = rs.getInt("id");
			int resaClientId = rs.getInt("client_id");
			int resaVehicleId = rs.getInt("vehicle_id");
			LocalDate resaDebut = rs.getDate("debut").toLocalDate();
			LocalDate resaFin = rs.getDate("fin").toLocalDate();
			Reservation resa = new Reservation(resaId, resaClientId, resaVehicleId, resaDebut, resaFin);
			resasByClientId.add(resa);
			}
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resasByClientId;

	}
	
	public List<Reservation> findResaByVehicleId(int vehicleId) throws DaoException {
		List<Reservation> resasByVehicleId = new ArrayList<>();
		try {
			
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);
			pstmt.setInt(1,vehicleId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
			int resaId = rs.getInt("id");
			int resaClientId = rs.getInt("client_id");
			int resaVehicleId = rs.getInt("vehicle_id");
			LocalDate resaDebut = rs.getDate("debut").toLocalDate();
			LocalDate resaFin = rs.getDate("fin").toLocalDate();
			Reservation resa = new Reservation(resaId, resaClientId, resaVehicleId, resaDebut, resaFin);
			resasByVehicleId.add(resa);
			}
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resasByVehicleId;
	}

	public List<Reservation> findAll() throws DaoException {
		List<Reservation> resas = new ArrayList<>();
		
		try {
			
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_QUERY);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int resaId = rs.getInt("id");
				int resaClientId = rs.getInt("client_id");
				int resaVehicleId = rs.getInt("vehicle_id");
				LocalDate resaDebut = rs.getDate("debut").toLocalDate();
				LocalDate resaFin = rs.getDate("fin").toLocalDate();
				Reservation resa = new Reservation(resaId, resaClientId, resaVehicleId, resaDebut, resaFin);
				resas.add(resa);
			}
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return resas;
	}
	
	public int count() throws DaoException {
		int n = 0;
		try {
			
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(COUNT_RESERVATIONS_QUERY);
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
