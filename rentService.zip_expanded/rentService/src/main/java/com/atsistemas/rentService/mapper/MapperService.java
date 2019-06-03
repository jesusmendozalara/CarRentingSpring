package com.atsistemas.rentService.mapper;

import java.text.ParseException;

public interface MapperService<T, S> {
	/**
	 * Mapper from entity to DTO.
	 * 
	 * @param entity
	 * @return DTO
	 */
	T mapToDto(S entity);

	/**
	 * Mapper from DTO to Entity.
	 * 
	 * @param DTO
	 * @return Entity
	 * @throws ParseException
	 */
	S mapToEntity(T dto);
}
