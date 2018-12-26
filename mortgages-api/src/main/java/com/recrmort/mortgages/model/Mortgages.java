package com.recrmort.mortgages.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Vivek
 *
 */
@Entity
public class Mortgages {

	@Id
	@GeneratedValue
	private Integer maturityPeriod;
	@Column
	private Float interestRate;
	@Column
	private LocalDateTime lastUpdate;

	/**
	 * @return the maturityPeriod
	 */
	public Integer getMaturityPeriod() {
		return maturityPeriod;
	}

	/**
	 * @param maturityPeriod the maturityPeriod to set
	 */
	public void setMaturityPeriod(Integer maturityPeriod) {
		this.maturityPeriod = maturityPeriod;
	}

	/**
	 * @return the interestRate
	 */
	public Float getInterestRate() {
		return interestRate;
	}

	/**
	 * @param interestRate the interestRate to set
	 */
	public void setInterestRate(Float interestRate) {
		this.interestRate = interestRate;
	}

	/**
	 * @return the lastUpdate
	 */
	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * @param lastUpdate the lastUpdate to set
	 */
	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
