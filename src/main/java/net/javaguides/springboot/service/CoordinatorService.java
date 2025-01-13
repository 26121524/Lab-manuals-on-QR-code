package net.javaguides.springboot.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetailsService;

import net.javaguides.springboot.model.Coordinator;
import net.javaguides.springboot.web.dto.CoordinatorRegistrationDto;

import java.util.List;

@Lazy
public interface CoordinatorService extends UserDetailsService{
    List<Coordinator> getAllCoordinators();

    Coordinator saveCoordinator(Coordinator coordinator);

    Coordinator getCoordinatorById(Long id);

    Coordinator updateCoordinator(Coordinator coordinator);

    void deleteCoordinatorById(Long id);
    Coordinator save(CoordinatorRegistrationDto registrationDto);
}
