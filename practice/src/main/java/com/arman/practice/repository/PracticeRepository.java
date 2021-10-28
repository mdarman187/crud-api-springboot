package com.arman.practice.repository;

import com.arman.practice.model.Practice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PracticeRepository extends JpaRepository<Practice, Long> {

}
