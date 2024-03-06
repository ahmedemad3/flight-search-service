package com.eg.booking.flightsearchservice.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.eg.booking.flightsearchservice.model.FlightSearchData;
import com.eg.booking.flightsearchservice.model.PriceDetail;
import com.eg.booking.flightsearchservice.model.TripDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FlightDataResponseGenerator {

	public static FlightSearchData getFlightDataResponse() {
		FlightSearchData searchData = new FlightSearchData();

		searchData.setAirline("airblue");
		searchData.setVersion("1.0.0");

		Map<String, TripDetails> data = new HashMap<>();
		data.put("trip1", generateRandomTripDetails());
		data.put("trip2", generateRandomTripDetails());

		searchData.setData(data);

		log.info("searchData : {} ", searchData.toString());
		return searchData;

	}

	private static TripDetails generateRandomTripDetails() {
		TripDetails tripDetails = new TripDetails();
		Random random = new Random();
		tripDetails.setFrom(generateRandomAirportCode());
		tripDetails.setTo(generateRandomAirportCode());
		tripDetails.setCurrency("AED");

		List<PriceDetail> other = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			PriceDetail priceDetail = new PriceDetail();
			priceDetail.setDate("2015-03-2" + (i + 8));
			priceDetail.setPrice(String.valueOf(random.nextInt(1000) + 300));
			other.add(priceDetail);
		}
		tripDetails.setOther(other);

		PriceDetail requestedDate = new PriceDetail();
		requestedDate.setDate("2015-04-0" + (random.nextInt(3) + 5));
		requestedDate.setPrice(String.valueOf(random.nextInt(1000) + 800));
		tripDetails.setRequestedDate(requestedDate);

		return tripDetails;
	}

	private static String generateRandomAirportCode() {
		List<String> airportCodes = Arrays.asList("DXB", "LHE", "JFK", "LAX", "LHR", "CDG", "SIN");
		Random random = new Random();
		return airportCodes.get(random.nextInt(airportCodes.size()));
	}

}
