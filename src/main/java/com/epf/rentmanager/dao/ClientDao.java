package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.persistence.ConnectionManager;

public class ClientDao {
	
	private static ClientDao instance = null;
	private ClientDao() {}
	public static ClientDao getInstance() {
		if(instance == null) {
			instance = new ClientDao();
		}
		return instance;
	}
	
	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
	
	public long create(Client client) throws DaoException {
		long n = 0;
		try {
			
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(CREATE_CLIENT_QUERY);
			pstmt.setString(1,client.getNom());
			pstmt.setString(2,client.getPrenom());
			pstmt.setString(3,client.getEmail());
			pstmt.setString(4,client.getNaissance().toString());
			n = pstmt.executeUpdate();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return n;
	}
	
	public long delete(Client client) throws DaoException {
		long n = 0;
		try {
			
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(DELETE_CLIENT_QUERY);
			pstmt.setInt(1,client.getId());
			n = pstmt.executeUpdate();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return n;
	}

	public Optional<Client> findById(int id) throws DaoException {
				
		try {
			
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_CLIENT_QUERY);
			pstmt.setInt(1,id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			int clientId = rs.getInt("id");
			String clientLastName = rs.getString("nom");
			String clientFirstName = rs.getString("prenom");
			String clientEmail = rs.getString("email");
			LocalDate clientBirthdate = rs.getDate("naissance").toLocalDate();
			
			Client client = new Client(clientId,clientLastName,clientFirstName,clientEmail,clientBirthdate);
			conn.close();
			return Optional.of(client);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return Optional.empty();
	}

	public List<Client> findAll() throws DaoException {
		List<Client> clients = new ArrayList<>();
		
		try {
			
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_CLIENTS_QUERY);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int clientId = rs.getInt("id");
				String clientLastName = rs.getString("nom");
				String clientFirstName = rs.getString("prenom");
				String clientEmail = rs.getString("email");
				LocalDate clientBirthdate = rs.getDate("naissance").toLocalDate();
				Client client = new Client(clientId,clientLastName,clientFirstName,clientEmail,clientBirthdate);
				clients.add(client);
			}
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return clients;
	}

}
