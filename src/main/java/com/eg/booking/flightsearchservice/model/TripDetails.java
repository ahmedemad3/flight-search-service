package com.eg.booking.flightsearchservice.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class TripDetails implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8082008795767206480L;
	private String from;
    private String to;
    private String currency;
    private List<PriceDetail> other;
    private PriceDetail requestedDate;

}
