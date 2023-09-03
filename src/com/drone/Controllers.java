package com.drone;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.servlet.ModelAndView;  
import org.springframework.ui.Model;  
import java.util.ArrayList;  
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import java.util.Map;
import org.springframework.validation.BindingResult;
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.ModelAttribute;  
import org.springframework.web.bind.annotation.RequestMapping;  
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Controller  
@SessionAttributes({"username"})
public class Controllers { 
		
	private String user_email;
@RequestMapping("/Admin") 
public ModelAndView admin(Model model){
	Admin admin = new Admin();
	model.addAttribute("Admin", admin);
	return new ModelAndView("Admin","command",admin);  
}

@RequestMapping(value="/AdminValidation",method = RequestMethod.POST)
public ModelAndView validateAdmin(@Valid @ModelAttribute("Admin") Admin admin, BindingResult binding, Model model){
	if(binding.hasErrors()) {
		return new ModelAndView("Admin","command",admin);
	}  else {
		String navigate = null;
		boolean status = JDBCConnection.getadminValidation(admin.getUsername(),admin.getPassword());
		if (status) {
			model.addAttribute("msg", "Welcome "+admin.getUsername());
			navigate = "AdminHome"; 
		} else {
			navigate = "Admin";
			model.addAttribute("msg", "Incorrect login details");
        }
		return new ModelAndView(navigate);
	}
}


@RequestMapping("/index") 
public String index(Model model) {
	return "index";
}
	
@RequestMapping("/AdminHome") 
public String adminHome(Model model) {
	return "AdminHome";
}
@RequestMapping("/Logout") 
public String logout(Model model) {
	Map<String,Object> session = model.asMap();
	session.remove("username");
	return "index";
}

@RequestMapping("/Register") 
public ModelAndView register(Model model){
	Register register = new Register();
	model.addAttribute("Register", register);
	return new ModelAndView("Register","command",register);  
}

@RequestMapping("/OperatorRegister") 
public ModelAndView operatorRegister(Model model){
	Register register = new Register();
	model.addAttribute("OperatorRegister", register);
	return new ModelAndView("OperatorRegister","command",register);  
}
@RequestMapping(value="/StoreOperatorRegister",method = RequestMethod.POST) 
public ModelAndView storeOperatorRegister(@Valid @ModelAttribute("OperatorRegister") Register register, BindingResult binding,Model model) {
	if(binding.hasErrors()) {  
		return new ModelAndView("OperatorRegister","command",register);  
	} 
	if(!validateEmail(register.getEmail())){
		model.addAttribute("status", "Please enter correct Email ID");
		return new ModelAndView("OperatorRegister","command",register);  
	}  
	String status = JDBCConnection.register(register);
	model.addAttribute("status", status);
    return new ModelAndView("OperatorRegister");
}  

public boolean validateEmail(String email){
	 boolean check = false;
	 String regEx = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";
	 Pattern pattern = Pattern.compile(regEx);
	 Matcher matcher = pattern.matcher(email);
	 if(matcher.find())
		check = true;
	 else
		check = false;
     return check;
}

@RequestMapping(value="/StoreRegister",method = RequestMethod.POST) 
public ModelAndView storeRegister(@Valid @ModelAttribute("Register") Register register, BindingResult binding,Model model) {
	if(binding.hasErrors()) {  
		return new ModelAndView("Register","command",register);  
	} 
	if(!validateEmail(register.getEmail())){
		model.addAttribute("status", "Please enter correct Email ID");
		return new ModelAndView("Register","command",register);  
	}  
	String status = JDBCConnection.register(register);
	model.addAttribute("status", status);
    return new ModelAndView("Register");
}  

@RequestMapping(value="/ManagerValidation",method = RequestMethod.POST)
public ModelAndView managerValidation(@Valid @ModelAttribute("Manager") Register register, BindingResult binding, Model model){
	if(binding.hasErrors()) {
		return new ModelAndView("Manager","command",register);
	}  else {
		String navigate = null;
		boolean status = JDBCConnection.managerLogin(register.getEmail(),register.getPassword());
		if (status) {
			user_email = register.getEmail();
			model.addAttribute("status", "Welcome "+register.getEmail());
			navigate = "ManagerHome"; 
		} else {
			navigate = "Manager";
			model.addAttribute("status", "Incorrect login details");
        }
		return new ModelAndView(navigate);
	}
}

@RequestMapping("/Manager") 
public ModelAndView manager(Model model){
	Register register = new Register();
	model.addAttribute("Manager", register);
	return new ModelAndView("Manager","command",register);  
}

@RequestMapping("/CreateDrone") 
public ModelAndView createDrone(Model model){
	DroneDetails dd = new DroneDetails();
	model.addAttribute("CreateDrone", dd);
	return new ModelAndView("CreateDrone","command",dd);  
}
@RequestMapping(value="/StoreDroneDetails",method = RequestMethod.POST) 
public ModelAndView storeDroneDetails(@Valid @ModelAttribute("CreateDrone") DroneDetails dd, BindingResult binding,Model model) {
	if(binding.hasErrors()) {  
		return new ModelAndView("CreateDrone","command",dd);  
	} 
	String status = JDBCConnection.addDroneDetails(dd);
	model.addAttribute("status", status);
    return new ModelAndView("CreateDrone");
} 

@RequestMapping("/DeleteDrone")  
public ModelAndView deleteDrone() {
	List<DroneDetails> drone_list = JDBCConnection.getDroneData(); 
	return new ModelAndView("DeleteDrone","list",drone_list);  
} 
@RequestMapping(value="/DeleteAction",method = RequestMethod.GET) 
public ModelAndView deleteAction(@RequestParam("t1") String id,Model model) {
	JDBCConnection.deleteDrone(id);
	List<DroneDetails> drone_list = JDBCConnection.getDroneData(); 
	return new ModelAndView("DeleteDrone","list",drone_list);  
} 

@RequestMapping("/EditDrone") 
public ModelAndView editDrone(@RequestParam("t1") String id,@RequestParam("t2") String droneno,  @RequestParam("t3") String dronemodel,  @RequestParam("t4") String date,Model model) {
	model.addAttribute("id",id);
	model.addAttribute("droneno",droneno);
	model.addAttribute("dronemodel",dronemodel);
	model.addAttribute("purchasedate",date);
	
	DroneDetails dd = new DroneDetails();
	dd.setId(Integer.parseInt(id));
	dd.setDroneno(droneno);
	dd.setDronemodel(dronemodel);
	dd.setPurchasedate(date);
	model.addAttribute("EditDrone", dd);
	return new ModelAndView("EditDrone"); 
}
@RequestMapping(value="/EditDroneDetails",method = RequestMethod.POST) 
public ModelAndView editDroneDetails(@Valid @ModelAttribute("EditDrone") DroneDetails dd, BindingResult binding,Model model) {
	if(binding.hasErrors()) {  
		return new ModelAndView("EditDrone","command",dd);  
	} 
	String status = JDBCConnection.editDroneDetails(dd);
	model.addAttribute("status", status);
    List<DroneDetails> drone_list = JDBCConnection.getDroneData(); 
	return new ModelAndView("DeleteDrone","list",drone_list);  
}  

@RequestMapping("/RaiseIssue")  
public ModelAndView raiseIssue() {
	List<DroneDetails> drone_list = JDBCConnection.getDroneData(); 
	return new ModelAndView("RaiseIssue","list",drone_list);  
} 
@RequestMapping("/RaiseIssueRequest") 
public ModelAndView raiseIssueRequest(@RequestParam("t1") String id,@RequestParam("t2") String droneno,  @RequestParam("t3") String dronemodel,Model model) {
	model.addAttribute("id",id);
	model.addAttribute("droneno",droneno);
	model.addAttribute("dronemodel",dronemodel);
	Register register = JDBCConnection.getManagerDetails(user_email);
	model.addAttribute("managerphone",register.getPhoneno());
	model.addAttribute("managername",register.getEmail());
	
	RaiseIssue ri = new RaiseIssue();
	ri.setDroneid(id);
	ri.setDroneno(droneno);
	ri.setDronemodel(dronemodel);
	ri.setManagerphone(register.getPhoneno());
	ri.setManagername(register.getEmail());
	model.addAttribute("RaiseIssue", ri);
	return new ModelAndView("RaiseIssuePage"); 
}
@RequestMapping(value="/RaiseIssuePageAction",method = RequestMethod.POST) 
public ModelAndView raiseIssuePageAction(@Valid @ModelAttribute("RaiseIssue") RaiseIssue ri, BindingResult binding,Model model) {
	if(binding.hasErrors()) {  
		return new ModelAndView("RaiseIssue","command",ri);  
	} 
	java.util.Date dd = new java.util.Date();
	java.sql.Date date = new java.sql.Date(dd.getTime());
	ri.setStatus("Pending");
	ri.setIssuedate(date.toString());
	String status = JDBCConnection.raiseIssue(ri);
	model.addAttribute("status", status);
    return new ModelAndView("RaiseIssue");
} 

@ModelAttribute("getOperators")  
public List<String> getOperators() {
	List<String> list = JDBCConnection.getOperatorsList(); 
	return list;  
} 

@RequestMapping("/ViewRaiseTicket") 
public ModelAndView viewRaiseTicket(Model model){
	ArrayList<RaiseIssue> list = JDBCConnection.getRaiseIssue();
	return new ModelAndView("ViewRaiseTicket","list",list);
}

@RequestMapping("/AssignOperator") 
public ModelAndView assignOperator(@RequestParam("t1") String id,Model model) {
	model.addAttribute("id",id);
		
	AssignOperator ao = new AssignOperator();
	ao.setTicketid(id);
	model.addAttribute("AssignOperator", ao);
	return new ModelAndView("AssignOperator"); 
}

@RequestMapping(value="/AssignOperatorAction",method = RequestMethod.POST) 
public ModelAndView storeDroneDetails(@Valid @ModelAttribute("AssignOperator") AssignOperator ao, BindingResult binding,Model model) {
	if(binding.hasErrors()) {  
		return new ModelAndView("AssignOperator","command",ao);  
	} 
	java.util.Date dd = new java.util.Date();
	java.sql.Date date = new java.sql.Date(dd.getTime());
	ao.setAssigndate(date.toString());
	ao.setAnyquestion("none");
	ao.setAnswer("none");
	String status = JDBCConnection.assignOperator(ao);
	JDBCConnection.updateRaiseIssue(Integer.parseInt(ao.getTicketid()));
	model.addAttribute("status", status);
    return new ModelAndView("ViewRaiseTicket");
} 

@RequestMapping(value="/OperatorValidation",method = RequestMethod.POST)
public ModelAndView operatorValidation(@Valid @ModelAttribute("Register") Register register, BindingResult binding, Model model){
	if(binding.hasErrors()) {
		return new ModelAndView("Register","command",register);
	}  else {
		String navigate = null;
		boolean status = JDBCConnection.operatorLogin(register.getEmail(),register.getPassword());
		if (status) {
			user_email = register.getEmail();
			model.addAttribute("status", "Welcome "+register.getEmail());
			navigate = "OperatorHome"; 
		} else {
			navigate = "Operator";
			model.addAttribute("status", "Incorrect login details");
        }
		return new ModelAndView(navigate);
	}
}

@RequestMapping("/Operator") 
public ModelAndView operator(Model model){
	Register register = new Register();
	model.addAttribute("Register", register);
	return new ModelAndView("Operator","command",register);  
}

@RequestMapping("/ViewAssignedIssues") 
public ModelAndView viewAssignedIssues(Model model){
	ArrayList<RaiseIssue> list = JDBCConnection.getAssignedIssue(user_email);
	return new ModelAndView("ViewAssignedIssues","list",list);
}

@RequestMapping("/SolvedIssue") 
public ModelAndView solvedIssue(@RequestParam("t1") String id,Model model) {
	JDBCConnection.solveIssue(Integer.parseInt(id));	
	ArrayList<RaiseIssue> list = JDBCConnection.getAssignedIssue(user_email);
	return new ModelAndView("ViewAssignedIssues","list",list);
}

@RequestMapping("/issueNotification") 
public ModelAndView issueNotification(Model model){
	ArrayList<RaiseIssue> list = JDBCConnection.getSolvedIssue(user_email);
	return new ModelAndView("issueNotification","list",list);
}
}  

