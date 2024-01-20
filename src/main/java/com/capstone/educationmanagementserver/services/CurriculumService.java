package com.capstone.educationmanagementserver.services;

import java.util.List;

import com.capstone.educationmanagementserver.models.Curriculum;
import com.capstone.educationmanagementserver.models.ElectiveSubject;
import com.capstone.educationmanagementserver.models.Generation;
import com.capstone.educationmanagementserver.models.Organization;
import com.capstone.educationmanagementserver.models.Subject;
import com.capstone.educationmanagementserver.repositories.interfaces.ICurriculumRepository;
import com.capstone.educationmanagementserver.repositories.interfaces.IElectiveRepository;
import com.capstone.educationmanagementserver.requests.curriculum.AddCurriculumRequest;
import com.capstone.educationmanagementserver.requests.curriculum.AddElectivesRequest;
import com.capstone.educationmanagementserver.requests.curriculum.AddSubjectToCurriculum;
import com.capstone.educationmanagementserver.requests.curriculum.UpdateCirriculumRequest;
import com.capstone.educationmanagementserver.services.interfaces.ICurriculumService;
import com.capstone.educationmanagementserver.services.interfaces.IElectiveService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurriculumService implements ICurriculumService {
	@Autowired
	ICurriculumRepository iCirriculumRepository;
	@Autowired
	IElectiveService iElectiveService;

	@Override
	public void addCirriculum(AddCurriculumRequest request) {
		Curriculum c = Curriculum.builder().code(request.getCode()).name(request.getName())
				.organization(request.getDepartment()).generation(request.getGeneration()).build();
		iCirriculumRepository.save(c);
	}

	@Override
	public void updatingCurriculum(UpdateCirriculumRequest request) {
		Curriculum c = iCirriculumRepository.findById(request.getId());
		c.updateCurriculum(request);
		iCirriculumRepository.save(c);
	}

	@Override
	public void removeCirriculum(String id) {
		Curriculum cirriculum = iCirriculumRepository.findById(id);
		iCirriculumRepository.remove(cirriculum);

	}

	@Override
	public Curriculum getCirriculumById(String id) {
		return iCirriculumRepository.findById(id);
	}

	@Override
	public List<Curriculum> getAllCirriculums() {
		return iCirriculumRepository.findAll();
	}

	@Override
	public Curriculum getCirriculumByOrganization(Organization department, Generation generation) {
		return iCirriculumRepository.findCurriculumByOrganization(department, generation);
	}

	@Override
	public List<Curriculum> getCurriculumsByGeneration(Generation generation) {
		return iCirriculumRepository.getCurriculumsByGeneration(generation);
	}

	@Override
	public List<Curriculum> getCurriculumsByOrganization(Organization organization) {
		return iCirriculumRepository.getCurriculumsByOrganization(organization);
	}

	@Override
	public void addSubject(String id, Subject subject) {
		try {
			Curriculum c = getCirriculumById(id);
			List<Subject> subjects = c.getSubjects();
			addSubjectFunction(subjects, c, subject);
			iCirriculumRepository.save(c);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void addElectiveSubject(AddElectivesRequest request) {
		try {
			Curriculum c = getCirriculumById(request.getId());
			List<ElectiveSubject> ele = iElectiveService.getByGroup(request.getSubject().getId());
			List<ElectiveSubject> electives = c.getElectiveSubjects();
			for (ElectiveSubject s : ele) {
				if ((!electives.isEmpty()&&!electives.contains(s))||(electives.isEmpty())) {
					electives.add(s);
				}
			}
			c.setElectiveSubjects(electives);
			c.setTheoryCredit(c.getTheoryCredit() + request.getSubject().getCredit());
			iCirriculumRepository.save(c);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void addListSubjects(String id, List<Subject> subjects, List<ElectiveSubject> electives) {
		Curriculum c = getCirriculumById(id);
		if (!subjects.isEmpty()) {
			for (Subject subject : subjects) {
				addSubjectFunction(subjects, c, subject);
			}
		}
		if (electives.isEmpty()) {
			addElectivesFunction(electives, c);
		}
		iCirriculumRepository.save(c);
	}

	private void addSubjectFunction(List<Subject> subjects, Curriculum c, Subject subject) {
		if (!subjects.contains(subject)) {
			subjects.add(subject);
			c.setTheoryCredit(c.getTheoryCredit() + subject.getTheoryCredit());
			c.setSubjects(subjects);
		}
	}

	private void addElectivesFunction(List<ElectiveSubject> subjects, Curriculum c) {
		List<ElectiveSubject> electives = c.getElectiveSubjects();
		for (ElectiveSubject e : subjects) {
			if (!electives.contains(e)) {
				subjects.add(e);
				c.setElectiveSubjects(subjects);
			}
		}
	}

	@Override
	public List<Subject> getSubjectByCurriculum(String id) {
		Curriculum c = getCirriculumById(id);
		return c.getSubjects();
	}

	@Override
	public void addSubjectToCurriculum(AddSubjectToCurriculum request) {
		Curriculum c = getCirriculumById(request.getId());
		List<Subject> subjects = request.getSubjects();
		Integer credit = 0;
		for (Subject subject : subjects) {
			credit += subject.getTheoryCredit();
		}
		c.setSubjects(subjects);
		c.setTheoryCredit(credit);
		iCirriculumRepository.save(c);
	}

	@Override
	public List<ElectiveSubject> getElectiveByCurriculum(String id) {
		Curriculum c = getCirriculumById(id);
		return c.getElectiveSubjects();
	}

}
