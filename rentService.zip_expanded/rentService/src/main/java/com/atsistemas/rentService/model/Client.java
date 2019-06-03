package com.atsistemas.rentService.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private Integer id;

	private String dni;
	private String name;

	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
	private Set<Rental> rentals = new HashSet<Rental>();
}
