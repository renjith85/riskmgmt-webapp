package com.sample.risk.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.easymock.classextension.EasyMock;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;


import com.sample.risk.dao.RiskDAO;
import com.sample.risk.dao.RiskSummaryBO;

public class RiskManagementServiceTest {
	

	@Test
	public void getRiskCategoryNullData()
	{
		RiskManagementService service = new RiskManagementService();
		RiskDAO mockedDAO = EasyMock.createMock(RiskDAO.class);
		ReflectionTestUtils.setField(service, "riskDAO", mockedDAO);
		EasyMock.expect(mockedDAO.getRiskCategory()).andReturn(null);
		EasyMock.replay(mockedDAO);
		service.getRiskCategory();
		EasyMock.verify(mockedDAO);
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void getRiskCategoryValidData()
	{
		RiskManagementService service = new RiskManagementService();
		RiskDAO mockedDAO = EasyMock.createMock(RiskDAO.class);
		ReflectionTestUtils.setField(service, "riskDAO", mockedDAO);
		
		List categoryList =  new ArrayList();
		categoryList.add("INFO");
		categoryList.add("MAJOR");
		
		EasyMock.expect(mockedDAO.getRiskCategory()).andReturn(categoryList);
		EasyMock.replay(mockedDAO);
		
		String categories= service.getRiskCategory();
		
		//assertEquals("{\"riskCategory\":[\"INFO\",\"MAJOR\"],\"MESSAGE\":\"SUCCESS\"}",categories);
		
		EasyMock.verify(mockedDAO);
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void getRiskSummaryValidData()
	{
		RiskManagementService service = new RiskManagementService();
		RiskDAO mockedDAO = EasyMock.createMock(RiskDAO.class);
		ReflectionTestUtils.setField(service, "riskDAO", mockedDAO);
		
		List summaryList =  new ArrayList();
		
		RiskSummaryBO resultBO = new RiskSummaryBO();
		
		resultBO.setNoOfRisks(123);
		resultBO.setRiskCategory("INFO");
	
		resultBO.setTotalFactors(123);
		resultBO.setTotalPoints(new BigDecimal(123));
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date modifiedDate = null ;
		try {
			modifiedDate = format.parse("2013-02-09".trim());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		resultBO.setRiskDate(modifiedDate);
		summaryList.add(resultBO);
		
		EasyMock.expect(mockedDAO.getRiskSummary()).andReturn(summaryList);
		EasyMock.replay(mockedDAO);
		
		String summary= service.riskSummary();
		
		//assertEquals("{\"MESSAGE\":\"SUCCESS\",\"summary\":[{\"numberOfRisks\":123,\"date\":\"Sat Feb 09 00:00:00 GMT+05:30 2013\",\"type\":\"INFO\",\"totalFactor\":123,\"totalPoints\":123}]}",summary);
		
		EasyMock.verify(mockedDAO);
		
	}

}
