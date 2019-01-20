package spring.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author CBoom (cboom.trf@gmail.com)
 *
 */
@Entity
public class Role implements Serializable {

	private static final long serialVersionUID = -1762205109139723956L;

	@Id
	@GeneratedValue
	private long id;
	
	@NotNull(message= "Please enter a name.")
	@Size(min = 3, max = 16, message = "Name must be between 3 and 16 characters long.")
	private String name;
	
	//mapped by refers to object variable name role in User
	@OneToMany (fetch=FetchType.LAZY, mappedBy="role", cascade = CascadeType.ALL)
	private List<User> users;

	public Role() {
		super();
//		this.id = System.nanoTime();
	}

	public Role(String name) {
//		this.id = System.nanoTime();
		this.name = name;
	}
	
	public Role(long id, String name) {
		this.id = id;
		this.name = name;
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
}
