package com.example.demo.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.http.HttpMethod;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.Case;
import com.example.demo.service.CaseServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CaseControllerTest {
    
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CaseServiceImpl caseService;

    @LocalServerPort
    private int port;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/cases";
    }

    @Test
    public void testCreateCase() {
        Case newCase = new Case();
        newCase.setTitle("Test Case");
        newCase.setDescription("This is a test case");

        ResponseEntity<Case> response = restTemplate.postForEntity(getBaseUrl(), newCase, Case.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        Case createdCase = response.getBody();
        assertNotNull(createdCase.getCaseId());
        assertEquals(newCase.getTitle(), createdCase.getTitle());
        assertEquals(newCase.getDescription(), createdCase.getDescription());
    }

    @Test
    public void testGetCaseById() {
        Case newCase = new Case();
        newCase.setTitle("Test Case");
        newCase.setDescription("This is a test case");
        Case createdCase = caseService.createCase(newCase);

        ResponseEntity<Case> response = restTemplate.getForEntity(getBaseUrl() + "/" + createdCase.getCaseId(), Case.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        Case fetchedCase = response.getBody();
        assertEquals(createdCase.getCaseId(), fetchedCase.getCaseId());
        assertEquals(createdCase.getTitle(), fetchedCase.getTitle());
        assertEquals(createdCase.getDescription(), fetchedCase.getDescription());
    }


    @Test
    public void testDeleteCase() {
        Case newCase = new Case();
        newCase.setTitle("Test Case");
        newCase.setDescription("This is a test case");
        Case createdCase = caseService.createCase(newCase);

        ResponseEntity<?> response = restTemplate.exchange(getBaseUrl() + "/" + createdCase.getCaseId(), HttpMethod.DELETE, null, ResponseEntity.class);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
