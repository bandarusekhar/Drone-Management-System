package com.drone;
import javax.validation.constraints.*;
import javax.persistence.*;

@Entity
@Table(name="Admin")
public class Admin {
	@Id
	@Column(name="username")
	@Size(min=1,message="please enter username")
	private String username;
	@Size(min=1,message="please enter password")
	private String password;

public String getUsername() {
	return username;
}
public String getPassword() {
	return password;
}

public void setUsername(String username) {
	this.username = username;
}

public void setPassword(String password) {
	this.password = password;
}

}