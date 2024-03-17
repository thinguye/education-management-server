package com.capstone.educationmanagementserver.services;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import com.capstone.educationmanagementserver.models.Quarter;
import com.capstone.educationmanagementserver.models.Student;
import com.capstone.educationmanagementserver.models.Year;
import com.capstone.educationmanagementserver.repositories.interfaces.IQuarterRepository;
import com.capstone.educationmanagementserver.requests.quarter.AddQuarterRequest;
import com.capstone.educationmanagementserver.requests.quarter.UpdateQuarterRequest;
import com.capstone.educationmanagementserver.services.interfaces.IQuarterService;
import com.capstone.educationmanagementserver.services.interfaces.IYearService;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
		try {
			InputStream inputStream = file.getInputStream();
			try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
				XSSFSheet worksheet = workbook.getSheetAt(0);
				Year year = null;
				for (int i = 1; i <= worksheet.getPhysicalNumberOfRows(); i++) {
					XSSFRow row = worksheet.getRow(i);
					String s = row.getCell(0).getStringCellValue();
					if (s.trim().equalsIgnoreCase("Năm")) {
						year = iYearService.getYearByName(row.getCell(1).getStringCellValue());
					} else if (s.trim().contains("Học kì") || s.trim().contains("Học kỳ")) {
						Quarter temp = iQuarterRepository.findQuarterByQuarterYear(s, year);
//						String dateS = String.valueOf(row.getCell(1).getRawValue());
//						Date dateStart = DateUtils.parseDate(dateS, new String[] { "yyyy-MM-dd HH:mm:ss", "dd/MM/yyyy" });
						Date dateStart = row.getCell(1).getDateCellValue();
//						String dateE =String.valueOf(row.getCell(2).getRawValue());
//						Date dateEnd = DateUtils.parseDate(dateE, new String[] { "yyyy-MM-dd HH:mm:ss", "dd/MM/yyyy" });
						Date dateEnd = row.getCell(2).getDateCellValue();
						if (temp==null) {
							Quarter q = Quarter.builder().name(s).year(year).start(dateStart).end(dateEnd).build();
							iQuarterRepository.save(q);
						}else {
							temp.setStart(dateStart);
							temp.setEnd(dateEnd);
							iQuarterRepository.save(temp);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void addQuarter(AddQuarterRequest request) {
		Quarter quarter = Quarter.builder().name(request.getName()).year(request.getYear()).start(request.getStart())
				.end(request.getEnd()).build();
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
