package com.marketingapp7.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marketingapp7.dto.LeadDto;
import com.marketingapp7.entity.Lead;
import com.marketingapp7.repository.LeadRepository;

@RestController //it is help us to define the web-services layer in our project.
@RequestMapping("/api/leads")
public class LeadRestcontroller {
   
   @Autowired
   private LeadRepository leadRepo;
   
   //http://localhost:8080/api/leads ---> GET
   
   @GetMapping //it is used to convert the java object into json
   public List<Lead> getAllLeads(){
	   List<Lead> leads = leadRepo.findAll();
	   return leads;
   }
   
   //http://localhost:8080/api/leads ---> POST
	@PostMapping // to save the json content into database
	public void createLead(@RequestBody Lead lead) //@requestbody that takes the postman body into the lead objet
	{
		leadRepo.save(lead);
	}
	
	//http://localhost:8080/api/leads/6
	@DeleteMapping("{id}")
	public void deleteLead(@PathVariable("id") long id) // 
	{
		leadRepo.deleteById(id);
	}
	
	//http://localhost:8080/api/leads/6
	@PutMapping("{id}")
	public void updateLead(
			@PathVariable("id") long id,
			//for reading json on object
			@RequestBody LeadDto dto
			) {
		
		//update the record into lead
		Lead lead = new Lead();
		lead.setId(id);
		lead.setFirstname(dto.getFirstName());
		lead.setLastname(dto.getLastName());
		lead.setEmail(dto.getEmail());
		lead.setMobile(dto.getMobile());
		
		leadRepo.save(lead);
	}
 }

