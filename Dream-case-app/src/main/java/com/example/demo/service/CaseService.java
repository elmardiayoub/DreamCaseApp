package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Case;

public interface CaseService {
	
	public List<Case> getAllCases();
	public Optional<Case> getCaseById(Long id);
	public Case createCase(Case updatedCase);
	public boolean deleteCaseById(Long id);
	public Case updateCase(Case updatedCase, Long id) ;
}
