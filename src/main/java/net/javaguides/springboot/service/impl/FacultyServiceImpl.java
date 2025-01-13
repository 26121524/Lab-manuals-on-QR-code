package net.javaguides.springboot.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.Faculty;
import net.javaguides.springboot.repository.FacultyRepository;
import net.javaguides.springboot.service.FacultyService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

//implementation of methods declared in FacultyService
@Service
public class FacultyServiceImpl implements FacultyService{

	@PersistenceContext
	private EntityManager entityManager;
	private FacultyRepository facultyRepository;
	
	public FacultyServiceImpl(FacultyRepository facultyRepository) {
		super();
		this.facultyRepository = facultyRepository;
	}

	@Override
	@Transactional
	public List<Faculty> getAllFaculties() {
		System.out.println("Fetching all faculties...");
		return facultyRepository.findAll();
	}

	@Override
	public Faculty saveFaculty(Faculty faculty) {
		return facultyRepository.save(faculty);
	}

	@Override
	public Faculty getFacultyById(Long id) {
		return facultyRepository.findById(id).get();
	}

	@Override
	public Faculty updateFaculty(Faculty faculty) {
		return facultyRepository.save(faculty);
	}

	@Override
	@Transactional
	public void deleteFacultyById(Long id) {
		entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS=0").executeUpdate();
		facultyRepository.deleteById(id);
		entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS=1").executeUpdate();
		//facultyRepository.deleteById(id);
	}

}
