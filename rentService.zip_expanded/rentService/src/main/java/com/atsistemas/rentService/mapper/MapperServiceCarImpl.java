package com.atsistemas.rentService.mapper;

import org.springframework.stereotype.Component;

import com.atsistemas.rentService.dto.CarDTO;
import com.atsistemas.rentService.model.Car;

@Component
public class MapperServiceCarImpl implements MapperService<CarDTO, Car> {

	@Override
	public CarDTO mapToDto(Car car) {
		CarDTO cardto = new CarDTO();
		cardto.setCarPlate(car.getCarPlate());
		cardto.setId(car.getId());
		cardto.setRegistrationYear(car.getRegistrationYear());
		return cardto;
	}

	@Override
	public Car mapToEntity(CarDTO cardto) {
		Car car = new Car();
		car.setCarPlate(cardto.getCarPlate());
		car.setId(cardto.getId());
		car.setRegistrationYear(cardto.getRegistrationYear());
		return car;
	}

}
