package com.example.demo.model;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name="cases")
public class Case {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long caseId;
	
	@Column(name = "creationDate")
	private LocalDateTime creationDate;
	
	@Column(name = "lastUpdateDate")
	private LocalDateTime lastUpdateDate;
	
	private String title;
	
	private String description;

	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDateTime getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

}
