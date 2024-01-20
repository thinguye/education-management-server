package com.capstone.educationmanagementserver.services;

import java.util.List;

import com.capstone.educationmanagementserver.models.Year;
import com.capstone.educationmanagementserver.repositories.interfaces.IYearRepository;
import com.capstone.educationmanagementserver.requests.year.AddYearRequest;
import com.capstone.educationmanagementserver.requests.year.UpdateYearRequest;
import com.capstone.educationmanagementserver.services.interfaces.IYearService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YearService implements IYearService {
	@Autowired
	private IYearRepository iYearRepository;

	@Override
	public void addYear(AddYearRequest year) {
		Year newYear = Year.builder().code(year.getCode()).name(year.getName()).startYear(year.getStartYear())
				.endYear(year.getEndYear()).build();
		iYearRepository.save(newYear);
	}

	@Override
	public void updateYear(UpdateYearRequest year) {
		try {
			Year updateYear = getYearById(year.getId());
			updateYear.setCode(year.getCode());
			updateYear.setName(year.getName());
			updateYear.setStartYear(year.getStartYear());
			updateYear.setEndYear(year.getEndYear());
			iYearRepository.save(updateYear);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeYear(String id) {
		Year year = getYearById(id);
		iYearRepository.remove(year);
	}

	@Override
	public List<Year> getAllYear() {
		return iYearRepository.findAll();
	}

	@Override
	public Year getYearByCode(String code) {
		return iYearRepository.findByCode(code);
	}

	@Override
	public Year getYearById(String id) {
		return iYearRepository.findById(id);
	}

	@Override
	public Year getYearByName(String year) {
		return iYearRepository.findByName(year);
	}

}
