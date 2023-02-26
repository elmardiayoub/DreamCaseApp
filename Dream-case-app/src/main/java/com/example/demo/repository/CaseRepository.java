package com.example.demo.repository;

import org.springframework.stereotype.Repository;
import com.example.demo.model.Case;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CaseRepository extends JpaRepository <Case, Long> {

}
