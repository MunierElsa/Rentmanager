package com.epf.rentmanager.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.epf.rentmanager.exception.ContrainteException;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;

@RunWith(MockitoJUnitRunner.class)
public class ClientDaoTest {

	@InjectMocks
	   private ClientService clientService = Mockito.mock(ClientService.class);

	@Mock
	   private ClientDao clientDao = Mockito.mock(ClientDao.class);
	
    @Test
    void createTest() throws DaoException{
    	// Given
        Client IllegalUser = new Client("John", "Doe", "john.doe@ensta.fr", LocalDate.of(2022, 01, 25));
        Client LegalUser = new Client("John", "Doe", "john.doe@ensta.fr", LocalDate.of(2000, 01, 25));
        
        // Then
        assertEquals(0, clientDao.create(IllegalUser));
        assertThrows(SQLException.class, ()->clientDao.create(IllegalUser));
    }
    	       
    @Test
    public void deleteTest(){
    }
    
    @Test
    public void findbyIdTest(){
    }
    
    @Test
    public void findallTest(){
    }
    
    @Test
    public void editTest(){
    }
    
    @Test
    public void countTest(){
    }
    
  

}
