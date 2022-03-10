package com.epf.rentmanager.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.epf.rentmanager.exception.ContrainteException;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;

public class Client {
	private int id;
	private String nom;
	private String prenom;
	private String email;
	private LocalDate naissance;
	
	public Client() {
	
	}
	
	public Client(int id, String nom, String prenom, String email, LocalDate naissance) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.naissance = naissance;
	}
	
	public Client(String nom, String prenom, String email, LocalDate naissance) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.naissance = naissance;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", naissance="
				+ naissance + "]";
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public LocalDate getNaissance() {
		return naissance;
	}
	
	public void setNaissance(LocalDate naissance) {
		this.naissance = naissance;
	}
	
	public static boolean ageLegal(Client client){
		
		//Un client n'ayant pas 18 ans ne peut pas être créé
		long age = ChronoUnit.YEARS.between(client.getNaissance(), LocalDate.now());
		if (age < 18) {
			return false;
		} else return true;
	}	
	
	public static boolean nomLegal(Client client){
		//Un client doit avoir un nom avec un minimun de 3 lettres
		if (client.getNom().length() < 3) {
			return false;
		} else return true;
	}
	
	public static boolean prenomLegal(Client client){
		//Un client doit avoir un prénom avec un minimun de 3 lettres
		if (client.getPrenom().length() < 3) {
			return false;
		} else return true;
	}
		
	public static boolean emailLegal(Client client, List<Client> liste){
		//Un client ayant une adresse mail deja prise ne peut pas être cree
			for (Client clientliste : liste) {
				if (client.getEmail().equals(clientliste.getEmail())) {
						return false;
				} 
			} 
			return true;
	}
	
	public static boolean nomIsNotNull(Client client){
		if (client.getNom().equals("")) {
			return false;
		} else return true;
	}
	
	public static boolean prenomIsNotNull(Client client){
		if (client.getPrenom().equals("")) {
			return false;
		} else return true;
	}
	
}
