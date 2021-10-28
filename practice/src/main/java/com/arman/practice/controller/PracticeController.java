package com.arman.practice.controller;

import com.arman.practice.exception.ResourceNotFoundException;
import com.arman.practice.model.Practice;
import com.arman.practice.repository.PracticeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")

public class PracticeController {

    @Autowired
    PracticeRepository practiceRepository;

    @GetMapping("/practices")
    public List<Practice> getAllPractices() {
        return practiceRepository.findAll();
    }

    @PostMapping("/practices")
    public Practice createPractice(@Valid @RequestBody Practice practice) {
        return practiceRepository.save(practice);
    }

    @GetMapping("/practices/{id}")
    public Practice getPracticeById(@PathVariable(value = "id") Long practiceId) {
        return practiceRepository.findById(practiceId)
                .orElseThrow(() -> new ResourceNotFoundException("Practice", "id", practiceId));
    }

    @PutMapping("/practices/{id}")
    public Practice updatePractice(@PathVariable(value = "id") Long practiceId,
                           @Valid @RequestBody Practice practiceDetails) {

        Practice practice = practiceRepository.findById(practiceId)
                .orElseThrow(() -> new ResourceNotFoundException("Practice", "id", practiceId));

        practice.setName(practiceDetails.getName());
        practice.setAge(practiceDetails.getAge());

        Practice updatedPractice = practiceRepository.save(practice);
        return updatedPractice;
    }

    @DeleteMapping("/practices/{id}")
    public ResponseEntity<?> deletePractice(@PathVariable(value = "id") Long practiceId) {
        Practice practice = practiceRepository.findById(practiceId)
                .orElseThrow(() -> new ResourceNotFoundException("Practice", "id", practiceId));

        practiceRepository.delete(practice);

        return ResponseEntity.ok().build();
    }
}

