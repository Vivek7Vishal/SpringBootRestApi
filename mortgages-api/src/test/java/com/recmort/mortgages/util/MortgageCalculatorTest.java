package com.recmort.mortgages.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.recrmort.mortgages.model.MortgageCheck;
import com.recrmort.mortgages.model.Mortgages;
import com.recrmort.mortgages.util.MortgageCalculator;

@RunWith(PowerMockRunner.class)
@PrepareForTest({MortgageCalculator.class})
public class MortgageCalculatorTest {

	@InjectMocks
	MortgageCalculator unitToTest ;
	
	@Test
	public void test_calculateMortgage_whenFeasible() {
		Mortgages mortgageRate = new Mortgages();
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		 LocalDateTime lastUpdate = LocalDateTime.parse("2014-04-01 00:00:00", formatter);
		mortgageRate.setInterestRate((float) 5.00);
		mortgageRate.setMaturityPeriod(1);
		mortgageRate.setLastUpdate(lastUpdate);
		
		MortgageCheck mortCheck = unitToTest.calculateMortgage(10000.00, mortgageRate, 40000.00, 40000.00);
		
		assertEquals("", Double.valueOf(3424.0), mortCheck.getMonthlyCost());
		assertTrue("", mortCheck.getFeasible());
		
	}
	
	@Test
	public void test_calculateMortgage_whenNotFeasible() {
		Mortgages mortgageRate = new Mortgages();
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		 LocalDateTime lastUpdate = LocalDateTime.parse("2014-04-01 00:00:00", formatter);
		mortgageRate.setInterestRate((float) 5.00);
		mortgageRate.setMaturityPeriod(1);
		mortgageRate.setLastUpdate(lastUpdate);
		
		MortgageCheck mortCheck = unitToTest.calculateMortgage(1000.00, mortgageRate, 40000.00, 40000.00);
		
		assertEquals("", Double.valueOf(0.0), mortCheck.getMonthlyCost());
		assertFalse("", mortCheck.getFeasible());
		
	}
	
}
