package com.recrmort.mortgages.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recrmort.mortgages.model.Mortgages;

@Repository
public interface MortgageRepository extends JpaRepository<Mortgages, Integer> {

}
