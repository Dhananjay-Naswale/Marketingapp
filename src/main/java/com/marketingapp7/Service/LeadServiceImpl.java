package com.marketingapp7.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marketingapp7.entity.Lead;
import com.marketingapp7.repository.LeadRepository;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

import java.util.List;

@Service
public class LeadServiceImpl implements LeadService {
	
	@Autowired
	private LeadRepository leadRepo;
	
	@Override
	public void saveLead(Lead lead) {
		leadRepo.save(lead);
		}

	@Override
	public List<Lead> getLeads() {
	List<Lead> leads = leadRepo.findAll();//take the data from database.
	return leads;
	}

	@Override
	public void deleteLead(long id) {
		leadRepo.deleteById(id);
		
	}
	//for Update.
	@Override
	public Lead getLeadById(long id) {
		java.util.Optional<Lead> findById = leadRepo.findById(id);
		Lead lead = findById.get();
		return lead;
	}
     

}
