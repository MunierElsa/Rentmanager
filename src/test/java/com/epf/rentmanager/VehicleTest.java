package com.epf.rentmanager;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.epf.rentmanager.exception.ContrainteException;
import com.epf.rentmanager.model.Vehicle;

public class VehicleTest {
	
	 @Test
	  public void nbPlacesLegal_should_return_true_when_nb_Places_is_over_2_or_under_9() throws ContrainteException {
	      // Given
	      Vehicle legalVehicle = new Vehicle("Constructeur", (short) 6);
	        
	      // Then
	      assertTrue(Vehicle.nbPlacesLegal(legalVehicle));
	  }
	 
	 @Test
	  public void nbPlacesLegal_should_return_false_when_nb_Places_is_under_2_or_over_9() throws ContrainteException {
	      // Given
	      Vehicle legalVehicle = new Vehicle("Constructeur", (short) 15);
	        
	      // Then
	      assertFalse(Vehicle.nbPlacesLegal(legalVehicle));
	  }

}
