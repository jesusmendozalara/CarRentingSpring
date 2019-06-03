package com.atsistemas.rentService.dto;

import lombok.Data;

@Data
public class RateDTO {
	private Integer id;
	private Double price;
	private String startDate;
	private String endDate;
}
