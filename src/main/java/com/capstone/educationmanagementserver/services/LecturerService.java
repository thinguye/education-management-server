package com.capstone.educationmanagementserver.services;

import java.util.List;

import com.capstone.educationmanagementserver.enums.Gender;
import com.capstone.educationmanagementserver.models.Lecturer;
import com.capstone.educationmanagementserver.repositories.interfaces.ILecturerRepository;
import com.capstone.educationmanagementserver.requests.staff.AddLecturerRequest;
import com.capstone.educationmanagementserver.requests.staff.UpdateLecturerRequest;
import com.capstone.educationmanagementserver.services.interfaces.ILecturerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class LecturerService implements ILecturerService {
	@Autowired
	ILecturerRepository iLecturerRepository;

	@Override
	public List<Lecturer> getAllLecturer() {
		return iLecturerRepository.findAll();
	}

	@Override
	public Lecturer getLecturerById(String id) {
		return iLecturerRepository.findById(id);
	}

	@Override
	public Lecturer getLecturerByCode(String code) {
		return iLecturerRepository.findByCode(code);
	}

	@Override
	public void addLecturersFromFile(MultipartFile file) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addLecturer(AddLecturerRequest request) {
		Lecturer l = Lecturer.builder().code(request.getCode()).lastName(request.getLastName())
				.middleName(request.getMiddleName()).firstName(request.getFirstName()).email(request.getEmail())
				.gender(Gender.valueOf(request.getGender())).department(request.getDepartment()).build();
		iLecturerRepository.save(l);
	}

	@Override
	public List<Lecturer> getLecturerByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeLecturer(String id) {
		Lecturer l = iLecturerRepository.findById(id);
		iLecturerRepository.remove(l);

	}

	@Override
	public void updateLecturer(UpdateLecturerRequest request) {
		Lecturer l = iLecturerRepository.findById(request.getId());
		Lecturer l2 = iLecturerRepository.findByCode(request.getCode());
		if (l2 == null) {
			l.setCode(request.getCode());
		}
		l.setEmail(request.getEmail());
		l.setOrganization(request.getOrganization());
		l.setFirstName(request.getFirstName());
		l.setLastName(request.getLastName());
		l.setMiddleName(request.getMiddleName());
		iLecturerRepository.save(l);
	}

}
