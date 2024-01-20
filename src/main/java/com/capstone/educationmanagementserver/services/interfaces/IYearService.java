package com.capstone.educationmanagementserver.services.interfaces;

import java.util.List;

import com.capstone.educationmanagementserver.models.Year;
import com.capstone.educationmanagementserver.requests.year.AddYearRequest;
import com.capstone.educationmanagementserver.requests.year.UpdateYearRequest;

public interface IYearService {
	void addYear(AddYearRequest year);

	void updateYear(UpdateYearRequest year);

	void removeYear(String id);

	List<Year> getAllYear();

	Year getYearByCode(String code);

	Year getYearById(String id);

	Year getYearByName(String year);
}
