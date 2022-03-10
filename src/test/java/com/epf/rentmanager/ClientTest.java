package com.epf.rentmanager;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.ContrainteException;
import com.epf.rentmanager.model.Client;

import org.junit.Test;


public class ClientTest {
   
	
   @Test
   public void ageLegal_should_return_true_when_age_is_over_18() throws ContrainteException {
       // Given
       Client legalUser = new Client("John", "Doe", "john.doe@ensta.fr", LocalDate.of(2000, 05,26));
        
       // Then
       assertTrue(Client.ageLegal(legalUser));
   }
   
   @Test
   public void ageLegal_should_return_false_when_age_is_under_18() throws ContrainteException {
       // Given
       Client illegalUser = new Client("John", "Doe", "john.doe@ensta.fr", LocalDate.of(2022, 05,26));
        
       // Then
       assertFalse(Client.ageLegal(illegalUser));
   }
   
   @Test
   public void nomLegal_should_return_true_when_nom_is_over_3_letters() throws ContrainteException {
       // Given
       Client legalUser = new Client("John", "Doe", "john.doe@ensta.fr", LocalDate.of(2000, 05,26));
        
       // Then
       assertTrue(Client.nomLegal(legalUser));
   }
   
   @Test
   public void nomLegal_should_return_false_when_nom_is_under_3_letters() throws ContrainteException {
       // Given
       Client illegalUser = new Client("John", "De", "john.doe@ensta.fr", LocalDate.of(2000, 05,26));
        
       // Then
       assertTrue(Client.nomLegal(illegalUser));
   }
   
   @Test
   public void prenomLegal_should_return_true_when_nom_is_over_3_letters() throws ContrainteException {
       // Given
       Client legalUser = new Client("John", "Doe", "john.doe@ensta.fr", LocalDate.of(2000, 05,26));
        
       // Then
       assertTrue(Client.prenomLegal(legalUser));
   }
   
   @Test
   public void prenomLegal_should_return_false_when_nom_is_under_3_letters() throws ContrainteException {
       // Given
       Client illegalUser = new Client("Jo", "Doe", "john.doe@ensta.fr", LocalDate.of(2000, 05,26));
        
       // Then
       assertTrue(Client.prenomLegal(illegalUser));
   }
   
   @Test
   public void emailLegal_should_return_true_when_email_is_not_used() throws ContrainteException {
       // Given
	   List<Client> clients = new ArrayList<>();
	   clients.add(new Client("Elsa", "Munier", "elsa.munier@epf.fr", LocalDate.of(2000, 04, 12)));
	   clients.add(new Client("Nicolas", "Beque", "nicolas.bequer@epf.fr", LocalDate.of(2000, 8, 9)));
	   
       Client legalUser = new Client("John", "Doe", "john.doe@ensta.fr", LocalDate.of(2000, 05,26));
        
       // Then
       assertTrue(Client.emailLegal(legalUser,clients));
   }
   
   @Test
   public void emailLegal_should_return_false_when_email_is_used() throws ContrainteException {
       // Given
	   List<Client> clients = new ArrayList<>();
	   clients.add(new Client("Elsa", "Munier", "elsa.munier@epf.fr", LocalDate.of(2000, 04, 12)));
	   clients.add(new Client("Nicolas", "Beque", "nicolas.bequer@epf.fr", LocalDate.of(2000, 8, 9)));
	   
       Client illegalUser = new Client("John", "Doe", "elsa.munier@epf.fr", LocalDate.of(2000, 05,26));
        
       // Then
       assertFalse(Client.emailLegal(illegalUser,clients));
   }
   
   @Test
   public void prenomIsNotNull_should_return_true_when_prenom_is_not_null() throws ContrainteException {
       // Given
       Client legalUser = new Client("John", "Doe", "john.doe@ensta.fr", LocalDate.of(2000, 05,26));
        
       // Then
       assertTrue(Client.prenomIsNotNull(legalUser));
   }
   
   @Test
   public void prenomIsNotNull_should_return_false_when_prenom_is_null() throws ContrainteException {
       // Given
       Client illegalUser = new Client();
       illegalUser.setEmail("john.doe@ensta.fr");
       illegalUser.setNom("Doe");
       illegalUser.setPrenom("");
       illegalUser.setNaissance(LocalDate.of(2000, 05,26));
        
       // Then
       assertFalse(Client.prenomIsNotNull(illegalUser));
   }
   
   @Test
   public void nomIsNotNull_should_return_true_when_nom_is_not_null() throws ContrainteException {
       // Given
       Client legalUser = new Client("John", "Doe", "john.doe@ensta.fr", LocalDate.of(2000, 05,26));
        
       // Then
       assertTrue(Client.nomIsNotNull(legalUser));
   }
   
   @Test
   public void nomIsNotNull_should_return_false_when_nom_is_null() throws ContrainteException {
       // Given
       Client illegalUser = new Client();
       illegalUser.setEmail("john.doe@ensta.fr");
       illegalUser.setPrenom("John");
       illegalUser.setNom("");
       illegalUser.setNaissance(LocalDate.of(2000, 05,26));
        
       // Then
       assertFalse(Client.nomIsNotNull(illegalUser));
   }
   


}
