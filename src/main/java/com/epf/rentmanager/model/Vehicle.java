package com.epf.rentmanager.model;

public class Vehicle {
	private int id;
	private String constructeur;
	private String modele;
	private short nb_places;
	
	
	
	public Vehicle() {
	}


	public Vehicle(int id, String constructeur, short nb_places) {
		this.id = id;
		this.constructeur = constructeur;
		this.nb_places = nb_places;
	}
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getConstructeur() {
		return constructeur;
	}


	public void setConstructeur(String constructeur) {
		this.constructeur = constructeur;
	}


	public String getModele() {
		return modele;
	}


	public void setModele(String modele) {
		this.modele = modele;
	}


	public short getNb_places() {
		return nb_places;
	}


	public void setNb_places(short nb_places) {
		this.nb_places = nb_places;
	}


	public String toString() {
		return "id : " + id + "\nconstructeur : " + constructeur + "\nnb_places : " + nb_places;
	}
}
