package com.marketingapp7.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.marketingapp7.Service.LeadService;
import com.marketingapp7.entity.Lead;
import com.marketingapp7.util.EmailService;

@Controller
public class LeadController {
	
	@Autowired
	private LeadService leadService; //object of Service Layer.
	
	@Autowired
	private EmailService emailService; //object of EmailService emailService.
	
	
	//http://localhost:8080/view
    @RequestMapping("/view")
	public String viewLeadPage() {
		return "create_lead"; //Page name
		//request Dispatcher
	}
    

 // 
    @RequestMapping("/saveLead")
    public String saveOneLead(@ModelAttribute Lead lead, Model model) //model acts as requset.setattribute() 
    {
    leadService.saveLead(lead); 
    //email->
    emailService.sendEmail(lead.getEmail(), "@#@#@#@#@#@Yur Email Is Hacked!!!!!!!!!!!!!!!", "test"); 
    model.addAttribute("msg","Lead is Saved!!!!"); 
    return "create_lead"; 
    }

//    //Another Way
//	
//	  @RequestMapping("/saveLead") //
//	  public String saveOneLead(
//		@RequestParam("firstname") String firstname,
//		@RequestParam("lastname") String lastname,
//		@RequestParam("email") String email,
//		@RequestParam("mobile") long mobile,
//		Model model) {
//		  
//		  Lead lead = new Lead();
//		  lead.setFirstname(firstname);
//		  lead.setLastname(lastname);
//		  lead.setEmail(email);
//		  lead.setMobile(mobile);
//		  
//	leadService.saveLead(lead);
//	emailService.sendEmail(lead.getEmail(), "@#@#@#@#@#@Yur Email Is Hacked!!!!!!!!!!!!!!!", "test"); 
//	model.addAttribute("msg","Lead is Saved!!!!");  
//	return "create_lead"; 
//  }
	   
   // http://localhost:8080/listall
    @RequestMapping("/listall")
    public String getAllLeads(Model model) {
    	List <Lead> leads = leadService.getLeads();
    	model.addAttribute("leads",leads);
      System.out.println(leads);
		return "list_leads";
    }
    
    @RequestMapping("/delete")
    public String deleteLeadById(@RequestParam("id") long id,ModelMap model) {
    	leadService.deleteLead(id);
    	List <Lead> leads = leadService.getLeads();
    	model.addAttribute("leads",leads);
        System.out.println(leads);
  		return "list_leads";
    }
    
   @RequestMapping("/update")
   public String getLeadById(@RequestParam("id") long id, Model model) {
	   Lead lead = leadService.getLeadById(id);
	   model.addAttribute("lead",lead);
       System.out.println(lead);
 		return "update_lead";
   }
   @RequestMapping("/updateLead")
   public String updateOneLead(Lead lead,Model model)//model acts as requset.setattribute()
   {
   	leadService.saveLead(lead);
   	List <Lead> leads = leadService.getLeads();
	model.addAttribute("leads",leads);
	return "list_leads";
   }
}
