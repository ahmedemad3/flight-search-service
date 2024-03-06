package com.eg.booking.flightsearchservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class FlightSearchCriteria {

	private String from;
	private String to;
	private String departDate;
	private String returnDate;
	private Integer numberOfTravellers;

	public static FlightSearchCriteria createFlightSearchCriteria(String from, String to, String depdepartDate,
			String returnDate, Integer numberOfTravellers) {
		return FlightSearchCriteria.builder().from(from).to(to).departDate(depdepartDate).returnDate(returnDate)
				.numberOfTravellers(numberOfTravellers).build();
	}

}
