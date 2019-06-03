package com.atsistemas.rentService.dto;

import lombok.Data;

@Data
public class RentalDTO {
	private Integer id;
    private CarDTO car;
    private ClientDTO client;
    private Double price;
    private String startDate;
    private String endDate;
}
