package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Case;
import com.example.demo.repository.CaseRepository;


@Service
public class CaseServiceImpl implements CaseService{
	
	@Autowired
	private CaseRepository caseRepository;
	
	public List<Case> getAllCases(){
		return caseRepository.findAll();
	}
	
	public Optional<Case> getCaseById(Long id){
		return caseRepository.findById(id);
	}
	
	public Case createCase(Case updatedCase) {
		updatedCase.setCreationDate(LocalDateTime.now());
		updatedCase.setLastUpdateDate(LocalDateTime.now());
		return caseRepository.save(updatedCase);
	}
	
	public boolean deleteCaseById(Long id) {
	    Optional<Case> optionalCase = caseRepository.findById(id);
	    if(optionalCase.isPresent()) {
	    	caseRepository.deleteById(id);
	    	return true;
	    }
		return false;
	}
	
	public Case updateCase(Case updatedCase, Long id) {
		Optional<Case> existingCase = caseRepository.findById(id);
		if(existingCase.isPresent()) {
			Case caseToUpdate = existingCase.get();
			caseToUpdate.setTitle(updatedCase.getTitle());
			caseToUpdate.setDescription(updatedCase.getDescription());
			caseToUpdate.setCreationDate(updatedCase.getCreationDate());
			caseToUpdate.setLastUpdateDate(updatedCase.getLastUpdateDate());
			return caseRepository.save(caseToUpdate);
		}
		return null;
	}
}
