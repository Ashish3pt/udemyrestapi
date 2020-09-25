package com.example.demo.entities;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonView;



@Entity
@Table(name="user")
//@JsonFilter(value="userFilter") -Dyanmic Jackson Mapping 
//@JsonIgnoreProperties({"firstname","lastname"}) - Static Filtering 
public class User extends RepresentationModel<User>{
	
	@Id
	@GeneratedValue
	@JsonView(Views.External.class)
	private Long userid;
	
	
	@NotEmpty(message = "Username is a mandatory field")
	@Column(name="USER_NAME", length=50, nullable=false, unique=true)
	@JsonView(Views.External.class)
	private String username;
	
	@Size(min=2, message="First name must be atleast 2 chars")
	@Column(name="FIRST_NAME", length=50, nullable=false)
	@JsonView(Views.External.class)
	private String firstname;
	
	@Column(name="LAST_NAME", length=50, nullable=false)
	@JsonView(Views.External.class)
	private String lastname;
	
	@Column(name="EMAIL_ADDRESS", length=50, nullable=false)
	@JsonView(Views.External.class)
	private String email;
	
	@Column(name="ROLE", length=50, nullable=false)
	@JsonView(Views.Internal.class)
	private String role;
	
	@Column(name="SSN", length=50, nullable=true, unique=true)
	//@JsonIgnore - Static Filtering
	@JsonView(Views.Internal.class)
	private String ssn;
	
	@OneToMany(mappedBy="user")
	@JsonView(Views.Internal.class)
	private List<Order> orders;
	
	@Column(name="ADDRESS")
	private String address;
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User()
	{
		
	}

	
	public User(Long userid, @NotEmpty(message = "Username is a mandatory field") String username,
			@Size(min = 2, message = "First name must be atleast 2 chars") String firstname, String lastname,
			String email, String role, String ssn, List<Order> orders, String address) {
		super();
		this.userid = userid;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
		this.orders = orders;
		this.address = address;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long id) {
		this.userid = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "User [id=" + userid + ", username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + ", orders=" + orders + "]";
	}
	
	
	
}
