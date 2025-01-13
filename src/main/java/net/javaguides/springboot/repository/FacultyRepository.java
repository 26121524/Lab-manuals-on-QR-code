package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springboot.model.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long>{

}
