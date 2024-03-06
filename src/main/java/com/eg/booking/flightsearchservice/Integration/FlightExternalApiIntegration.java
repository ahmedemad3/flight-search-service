package com.eg.booking.flightsearchservice.Integration;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.eg.booking.flightsearchservice.model.FlightSearchCriteria;
import com.eg.booking.flightsearchservice.model.FlightSearchData;
import com.eg.booking.flightsearchservice.utils.FlightDataResponseGenerator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FlightExternalApiIntegration {

	private final RestTemplate restTemplate;

	@Value("${rapidapi.host}")
	private String rapidApiHost;

	@Value("${rapidapi.key}")
	private String rapidApiKey;

	@Value("${rapidapi.url}")
	private String rapidApiUrl;

	public FlightExternalApiIntegration(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public FlightSearchData searchFlights(FlightSearchCriteria criteria) {
		// should be async with completeable Future in case of multiple API Calls in the
		// Flight service

		log.info("criteria : {} " , criteria.toString());
		// Set query parameters using UriComponentsBuilder
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(rapidApiUrl)
				.queryParam("to", criteria.getTo()).queryParam("from", criteria.getFrom())
				.queryParam("depart-date", criteria.getDepartDate())
				.queryParam("return-date", criteria.getReturnDate());

		// Build URL with query parameters
		String fullUrl = builder.toUriString();

		log.info("fullUrl : {}" , fullUrl);
		
		// Set headers
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-RapidAPI-Key", rapidApiKey);
		headers.set("X-RapidAPI-Host", rapidApiHost);
		HttpEntity<String> entity = new HttpEntity<>(headers);

		// Make API call
		ResponseEntity<FlightSearchData> response = restTemplate.exchange(fullUrl, HttpMethod.GET, entity,
				FlightSearchData.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		} else {
			log.info("API Call status code: {}" , response.getStatusCode());
			throw new RuntimeException("Failed to retrieve flight data. Status code: " + response.getStatusCodeValue());
		}
	}
	
	public FlightSearchData searchFlightsGenerator(FlightSearchCriteria criteria) {
		log.info("criteria : {} " , criteria.toString());
		return FlightDataResponseGenerator.getFlightDataResponse();
	}

}
