package spring.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author CBoom (cboom.trf@gmail.com)
 *
 */
@Entity
public class User {
	
	@Id
	@GeneratedValue
	private long id;
	
	@NotNull(message= "Please enter a name.")
	@Size(min = 3, max = 16, message = "Name must be between 3 and 16 characters long.")
	private String name;
	
	@Size(min = 1, message = "StreetAdress is required.")
	private String streetAddress;
	
	@Size(min = 1, message = "City is required.")
	private String city;
	
	@Size(min = 1, message = "Housenumber is required.")
	private String houseNumber;
	
	@ManyToOne
	private Role role;

	public User() {
		super();
//		this.id = System.nanoTime();
	}
	
	public User(String name, String streetAddress, String city, String houseNumber, Role role) {
//		this.id = System.nanoTime();
		this.name = name;
		this.streetAddress = streetAddress;
		this.city = city;
		this.houseNumber = houseNumber;
		this.role = role;
	}

	public User(long id, String name, String streetAddress, String city, String houseNumber, Role role) {
		this.id = id;
		this.name = name;
		this.streetAddress = streetAddress;
		this.city = city;
		this.houseNumber = houseNumber;
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
