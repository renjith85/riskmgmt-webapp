package com.sample.risk.controller;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.easymock.classextension.EasyMock.replay;
import static org.easymock.classextension.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.sample.risk.service.IRiskManagement;
import com.sample.risk.view.RiskSummary;

public class RiskSummaryControllerTest {

	  private RiskSummaryController riskSummaryController;
	  
	  @SuppressWarnings("unchecked")
	  @Test
	  public void testRiskSummaryServiceReturnNull() throws Exception
	  {
		  	  ExtendedModelMap modelStub = new ExtendedModelMap();
			  IRiskManagement serviceMock = createMock(IRiskManagement.class) ;
			  expect(serviceMock.riskSummary()).andReturn(null);
			
			  riskSummaryController = new RiskSummaryController();
			  ReflectionTestUtils.setField(riskSummaryController, "service", serviceMock);
			 
			  replay(serviceMock);
			  
			  String view = riskSummaryController.riskSummary((Model) modelStub);
			  
			  verify(serviceMock);
			  java.util.List<RiskSummary> resultModel = (List<RiskSummary>) modelStub.get("summary");
			  assertNotNull(resultModel);
			  assertTrue(resultModel.isEmpty());
			  assertEquals("risk_details", view);
	  }
	  
	  public static void main(String[] args) throws JSONException
	  {
		  JSONObject jsonObject = new JSONObject();
			JSONArray arr = new JSONArray();
			
			JSONObject resultObject = new JSONObject();
			resultObject.put("numberOfRisks", 12);
			resultObject.put("type", "MAJOR");
			resultObject.put("date", "2013-03-14");
			resultObject.put("totalPoints", 123);
			resultObject.put("totalFactor", 127.5);
			
			JSONObject resultObject1 = new JSONObject();
			resultObject1.put("numberOfRisks", 12);
			resultObject1.put("type", "MAJOR");
			resultObject1.put("date", "2013-03-14");
			resultObject1.put("totalPoints", 123);
			resultObject1.put("totalFactor", 127.5);
			
			arr.put(resultObject);
			arr.put(resultObject1);
			
			jsonObject.put("summary", arr);
			
			System.err.println(jsonObject.toString());
			
	  }
	  
	  @SuppressWarnings("unchecked")
	  @Test
	  public void testRiskSummaryServiceReturnValid() throws Exception
	  {
		  	  ExtendedModelMap modelStub = new ExtendedModelMap();
			  IRiskManagement serviceMock = createMock(IRiskManagement.class) ;
			  expect(serviceMock.riskSummary()).andReturn("{\"summary\":[{\"numberOfRisks\":12,\"date\":\"2013-03-14\",\"type\":\"MAJOR\",\"totalFactor\":127.5,\"totalPoints\":123}," +
			  		"{\"numberOfRisks\":12,\"date\":\"2013-03-14\",\"type\":\"MAJOR\",\"totalFactor\":127.5,\"totalPoints\":123}]}");
			
			  riskSummaryController = new RiskSummaryController();
			  ReflectionTestUtils.setField(riskSummaryController, "service", serviceMock);
			 
			  replay(serviceMock);
			  
			  String view = riskSummaryController.riskSummary((Model) modelStub);
			  
			  verify(serviceMock);
			  java.util.List<RiskSummary> resultModel = (List<RiskSummary>) modelStub.get("summary");
			  assertNotNull(resultModel);
			  assertEquals(2,resultModel.size());
			  assertEquals("risk_details", view);
	  }
	  
	  @SuppressWarnings("unchecked")
	  @Test
	  public void testRiskSummaryServiceEmptyReturn() throws Exception
	  {
		  	  ExtendedModelMap modelStub = new ExtendedModelMap();
			  IRiskManagement serviceMock = createMock(IRiskManagement.class) ;
			  expect(serviceMock.riskSummary()).andReturn("{\"summary\":[]}");
			
			  riskSummaryController = new RiskSummaryController();
			  ReflectionTestUtils.setField(riskSummaryController, "service", serviceMock);
			 
			  replay(serviceMock);
			  
			  String view = riskSummaryController.riskSummary((Model) modelStub);
			  
			  verify(serviceMock);
			  java.util.List<RiskSummary> resultModel = (List<RiskSummary>) modelStub.get("summary");
			  assertNotNull(resultModel);
			  assertEquals(0,resultModel.size());
			  assertEquals("risk_details", view);
	  }
	 

}
