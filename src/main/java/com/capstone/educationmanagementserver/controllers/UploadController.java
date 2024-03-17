package com.capstone.educationmanagementserver.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.capstone.educationmanagementserver.general.Response;
import com.capstone.educationmanagementserver.general.Response.Status;
import com.capstone.educationmanagementserver.models.Curriculum;
import com.capstone.educationmanagementserver.repositories.interfaces.ICurriculumRepository;
import com.capstone.educationmanagementserver.requests.curriculum.AddProcessRequest;
import com.google.common.io.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/image")
@SuppressWarnings("rawtypes")
public class UploadController {
	@Autowired
	ICurriculumRepository iCurriculumRepository;

	public static String UPLOAD_DIRECTORY = "D:/EIU/Capstone/education-management/src/curriculum";

	public Resource displayUploadForm(String fileName) {
		Path path = Paths.get(UPLOAD_DIRECTORY);
		try {
			Path file = path.resolve(fileName);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/showImage")
	public Response getImage(@RequestParam(value = "fileName", required = true) String fileName) throws IOException {
		Resource r = displayUploadForm(fileName);
		return Response.ok().setPayload(new File(r.getFile().getPath()).toURI().toURL());

	}

	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Response uploadImage(@ModelAttribute AddProcessRequest request) {
		MultipartFile file = request.getFile();
		Curriculum c = iCurriculumRepository.findById(request.getId());
		String code = c.getCode()+"."+ Files.getFileExtension(file.getOriginalFilename());
		StringBuilder fileNames = new StringBuilder();
		Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, code);
		fileNames.append(code);
		try {
			java.nio.file.Files.write(fileNameAndPath, file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		c.setPathImage(fileNames.toString());
		iCurriculumRepository.save(c);
		return Response.ok().setStatus(Status.OK);
	}
}