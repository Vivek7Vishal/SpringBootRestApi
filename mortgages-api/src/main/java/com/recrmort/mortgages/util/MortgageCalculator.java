package com.recrmort.mortgages.util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.recrmort.mortgages.model.MortgageCheck;
import com.recrmort.mortgages.model.Mortgages;
import com.recrmort.mortgages.repository.MortgageRepository;

@Component
public class MortgageCalculator {

	@Autowired
	private MortgageRepository mortgageRepository;
/**
 * 
 * compares the loan with required income criteria and asset value 
 * calculates the monthly installment value based on provided inputs
 * 
 * @param income represents gross income of applying party in local currency 
 * @param mortgages object with current interest rate information from in-mem db based on user input
 * @param loanValue represents required loan amount of applying party
 * @param homeValue represents required home cost of applying party
 * @return MortgageCheck response entity with mortgage availability and monthly installments
 */
	public MortgageCheck calculateMortgage(Double income, Mortgages mortgages, Double loanValue, Double homeValue) {
		if (((income * 4) < loanValue) || (loanValue > homeValue)) {
			return new MortgageCheck(false, 0.00);
		}

		double monthlyIntersetRate = mortgages.getInterestRate() / 100 / 12;

		Integer termInMonths = mortgages.getMaturityPeriod() * 12;

		double monthlyPayment = (loanValue * monthlyIntersetRate)
				/ (1 - Math.pow(1 + monthlyIntersetRate, -termInMonths));

		monthlyPayment = Math.round(monthlyPayment * 100) / 100;
		return new MortgageCheck(true, monthlyPayment);
	}

	/**
	 * checks if the mortgage repository contains interest rate info for  entered maturity period by id
	 * returns the interest rate info
	 * @param maturityPeriod maturity period of loan in years 
	 * @return float interest rate in percentage
	 */
	public Mortgages getMortgageRates(Integer maturityPeriod) {
		Optional<Mortgages> byId = mortgageRepository.findById(maturityPeriod);
		return byId.isPresent() ? byId.get() : null;
	}
}
