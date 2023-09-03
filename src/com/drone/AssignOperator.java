package com.drone;
import javax.persistence.*;
import javax.validation.constraints.*;
@Entity
@Table(name="AssignOperator")
public class AssignOperator{

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;  

	@Size(min=1,message="Please enter ticket id")
	private String ticketid;
	@Size(min=1,message="Please enter operator name")
	private String operator;
	
	private String assigndate;
	private String anyquestion;
	private String answer;
	
public void setTicketid(String ticketid) {
	this.ticketid = ticketid;
}
public String getTicketid() {
	return ticketid;
}

public void setOperator(String operator) {
	this.operator = operator;
}
public String getOperator() {
	return operator;
}

public int getId() {  
    return id;  
}  
public void setId(int id) {  
    this.id = id;  
}  
	
public void setAssigndate(String assigndate) {
	this.assigndate = assigndate;
}
public String getAssigndate() {
	return assigndate;
}

public void setAnyquestion(String anyquestion) {
	this.anyquestion = anyquestion;
}
public String getAnyquestion() {
	return anyquestion;
}

public void setAnswer(String answer) {
	this.answer = answer;
}
public String getAnswer() {
	return answer;
}

}