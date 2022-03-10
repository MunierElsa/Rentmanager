package com.epf.rentmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.ContrainteException;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;

@Service
public class ClientService {

	private ClientDao clientDao;
	private ClientService(ClientDao clientDao) {
		this.clientDao = clientDao;
	}	
	
	public long create(Client client) throws ServiceException {
		// TODO: créer un client
		try {
			verifContrainte(client);
			verifException(client);
			return this.clientDao.create(client);
		} catch (DaoException e) {
			throw new ServiceException();
		} catch (ContrainteException e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	
	public long delete(Client client) throws ServiceException {
		// TODO: supprimer un client
		try {
			return this.clientDao.delete(client);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return 0;
		
	}

	public Client findById(int id) throws ServiceException {
		// TODO: récupérer un client par son id
		try {
			return this.clientDao.findById(id).get();
		} catch (DaoException e) {
			throw new ServiceException();
		}
		
	}

	public List<Client> findAll() throws ServiceException {
		// TODO: récupérer tous les clients
		try {
			return this.clientDao.findAll();
		} catch (DaoException e) {
			throw new ServiceException();
		}
		
	}
	
	public long edit(Client client) throws ServiceException {
		// TODO: modifier un client
		try {
			verifContrainte(client);
			verifException(client);
			return this.clientDao.edit(client);
		} catch (DaoException e) {
			e.printStackTrace();
		} catch (ContrainteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	public long count() throws ServiceException {
		// TODO: récupérer tous les clients
		try {
			return this.clientDao.count();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	public void verifContrainte(Client client) throws ContrainteException, ServiceException {
		
		//Un client n'ayant pas 18 ans ne peut pas être créé
		if (Client.ageLegal(client) == false) {
			throw new ContrainteException("Un client n'ayant pas 18 ans ne peut pas etre cree");
		}
		
		//Un client doit avoir un nom et un prénom avec un minimun de 3 lettres
		if (Client.nomLegal(client) == false || Client.prenomLegal(client) == false) {
			throw new ContrainteException("Un client doit avoir un nom et un prénom avec un minimun de 3 lettres");
		}
		
		//Un client ayant une adresse mail deja prise ne peut pas être cree
				if (!Client.emailLegal(client, this.findAll())) {
					throw new ContrainteException("Un client ayant une adresse mail deja prise ne peut pas être cree");
				}
		
	}
	
	public void verifException(Client client) throws ServiceException {
		if (!Client.prenomIsNotNull(client) || !Client.nomIsNotNull(client)) {
			throw new ServiceException();
		}
	}
	
	
}
