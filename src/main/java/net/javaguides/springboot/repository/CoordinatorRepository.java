package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.Coordinator;

@Repository
public interface CoordinatorRepository extends JpaRepository<Coordinator, Long>{
	Coordinator findByEmail(String email);
}
