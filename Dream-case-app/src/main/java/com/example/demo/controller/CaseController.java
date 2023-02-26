package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Case;
import com.example.demo.model.ErrorResponse;
import com.example.demo.service.CaseServiceImpl;

@RestController
@RequestMapping("/cases")
public class CaseController {

	@Autowired
	private CaseServiceImpl caseService;

	private final Logger LOGGER = Logger.getLogger(CaseController.class.getName());


	@GetMapping(value = "")
	public List<Case> getAllCases() {
		return caseService.getAllCases();
	}

	@GetMapping("/{caseId}")
	public ResponseEntity<?> getCaseById(@PathVariable (value="caseId") Long caseId){
		Optional<Case> foundCase = caseService.getCaseById(caseId);
		if(!foundCase.isPresent()) {
			return handleCaseNotFound(caseId);
		}
		return ResponseEntity.ok().body(foundCase);
	}

	@PutMapping("/{caseId}")
	public ResponseEntity<?> updateCase(@RequestBody Case caseDetails, @PathVariable(value="caseId") Long caseId){
		try {
			Case updatedCase = caseService.updateCase(caseDetails, caseId);
			if(updatedCase == null) {
				return handleCaseNotFound(caseId);
			}
			LOGGER.info("Case with id " + caseId + " updated successfully.");
			return ResponseEntity.ok(updatedCase);
		} catch (Exception e) {
			return handleCaseServiceError(caseId, "update", e);
		}
	}

	@PostMapping("")
	public ResponseEntity<?> createCase(@RequestBody Case newCase){
		try {
			Case createdCase = caseService.createCase(newCase);
			LOGGER.info("Created new case with id: "+ createdCase.getCaseId());
			return ResponseEntity.status(HttpStatus.CREATED).body(createdCase);

		}catch (Exception e) { 
			return handleCaseServiceError(newCase.getCaseId(), "create", e);

		}
	}

	@DeleteMapping("/{caseId}")
	public ResponseEntity<?> deleteCase(@PathVariable(value = "caseId") Long caseId){
		try {
			boolean deleted = caseService.deleteCaseById(caseId);
			if (!deleted) {
				return handleCaseNotFound(caseId);
			}
			LOGGER.info("Case with id "+caseId+" deleted successfully");
			return ResponseEntity.noContent().build();

		}catch (Exception e) {
			return handleCaseServiceError(caseId, "delete", e);
		} 
	}


	private ResponseEntity<?> handleCaseNotFound(Long caseId) {
		String errorMessage = "Case with id " + caseId + " not found";
		LOGGER.info(errorMessage);
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ErrorResponse(errorMessage, HttpStatus.NOT_FOUND));
	}

	private ResponseEntity<?> handleCaseServiceError(Long caseId, String action, Exception e) {
		String errorMessage = "Failed to " + action + " case with id " + caseId;
		LOGGER.info(errorMessage);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ErrorResponse(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR));
	}

}
