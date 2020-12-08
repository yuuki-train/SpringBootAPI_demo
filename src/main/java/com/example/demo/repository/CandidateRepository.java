package com.example.demo.repository;

import com.example.demo.entity.Candidate;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CandidateRepository
        extends MongoRepository<Candidate, String>{
    Optional<Candidate> findByEmail(String email);

    List<Candidate> findByExpGreaterThanEqual(double exp);
    
    List<Candidate> findByExpBetween(double from, double to);
}
