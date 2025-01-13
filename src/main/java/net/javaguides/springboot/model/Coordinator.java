package net.javaguides.springboot.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;

//Coordinator entity to create coordinator table and fetch coordinator's data
@Entity
@Table(name =  "coordinator", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Coordinator {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	private String email;
	
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "coordinators_roles",
			joinColumns = @JoinColumn(
		            name = "coordinator_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
				            name = "crole_id", referencedColumnName = "id"))
	
	private Collection<Crole> croles;
	
	public Coordinator() {
		
	}
	
	public Coordinator(String firstName, String lastName, String email, String password, Collection<Crole> croles) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.croles = croles;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Collection<Crole> getRoles() {
		return croles;
	}
	public void setRoles(Collection<Crole> croles) {
		this.croles = croles;
	}

}
