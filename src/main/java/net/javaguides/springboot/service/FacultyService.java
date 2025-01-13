package net.javaguides.springboot.service;

import java.util.List;

import net.javaguides.springboot.model.Faculty;

public interface FacultyService {
	List<Faculty> getAllFaculties();
	
	Faculty saveFaculty(Faculty faculty);
	
	Faculty getFacultyById(Long id);
	
	Faculty updateFaculty(Faculty faculty);
	
	void deleteFacultyById(Long id);
}
