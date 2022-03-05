package com.epf.rentmanager.dao;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.epf.rentmanager.exception.ContrainteException;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;

@RunWith(MockitoJUnitRunner.class)
public class ClientDaoTest {
	@InjectMocks
    private ClientService clientService;

    @Mock
    private ClientDao clientDao;
	
	@AfterEach
    public void tearDown(){
    }

    @BeforeEach
    public void setUp(){
    }

    @Test
    public void createTest(){
    	// Given
    	LocalDate date = LocalDate.of(2000, 12, 25);
        Client IllegalUser = new Client("John", "Doe", "john.doe@ensta.fr", date);
        // Then
        assertThrows(ContrainteException.class, ()->this.clientService.create(IllegalUser));
    }
    
    @Test
    void findAll_should_fail_when_dao_throws_exception() throws DaoException {
        // When
        when(this.clientDao.findAll()).thenThrow(DaoException.class);
        // Then
        assertThrows(ServiceException.class, () -> clientService.findAll());
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
