package com.atsistemas.rentService.mapper;

import java.text.ParseException;

public interface MapperServiceDate <T, S> {
	/**
	 * Mapper from entity to DTO which uses Date class
	 * @param entity
	 * @return DTO
	 */
	T mapToDto(S entity);
	
	/**
	 * Mapper from DTO to Entity which uses Date class
	 * @param DTO
	 * @return Entity
	 * @throws ParseException 
	 */
	S mapToEntity(T dto) throws ParseException;
}
