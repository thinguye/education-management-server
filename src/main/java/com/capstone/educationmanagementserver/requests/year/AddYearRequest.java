package com.capstone.educationmanagementserver.requests.year;


import java.util.Date;

import lombok.Data;

@Data
public class AddYearRequest {
	String code;
	String name;
	Date startYear;
	Date endYear;
}
