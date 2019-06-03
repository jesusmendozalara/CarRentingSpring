package com.atsistemas.rentService.service;

import java.util.Date;

public interface DateValidator {
	/**
	 * Dados dos intervalos de fechas comprueba que no se pisen entre ellas
	 * @param a
	 * @param b
	 * @return
	 */
	public Boolean validation(Date aStart, Date aEnd, Date bStart, Date bEnd);
}
