package com.drone;
import javax.persistence.*;
import javax.validation.constraints.*;
@Entity
@Table(name="DroneDetails")
public class DroneDetails{

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;  

	@Size(min=1,message="Please enter drone no")
	private String droneno;
	@Size(min=1,message="Please enter drone model")
	private String dronemodel;
	
	@Size(min=1,message="Please enter purchased date")
	private String purchasedate;

	
	
public void setDroneno(String droneno) {
	this.droneno = droneno;
}
public String getDroneno() {
	return droneno;
}

public void setDronemodel(String dronemodel) {
	this.dronemodel = dronemodel;
}
public String getDronemodel() {
	return dronemodel;
}

public int getId() {  
    return id;  
}  
public void setId(int id) {  
    this.id = id;  
}  
	
public void setPurchasedate(String purchasedate) {
	this.purchasedate = purchasedate;
}
public String getPurchasedate() {
	return purchasedate;
}


}