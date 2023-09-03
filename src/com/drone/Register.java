package com.drone;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
@Entity
@Table(name="Register")
public class Register{

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;  

	@Size(min=1,message="Please enter firstname")
	private String firstname;
	@Size(min=1,message="Please enter last name")
	private String lastname;
		@Size(min=1,message="Please enter phone no")
	private String phoneno;

	@Size(min=1,message="Please enter email id")
	private String email;

	@Size(min=1,message="Please enter password")
	private String password;
	private String usertype;

	@Size(min=1,message="Please enter your skills")
	private String skills;

	
public void setSkills(String skills) {
	this.skills = skills;
}
public String getSkills() {
	return skills;
}

public void setUsertype(String usertype) {
	this.usertype = usertype;
}
public String getUsertype() {
	return usertype;
}

public int getId() {  
    return id;  
}  
public void setId(int id) {  
    this.id = id;  
}  
	
public void setEmail(String email) {
	this.email = email;
}
public String getEmail() {
	return email;
}

public void setPhoneno(String phoneno) {
	this.phoneno = phoneno;
}
public String getPhoneno() {
	return phoneno;
}


public void setPassword(String password) {
	this.password = password;
}
public String getPassword() {
	return password;
}

public void setFirstname(String firstname) {
	this.firstname = firstname;
}
public String getFirstname() {
	return firstname;
}

public void setLastname(String lastname) {
	this.lastname = lastname;
}
public String getLastname() {
	return lastname;
}
}