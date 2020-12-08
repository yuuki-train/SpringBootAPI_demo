package com.example.demo.controller;

import com.example.demo.entity.Candidate;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateRepository candidateRepository;

    //リポジトリにデータを追加
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Candidate add(@RequestBody Candidate candidate){
        return candidateRepository.save(candidate);
    }

    //リポジトリ全体のデータを取得
    @GetMapping
    public List<Candidate> getAll(){
        return candidateRepository.findAll();
    }

    //idを指定してデータを取得
    @GetMapping(value ="/{id}")
    public Candidate getOne(@PathVariable String id){
        return candidateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());
    }

    //idを指定してデータを更新
    @PutMapping(value = "/{id}")
    public Candidate update(@PathVariable String id, @RequestBody Candidate updatedCandidate){
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());
        candidate.setName(updatedCandidate.getName());
        candidate.setExp(updatedCandidate.getExp());
        candidate.setEmail(updatedCandidate.getEmail());
        return candidateRepository.save(candidate);
    }

    //idを指定してデータを削除
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void delete(@PathVariable String id){
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());
        candidateRepository.delete(candidate);
    }

    //Eメールを指定してデータを取得
    @GetMapping("/searchByEmail")
    public Candidate searchByEmail(@RequestParam(name = "email") String email){
        return candidateRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException());
    }

    //Exp値を指定してデータを取得
    @GetMapping("/searchByExp")
    public List<Candidate> searchByExp(@RequestParam(name = "expFrom") Double expFrom,
                                       @RequestParam(name = "expTo", required = false) Double expTo){
        List<Candidate> result = new ArrayList<>();
        if (expTo != null){
            result = candidateRepository.findByExpBetween(expFrom, expTo);
        }else{
            result = candidateRepository.findByExpGreaterThanEqual(expFrom);
        }
        return result;

    }
}
