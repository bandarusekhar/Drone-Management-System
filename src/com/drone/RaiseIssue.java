package com.drone;
import javax.persistence.*;
import javax.validation.constraints.*;
@Entity
@Table(name="RaiseIssue")
public class RaiseIssue{

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;  

	@Size(min=1,message="Please enter drone id")
	private String droneid;

	@Size(min=1,message="Please enter droneno")
	private String droneno;
	@Size(min=1,message="Please enter drone model")
	private String dronemodel;
	@Size(min=1,message="Please enter phone no")
	private String managerphone;

	@Size(min=1,message="Please enter manager name")
	private String managername;

	@Size(min=1,message="Please enter issue description")
	private String description;
	private String issuedate;
	private String status;

public void setIssuedate(String issuedate) {
	this.issuedate = issuedate;
}
public String getIssuedate() {
	return issuedate;
}

public void setStatus(String status) {
	this.status = status;
}
public String getStatus() {
	return status;
}



	
public void setDroneid(String droneid) {
	this.droneid = droneid;
}
public String getDroneid() {
	return droneid;
}

public void setDroneno(String droneno) {
	this.droneno = droneno;
}
public String getDroneno() {
	return droneno;
}

public int getId() {  
    return id;  
}  
public void setId(int id) {  
    this.id = id;  
}  
	
public void setDronemodel(String dronemodel) {
	this.dronemodel = dronemodel;
}
public String getDronemodel() {
	return dronemodel;
}

public void setManagerphone(String managerphone) {
	this.managerphone = managerphone;
}
public String getManagerphone() {
	return managerphone;
}


public void setManagername(String managername) {
	this.managername = managername;
}
public String getManagername() {
	return managername;
}

public void setDescription(String description) {
	this.description = description;
}
public String getDescription() {
	return description;
}


}