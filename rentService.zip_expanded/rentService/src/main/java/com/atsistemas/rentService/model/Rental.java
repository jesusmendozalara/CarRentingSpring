package com.atsistemas.rentService.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Rental {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private Integer id;

	private Date startDate;
	private Date endDate;
	private Double price;

	@ManyToOne(fetch = FetchType.LAZY)
	private Car car;

	@ManyToOne(fetch = FetchType.LAZY)
	private Client client;
}
