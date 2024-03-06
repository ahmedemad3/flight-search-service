package com.eg.booking.flightsearchservice.model;

import java.io.Serializable;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class PriceDetail implements Serializable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = -8635389020139915781L;
	private String date;
     private String price;

}
