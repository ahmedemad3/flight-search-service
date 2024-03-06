package com.eg.booking.flightsearchservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.eg.booking.flightsearchservice.Integration.FlightExternalApiIntegration;
import com.eg.booking.flightsearchservice.model.FlightSearchCriteria;
import com.eg.booking.flightsearchservice.model.FlightSearchData;

@Service
public class FlightService {

	@Autowired
	private FlightExternalApiIntegration flightExternalApiIntegration;

	@Cacheable(value = "flightSearchData", key = "#guestUserId + '-' + #searchCriteria.hashCode()")
	public FlightSearchData searchFlightsForGuestUser(String guestUserId, FlightSearchCriteria searchCriteria) {
		// Perform the actual search using the provided criteria
//		FlightSearchData flightData = flightExternalApiIntegration.searchFlights(searchCriteria); // API issue 
		FlightSearchData flightData = flightExternalApiIntegration.searchFlightsGenerator(searchCriteria); // static
																											// data

		// Simulate some delay or complex processing
		simulateDelay();

		return flightData;
	}

	@Cacheable(value = "flightSearchCriteria", key = "#loggedInUser + '-' + #searchCriteria.hashCode()")
	public FlightSearchData searchFlightsForLoggedInUser(String loggedInUser, FlightSearchCriteria searchCriteria) {
		// Perform the actual search using the provided criteria
		FlightSearchData flightData = flightExternalApiIntegration.searchFlights(searchCriteria);

		return flightData;
	}

	// Simulate a delay or complex processing
	private void simulateDelay() {
		try {
			Thread.sleep(2000); // 2 seconds
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}
