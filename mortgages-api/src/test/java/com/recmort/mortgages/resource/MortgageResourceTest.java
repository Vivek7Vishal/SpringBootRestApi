package com.recmort.mortgages.resource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.ResponseEntity;

import com.recrmort.mortgages.config.MortgagesApiApplication;
import com.recrmort.mortgages.exception.BadRequestDataException;
import com.recrmort.mortgages.model.MortgageCheck;
import com.recrmort.mortgages.model.Mortgages;
import com.recrmort.mortgages.repository.MortgageRepository;
import com.recrmort.mortgages.resource.MortgageResource;
import com.recrmort.mortgages.util.MortgageCalculator;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ MortgagesApiApplication.class, MortgageResource.class })
public class MortgageResourceTest {

	@InjectMocks
	private MortgageResource unitToTest;

	@Mock
	private MortgageRepository mortgageRepository;

	@Mock
	MortgageCalculator mortgageCalculator;

	@Test
	public void test_getIntRates() {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime publishDate = LocalDateTime.parse("2014-04-01 00:00:00", formatter);

		Mortgages mortgageRate1 = new Mortgages();
		mortgageRate1.setInterestRate((float) 2.00);
		mortgageRate1.setMaturityPeriod(10);
		mortgageRate1.setLastUpdate(publishDate);

		Mortgages mortgageRate2 = new Mortgages();
		mortgageRate2.setInterestRate((float) 2.00);
		mortgageRate2.setMaturityPeriod(10);
		mortgageRate2.setLastUpdate(publishDate);

		List<Mortgages> mortgageRateList = new ArrayList<>();
		mortgageRateList.add(mortgageRate1);
		mortgageRateList.add(mortgageRate2);

		when(mortgageRepository.findAll()).thenReturn(mortgageRateList);

		List<Mortgages> mortgageRateListActual = unitToTest.getInterestRates();
		assertThat(mortgageRateListActual).contains(mortgageRate1);
		assertEquals("check if mortagerate obj2 is at index 1", mortgageRate2, mortgageRateListActual.get(1));
		verify(mortgageRepository).findAll();
		verifyNoMoreInteractions(mortgageRepository);
	}

	@Test
	public void test_postMortgageCheck() throws Exception {
		Double loanValue = 10000.00;
		Double homeValue = 40000.00;
		Integer maturityPeriod = 1;
		Double income = 10000.00;
		double monthlyCost = 500.0;

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime lastUpdate = LocalDateTime.parse("2014-04-01 00:00:00", formatter);

		// setting mortage rate object
		Mortgages mortgageRate = new Mortgages();
		mortgageRate.setInterestRate((float) 2.00);
		mortgageRate.setMaturityPeriod(maturityPeriod);
		mortgageRate.setLastUpdate(lastUpdate);

		// setting mortgage check response
		MortgageCheck mortgageCheck = new MortgageCheck();
		mortgageCheck.setFeasible(true);
		mortgageCheck.setMonthlyCost(monthlyCost);

		when(mortgageCalculator.getMortgageRates(maturityPeriod)).thenReturn(mortgageRate);

		when(mortgageCalculator.calculateMortgage(income, mortgageRate, loanValue, homeValue))
				.thenReturn(mortgageCheck);

		ResponseEntity<MortgageCheck> mortgageCheck1 = unitToTest.postMortgageCheck(income, maturityPeriod, loanValue,
				homeValue);

		assertEquals("should get 200 status for a succes call ", "200 OK", mortgageCheck1.getStatusCode().toString());
		assertEquals("", Double.valueOf(500.0), mortgageCheck1.getBody().getMonthlyCost());
		verify(mortgageCalculator).calculateMortgage(income, mortgageRate, loanValue, homeValue);
		verify(mortgageCalculator).getMortgageRates(maturityPeriod);
		verifyNoMoreInteractions(mortgageCalculator);

	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void test_postMortgageCheckForNullRate() throws Exception {
		Double loanValue = 10000.00;
		Double homeValue = 40000.00;
		Integer maturityPeriod = 100;
		Double income = 10000.00;
		
		ResponseEntity<MortgageCheck> mortgageCheck1 = null;

		when(mortgageCalculator.getMortgageRates(maturityPeriod)).thenReturn(null);

		try {
			 mortgageCheck1 = unitToTest.postMortgageCheck(income, maturityPeriod, loanValue,
					homeValue);
		}
		catch( BadRequestDataException e) {
			assertEquals("should get 400 status for a succes call ", "Mortgage Period is Not Valid", e.getMessage());
		}
		

		verify(mortgageCalculator).getMortgageRates(maturityPeriod);
		verifyNoMoreInteractions(mortgageCalculator);

	}


}
