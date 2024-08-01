package com.mindex.challenge;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.controller.CompensationController;
import com.mindex.challenge.controller.ReportingStructureController;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.ReportingStructureService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChallengeApplicationTests {

	@Autowired
	private ReportingStructureController reportingStructureController;

	@Autowired
	private ReportingStructureService reportingStructureService;

	@Autowired
	private CompensationController compensationController;

	@Autowired
	private CompensationService compensationService;

	@Test
	public void contextLoads() throws Exception{
		assertNotNull(reportingStructureController);
		assertNotNull(reportingStructureService);
		assertNotNull(compensationController);
		assertNotNull(compensationService);
	}

}
