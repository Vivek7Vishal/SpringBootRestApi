package com.RecrMort.mortgages.mortgagesapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.recrmort.mortgages.config.MortgagesApiApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MortgagesApiApplication.class)
//Test class added ONLY to cover main() invocation not covered by application tests.
public class MortgagesApiApplicationTests {

	@Test
	public void contextLoads() {
		MortgagesApiApplication.main(new String[] {
				"spring.datasource.url=jdbc:h2:mem:mortgagesdb"
		});
	}

}

