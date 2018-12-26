package com.recrmort.mortgages.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recrmort.mortgages.exception.BadRequestDataException;
import com.recrmort.mortgages.model.MortgageCheck;
import com.recrmort.mortgages.model.Mortgages;
import com.recrmort.mortgages.repository.MortgageRepository;
import com.recrmort.mortgages.util.MortgageCalculator;

/**
 * 
 * resource configuration class for all mortgage api's including get and posts
 *
 */
@RestController
@RequestMapping("/api")
public class MortgageResource {

	@Autowired
	private MortgageRepository mortgageRepository;

	@Autowired
	private MortgageCalculator mortgageCalculator;
	
/**
 * 
 * @return list of mortgage interest rate from  repository as response entity
 */
	@GetMapping("/interest-rates")
	public List<Mortgages> getInterestRates() {
		return mortgageRepository.findAll();
	}
	
/**
 * 
 * @param income represents gross income of applying party  
 * @param mortgages object with current interest rate information from in-mem db based on user input
 * @param loanValue represents required loan amount of applying party
 * @param homeValue represents required home cost of applying party
 * @return ResponseEntity response object for mortgage check with feasibility and monthly costs
 */
	@PostMapping("/mortgage-check")
	public ResponseEntity<MortgageCheck> postMortgageCheck(@RequestParam Double income,
			@RequestParam Integer maturityPeriod, @RequestParam Double loanValue, @RequestParam Double homeValue) {
		
		Mortgages mortgages = mortgageCalculator.getMortgageRates(maturityPeriod);
		if (mortgages == null) {
			throw new BadRequestDataException("Mortgage Period is Not Valid");
		}
		return ResponseEntity.ok(mortgageCalculator.calculateMortgage(income, mortgages, loanValue, homeValue));
	}
}
