package com.eg.booking.flightsearchservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.eg.booking.flightsearchservice.model.FlightSearchCriteria;
import com.eg.booking.flightsearchservice.model.FlightSearchData;
import com.eg.booking.flightsearchservice.service.FlightService;
import com.eg.booking.flightsearchservice.utils.GuestUserIdGenerator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/flight")
public class FlightController {

	private final FlightService flightService;

	@Autowired
	public FlightController(FlightService flightService) {
		this.flightService = flightService;
	}

	@GetMapping("/search")
	@ResponseStatus(code = HttpStatus.OK)
	public FlightSearchData searchFlights(HttpServletRequest request, HttpServletResponse response , @RequestParam String to,
			@RequestParam String from, @RequestParam String departDate, @RequestParam String returnDate,
			@RequestParam Integer numberOfTravellers, @RequestParam(required = false) String userId) {

		FlightSearchCriteria searchCriteria = FlightSearchCriteria.createFlightSearchCriteria(from, to, departDate,
				returnDate, numberOfTravellers);

		if (userId != null && !userId.isEmpty()) {
			// User is logged in, perform logged-in user logic
			return flightService.searchFlightsForLoggedInUser(userId, searchCriteria);
		} else {
			// Guest user, perform guest user logic, Get guestUserID
			String guestUserId = GuestUserIdGenerator.generateGuestUserId(request , response);
			log.info("guestUserId : {} " , guestUserId);
			return flightService.searchFlightsForGuestUser(guestUserId, searchCriteria);
		}
	}

}
