package com.eg.booking.flightsearchservice.model;

import java.io.Serializable;
import java.util.Map;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class FlightSearchData implements Serializable{
	
	
	private static final long serialVersionUID = -3334957280558373425L;
	private String airline;
    private Map<String, TripDetails> data;
    private String version;

}
