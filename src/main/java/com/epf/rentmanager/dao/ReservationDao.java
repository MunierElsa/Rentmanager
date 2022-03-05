package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epf.rentmanager.exception.ContrainteException;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.persistence.ConnectionManager;
import com.epf.rentmanager.service.VehicleService;

@Repository
public class ReservationDao {

	private ReservationDao() {}
	
	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
	private static final String EDIT_RESERVATIONS_QUERY = "UPDATE Reservation SET client_id = ?, vehicle_id = ?, debut = ?, fin = ? WHERE id = ?;";
	private static final String COUNT_RESERVATIONS_QUERY = "SELECT COUNT(id) AS count FROM Reservation;";
	
	@Autowired
	private VehicleService vehicleService;
	
	public long create(Reservation reservation) throws DaoException {
		long n = 0;
		try {
			try {
				Connection conn = ConnectionManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(CREATE_RESERVATION_QUERY);
				pstmt.setInt(1,reservation.getClient_id());
				pstmt.setInt(2,reservation.getVehicle_id());
				pstmt.setString(3,reservation.getDebut().toString());
				pstmt.setString(4,reservation.getFin().toString());
				verifContrainte(reservation);
				n = pstmt.executeUpdate();
				conn.close();
			} catch (ContrainteException e) {
				e.printStackTrace();
			}
			
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
	
	public long edit(Reservation reservation) throws DaoException {
		long n = 0;
		try {
			try {
				Connection conn = ConnectionManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(EDIT_RESERVATIONS_QUERY);
				pstmt.setInt(1,reservation.getClient_id());
				pstmt.setInt(2,reservation.getVehicle_id());
				pstmt.setString(3,reservation.getDebut().toString());
				pstmt.setString(4,reservation.getFin().toString());
				pstmt.setInt(5,reservation.getId());
				verifContrainte(reservation);
				n = pstmt.executeUpdate();
				conn.close();
			} catch (ContrainteException e) {
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
			PreparedStatement pstmt = conn.prepareStatement(COUNT_RESERVATIONS_QUERY);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			n = rs.getInt("count");
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return n;
	}
	
	private void verifContrainte(Reservation reservation) throws ContrainteException {
		
		//La voiture ne peut pas être réservée 2 fois le même jour
		try { 
			for (Reservation resa : this.vehicleService.findAllResa()) {
				if (reservation.getVehicle_id() == resa.getVehicle_id()) {
					if (reservation.getDebut().isAfter(resa.getDebut()) && reservation.getDebut().isBefore(resa.getFin())) {
						throw new ContrainteException("La voiture ne peut pas etre reservee 2 fois le meme jour");
					}
					if (reservation.getDebut().isBefore(resa.getDebut()) && reservation.getFin().isAfter(resa.getDebut())) {
						throw new ContrainteException("La voiture ne peut pas etre reservee 2 fois le meme jour");
					}
				}
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		//La voiture ne peut pas être réservée plus de 7 jours de suite par le même utilisateur
		if(reservation.getDebut().compareTo(reservation.getFin()) < -7) {
			throw new ContrainteException("La voiture ne peut pas etre reservee plus de 7 jours de suite par le meme utilisateur");
		}
	}
}
