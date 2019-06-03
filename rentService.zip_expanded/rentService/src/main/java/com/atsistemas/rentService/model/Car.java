package com.atsistemas.rentService.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private Integer id;

	private String carPlate;
	private String registrationYear;

	@OneToMany(mappedBy = "car", fetch = FetchType.LAZY)
	private Set<Rental> rentals = new HashSet<Rental>();

	@ManyToMany(mappedBy = "cars", fetch = FetchType.LAZY)
	private Set<Rate> rates = new HashSet<Rate>();
}
