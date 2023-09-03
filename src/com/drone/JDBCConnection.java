package com.drone;  
import org.eclipse.persistence.config.*;
import java.sql.*;
import java.util.*;
import javax.persistence.*;  

public class JDBCConnection{

	static Properties properties = null;

public static void load() {
	if(properties == null) {
		properties = new Properties();
		properties.setProperty(PersistenceUnitProperties.ECLIPSELINK_PERSISTENCE_XML,"META-INF/persistence.xml");
	}
}

public static boolean getadminValidation(String uname,String pass){
	boolean status = false;
	try{
		load();
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("User");  
		EntityManager manager = factory.createEntityManager();  
		Query query = manager.createQuery("SELECT e FROM Admin e");
		Collection<Admin> admin_list = (Collection<Admin>) query.getResultList();
		for(Admin admin : admin_list) {
			if(admin.getUsername().equals(uname) && admin.getPassword().equals(pass)) {
				status = true;
				break;
			}
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	return status;
}	

public static String register(Register register) {
	String error = "internal error occured in saving registration data";
	try{
		load();
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Register");  
        EntityManager manager = factory.createEntityManager();  
        manager.getTransaction().begin(); 
		manager.persist(register);
		manager.getTransaction().commit();  
		error = "Registration successful";
       
	}catch(Exception e){
		e.printStackTrace();
	}
	return error;
}

public static Register getManagerDetails(String email){
	Register reg = null;
	try{
		load();
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Register");  
		EntityManager manager = factory.createEntityManager();  
		Query query = manager.createQuery("SELECT e FROM Register e");
		Collection<Register> list = (Collection<Register>) query.getResultList();
		for(Register register : list) {
			if(register.getEmail().equals(email) && register.getUsertype().equals("Manager")) {
				reg = register;
				break;
			}
		}
	}catch(Exception exc){
		exc.printStackTrace();
	}
	return reg;
}

public static ArrayList<String> getOperatorsList(){
	ArrayList<String> list = new ArrayList<String>();
	try{
		load();
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Register");  
		EntityManager manager = factory.createEntityManager();  
		Query query = manager.createQuery("SELECT e FROM Register e");
		Collection<Register> lists = (Collection<Register>) query.getResultList();
		for(Register register : lists) {
			if(register.getUsertype().equals("Operator")) {
				list.add(register.getEmail());
			}
		}
	}catch(Exception exc){
		exc.printStackTrace();
	}
	return list;
}


public static boolean managerLogin(String user,String pass){
	boolean status = false;
	try{
		load();
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Register");  
		EntityManager manager = factory.createEntityManager();  
		Query query = manager.createQuery("SELECT e FROM Register e");
		Collection<Register> list = (Collection<Register>) query.getResultList();
		for(Register register : list) {
			if(register.getEmail().equals(user) && register.getPassword().equals(pass) && register.getUsertype().equals("Manager")) {
				status = true;
				break;
			}
		}
	}catch(Exception exc){
		exc.printStackTrace();
	}
	return status;
}

public static String addDroneDetails(DroneDetails dd) {
	String error = "internal error occured in saving drone details";
	try{
		load();
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("DroneDetails");  
        EntityManager manager = factory.createEntityManager();  
        manager.getTransaction().begin(); 
		manager.persist(dd);
		manager.getTransaction().commit();  
		error = "New drone details added successfully";
       
	}catch(Exception exc){
		exc.printStackTrace();
	}
	return error;
}

public static ArrayList<DroneDetails> getDroneData(){
	ArrayList<DroneDetails> drone_list = new ArrayList<DroneDetails>();
	try{
		load();
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("DroneDetails");  
		EntityManager manager = factory.createEntityManager();  
		Query query = manager.createQuery("SELECT e FROM DroneDetails e");
		Collection<DroneDetails> data = (Collection<DroneDetails>) query.getResultList();
		for(DroneDetails dd : data) {
			drone_list.add(dd);
		}
	}catch(Exception exc){
		exc.printStackTrace();
	}
	return drone_list;
}

public static void solveIssue(int id) {
	try{
		load();
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("RaiseIssue");  
        EntityManager manager = factory.createEntityManager();  
        manager.getTransaction().begin(); 
		RaiseIssue ri = manager.find(RaiseIssue.class,id);
		ri.setStatus("Issue solved");
        manager.merge(ri);
		manager.getTransaction().commit();  
	}catch(Exception exc){
		exc.printStackTrace();
	}
}

public static void updateRaiseIssue(int id) {
	try{
		load();
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("RaiseIssue");  
        EntityManager manager = factory.createEntityManager();  
        manager.getTransaction().begin(); 
		RaiseIssue ri = manager.find(RaiseIssue.class,id);
		ri.setStatus("Assigned");
        manager.merge(ri);
		manager.getTransaction().commit();  
	}catch(Exception exc){
		exc.printStackTrace();
	}
}
public static void deleteDrone(String id) {
	try{
		load();
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("DroneDetails");  
        EntityManager manager = factory.createEntityManager();  
        manager.getTransaction().begin(); 
		DroneDetails dd = manager.find(DroneDetails.class,Integer.parseInt(id));
        manager.remove(dd);
		manager.getTransaction().commit();  
	}catch(Exception exc){
		exc.printStackTrace();
	}
}

public static String editDroneDetails(DroneDetails dd) {
	String error = "internal error occured in editing drone details";
	try{
		load();
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("DroneDetails");  
        EntityManager manager = factory.createEntityManager();  
        manager.getTransaction().begin(); 
		manager.merge(dd);
		manager.getTransaction().commit();  
		error = "Drone details edited successfully";
       
	}catch(Exception exc){
		exc.printStackTrace();
	}
	return error;
}

public static String raiseIssue(RaiseIssue ri) {
	String error = "internal error occured in saving issue details";
	try{
		load();
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("RaiseIssue");  
        EntityManager manager = factory.createEntityManager();  
        manager.getTransaction().begin(); 
		manager.persist(ri);
		manager.getTransaction().commit();  
		error = "Your drone issue details added successfully";
       
	}catch(Exception exc){
		exc.printStackTrace();
	}
	return error;
}

public static String assignOperator(AssignOperator ao) {
	String error = "internal error occured in saving assign operator details";
	try{
		load();
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("AssignOperator");  
        EntityManager manager = factory.createEntityManager();  
        manager.getTransaction().begin(); 
		manager.persist(ao);
		manager.getTransaction().commit();  
		error = ao.getOperator()+" Operator successfully assigned to : "+ao.getTicketid();
       
	}catch(Exception exc){
		exc.printStackTrace();
	}
	return error;
}

public static ArrayList<RaiseIssue> getRaiseIssue(){
	ArrayList<RaiseIssue> issue_list = new ArrayList<RaiseIssue>();
	try{
		load();
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("RaiseIssue");  
		EntityManager manager = factory.createEntityManager();  
		Query query = manager.createQuery("SELECT e FROM RaiseIssue e");
		Collection<RaiseIssue> data = (Collection<RaiseIssue>) query.getResultList();
		for(RaiseIssue dd : data) {
			if(dd.getStatus().equals("Pending"))
				issue_list.add(dd);
		}
	}catch(Exception exc){
		exc.printStackTrace();
	}
	return issue_list;
}

public static boolean operatorLogin(String user,String pass){
	boolean status = false;
	try{
		load();
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Register");  
		EntityManager manager = factory.createEntityManager();  
		Query query = manager.createQuery("SELECT e FROM Register e");
		Collection<Register> list = (Collection<Register>) query.getResultList();
		for(Register register : list) {
			if(register.getEmail().equals(user) && register.getPassword().equals(pass) && register.getUsertype().equals("Operator")) {
				status = true;
				break;
			}
		}
	}catch(Exception exc){
		exc.printStackTrace();
	}
	return status;
}

public static ArrayList<RaiseIssue> getAssignedIssue(String user_email) {
	ArrayList<RaiseIssue> issue_list = new ArrayList<RaiseIssue>();
	ArrayList<String> temp = new ArrayList<String>();
	try{
		load();
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("AssignOperator");  
		EntityManager manager = factory.createEntityManager();  
		Query query = manager.createQuery("SELECT e FROM AssignOperator e");
		Collection<AssignOperator> list = (Collection<AssignOperator>) query.getResultList();
		for(AssignOperator ao : list) {
			if(ao.getOperator().equals(user_email)) {
				temp.add(ao.getTicketid());
			}
		}

		factory = Persistence.createEntityManagerFactory("RaiseIssue");  
		manager = factory.createEntityManager();  
		query = manager.createQuery("SELECT e FROM RaiseIssue e");
		Collection<RaiseIssue> ri_list = (Collection<RaiseIssue>) query.getResultList();
		for(RaiseIssue ri : ri_list) {
			String id = Integer.toString(ri.getId());
			if(temp.contains(id) && ri.getStatus().equals("Assigned")){
				issue_list.add(ri);
			}
		}
	}catch(Exception exc){
		exc.printStackTrace();
	}
	return issue_list;
}

public static ArrayList<RaiseIssue> getSolvedIssue(String user){
	ArrayList<RaiseIssue> issue_list = new ArrayList<RaiseIssue>();
	try{
		load();
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("RaiseIssue");  
		EntityManager manager = factory.createEntityManager();  
		Query query = manager.createQuery("SELECT e FROM RaiseIssue e");
		Collection<RaiseIssue> data = (Collection<RaiseIssue>) query.getResultList();
		for(RaiseIssue dd : data) {
			if(dd.getManagername().equals(user))
				issue_list.add(dd);
		}
	}catch(Exception exc){
		exc.printStackTrace();
	}
	return issue_list;
}
}