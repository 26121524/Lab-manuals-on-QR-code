package net.javaguides.springboot.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import net.javaguides.springboot.model.Coordinator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.Crole;
import net.javaguides.springboot.repository.CoordinatorRepository;
import net.javaguides.springboot.web.dto.CoordinatorRegistrationDto;

import javax.transaction.Transactional;

//implementation of all methods declared in CoordinatorService
@Service
@Lazy
public class CoordinatorServiceImpl implements CoordinatorService {

	private CoordinatorRepository coordinatorRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder1;
	
	public CoordinatorServiceImpl(@Lazy CoordinatorRepository coordinatorRepository) {
		super();
		this.coordinatorRepository = coordinatorRepository;
		//this.passwordEncoder1=passwordEncoder;
	}

	@Override
	public Coordinator save(CoordinatorRegistrationDto registrationDto) {
		Coordinator coordinator = new Coordinator(registrationDto.getFirstName(),
				registrationDto.getLastName(), registrationDto.getEmail(),
				passwordEncoder1.encode(registrationDto.getPassword()), Arrays.asList(new Crole("ROLE_COORDINATOR")));
		
		return coordinatorRepository.save(coordinator);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		Coordinator coordinator = coordinatorRepository.findByEmail(username);
		if(coordinator == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(coordinator.getEmail(), coordinator.getPassword(), mapRolesToAuthorities(coordinator.getRoles()));
	}
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Crole> croles){
		return croles.stream().map(crole -> new SimpleGrantedAuthority(crole.getName())).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<Coordinator> getAllCoordinators() {
		System.out.println("Fetching all coordinators...");
		return coordinatorRepository.findAll();
	}

	@Override
	public Coordinator saveCoordinator(Coordinator coordinator) {
		return coordinatorRepository.save(coordinator);
	}

	@Override
	public Coordinator getCoordinatorById(Long id) {
		return coordinatorRepository.findById(id).get();
	}

	@Override
	public Coordinator updateCoordinator(Coordinator coordinator) {
		return coordinatorRepository.save(coordinator);
	}

	@Override
	@Transactional
	public void deleteCoordinatorById(Long id) {
		coordinatorRepository.deleteById(id);
	}


}
