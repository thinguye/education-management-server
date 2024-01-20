package com.capstone.educationmanagementserver.services.interfaces;

import java.util.List;

import com.capstone.educationmanagementserver.models.Quarter;
import com.capstone.educationmanagementserver.requests.quarter.AddQuarterRequest;
import com.capstone.educationmanagementserver.requests.quarter.UpdateQuarterRequest;

import org.springframework.web.multipart.MultipartFile;

public interface IQuarterService {
	void addQuarterFromFile(MultipartFile file);

	void addQuarter(AddQuarterRequest generation);

	void updateQuarter(UpdateQuarterRequest generation);

	void removeQuarter(String id);

	List<Quarter> getAllQuarter();

	Quarter getQuarterById(String id);

	List<Quarter> getQuartersByYear(String year);

	Quarter getQuarterByQuarterYear(String quarter, String year);
}
