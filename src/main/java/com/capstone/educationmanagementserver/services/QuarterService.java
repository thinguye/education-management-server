package com.capstone.educationmanagementserver.services;

import java.util.List;

import com.capstone.educationmanagementserver.models.Quarter;
import com.capstone.educationmanagementserver.models.Year;
import com.capstone.educationmanagementserver.repositories.interfaces.IQuarterRepository;
import com.capstone.educationmanagementserver.requests.quarter.AddQuarterRequest;
import com.capstone.educationmanagementserver.requests.quarter.UpdateQuarterRequest;
import com.capstone.educationmanagementserver.services.interfaces.IQuarterService;
import com.capstone.educationmanagementserver.services.interfaces.IYearService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class QuarterService implements IQuarterService {

	@Autowired
	private IQuarterRepository iQuarterRepository;

	@Autowired
	private IYearService iYearService;

	@Override
	public void addQuarterFromFile(MultipartFile file) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addQuarter(AddQuarterRequest generation) {
		Quarter quarter = Quarter.builder().name(generation.getName())
				.year(generation.getYear()).build();
		iQuarterRepository.save(quarter);
	}

	@Override
	public void updateQuarter(UpdateQuarterRequest generation) {
		Quarter quarter = getQuarterById(generation.getId());
		quarter.setName(generation.getName());
		quarter.setYear(generation.getYear());
		iQuarterRepository.save(quarter);
	}

	@Override
	public void removeQuarter(String id) {
		Quarter quarter = getQuarterById(id);
		iQuarterRepository.remove(quarter);
	}

	@Override
	public List<Quarter> getAllQuarter() {
		return iQuarterRepository.findAll();
	}

	@Override
	public Quarter getQuarterById(String id) {
		return iQuarterRepository.findById(id);
	}

	@Override
	public List<Quarter> getQuartersByYear(String year) {
		Year yearObject = iYearService.getYearById(year);
		return iQuarterRepository.findQuartersByYear(yearObject);
	}

	@Override
	public Quarter getQuarterByQuarterYear(String quarter, String year) {
		Year yearObject = iYearService.getYearByName(year);
		return iQuarterRepository.findQuarterByQuarterYear(quarter, yearObject);
	}

}
