package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.ExperimentLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperimentLinkRepository extends JpaRepository<ExperimentLink, Long> {
}
