package com.recrmort.mortgages.model;

public class MortgageCheck {

	private Boolean feasible;
	private Double monthlyCost;

	/**
	 * 
	 */
	public MortgageCheck() {
		super();
	}

	/**
	 * constructor method for class MortgageCheck
	 * @param feasible loan feasibility in boolean(true/false)
	 * @param monthlyCost monthly installment amount 
	 */
	public MortgageCheck(Boolean feasible, Double monthlyCost) {
		super();
		this.feasible = feasible;
		this.monthlyCost = monthlyCost;
	}

	/**
	 * @return the feasible
	 */
	public Boolean getFeasible() {
		return feasible;
	}

	/**
	 * @param feasible the feasible to set
	 */
	public void setFeasible(Boolean feasible) {
		this.feasible = feasible;
	}

	/**
	 * @return the monthlyCost
	 */
	public Double getMonthlyCost() {
		return monthlyCost;
	}

	/**
	 * @param monthlyCost the monthlyCost to set
	 */
	public void setMonthlyCost(Double monthlyCost) {
		this.monthlyCost = monthlyCost;
	}

}
