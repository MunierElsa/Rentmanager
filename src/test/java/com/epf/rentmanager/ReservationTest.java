package com.epf.rentmanager;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.epf.rentmanager.exception.ContrainteException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;

public class ReservationTest {
	
	@Test
	public void resaLegal_should_return_true_when_resa_is_legal() throws ContrainteException, ServiceException {
		List<Reservation> reservations = new ArrayList<Reservation>();
	    // Given
	    reservations.add(new Reservation(1, 2, LocalDate.parse("2022-04-12"), LocalDate.parse("2022-04-15")));
	    reservations.add(new Reservation(3, 1, LocalDate.parse("2021-03-10"), LocalDate.parse("2021-03-12")));
	    
	    Reservation legalReservation = new Reservation(2, 1, LocalDate.parse("2022-03-10"), LocalDate.parse("2022-03-12"));
	        
	    // Then
	    assertTrue(Reservation.resaLegal(legalReservation, reservations));
	}
	
	@Test
	public void resaLegal_should_return_true_when_resa_is_not_legal() throws ContrainteException, ServiceException {
		List<Reservation> reservations = new ArrayList<Reservation>();
	    // Given
	    reservations.add(new Reservation(1, 2, LocalDate.parse("2022-04-12"), LocalDate.parse("2022-04-15")));
	    reservations.add(new Reservation(3, 1, LocalDate.parse("2021-03-10"), LocalDate.parse("2021-03-12")));
	    
	    Reservation legalReservation = new Reservation(2, 1, LocalDate.parse("2021-03-10"), LocalDate.parse("2021-03-12"));
	        
	    // Then
	    assertFalse(Reservation.resaLegal(legalReservation, reservations));
	}
	
	@Test
	public void resa7DaysLegal_should_return_true_when_number_of_days_is_less_than_7_days() throws ContrainteException {
	    // Given
	    Reservation legalReservation = new Reservation(2, 1, LocalDate.parse("2021-03-10"), LocalDate.parse("2021-03-15"));
	        
	    // Then
	    assertTrue(Reservation.resa7DaysLegal(legalReservation));
	}
	
	@Test
	public void resa7DaysLegal_should_return_false_when_number_of_days_is_more_than_7_days() throws ContrainteException {
	    // Given
	    Reservation legalReservation = new Reservation(2, 1, LocalDate.parse("2021-03-10"), LocalDate.parse("2021-03-30"));
	        
	    // Then
	    assertFalse(Reservation.resa7DaysLegal(legalReservation));
	}

}
