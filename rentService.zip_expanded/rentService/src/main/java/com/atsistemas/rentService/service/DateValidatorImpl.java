package com.atsistemas.rentService.service;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class DateValidatorImpl implements DateValidator {
	public Boolean validation(Date aStart, Date aEnd, Date bStart, Date bEnd) {
		if ((aEnd.compareTo(bStart) >= 0 && aEnd.compareTo(bEnd) <= 0)
				|| (aStart.compareTo(bStart) >= 0 && aStart.compareTo(bEnd) <= 0)) {
			return false;
		} else {
			return true;
		}
	}
}