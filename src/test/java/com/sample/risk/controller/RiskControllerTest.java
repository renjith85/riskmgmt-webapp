package com.sample.risk.controller;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.easymock.classextension.EasyMock.replay;
import static org.easymock.classextension.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.LinkedHashMap;

import org.easymock.EasyMock;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.sample.risk.service.IRiskManagement;
import com.sample.risk.view.Risk;

public class RiskControllerTest {

	  private RiskController riskController;
	  
	  @Test
	  public void testAddCategoryPOSTForNullInput() throws Exception
	  {
		
		  	 Risk risk = new Risk();
			 ExtendedModelMap modelStub = new ExtendedModelMap();
			  IRiskManagement serviceMock = createMock(IRiskManagement.class) ;
			  expect(serviceMock.getRiskCategory()).andReturn(null);
			
			  riskController = new RiskController();
			  ReflectionTestUtils.setField(riskController, "service", serviceMock);
			 
			  replay(serviceMock);
			  
			  String view = riskController.addCategory(null,risk, (Model) modelStub);
			  
			  verify(serviceMock);
			  assertEquals("risk_add", view);
			  assertEquals("typeFail", modelStub.get("state"));
	  }
	  
	  @Test
	  public void testAddCategoryPOSTForEmptyInput() throws Exception
	  {
		
		  	 Risk risk = new Risk();
			 ExtendedModelMap modelStub = new ExtendedModelMap();
			  IRiskManagement serviceMock = createMock(IRiskManagement.class) ;
			  expect(serviceMock.getRiskCategory()).andReturn(null);
			
			  riskController = new RiskController();
			  ReflectionTestUtils.setField(riskController, "service", serviceMock);
			 
			  replay(serviceMock);
			  
			  String view = riskController.addCategory("",risk, (Model) modelStub);
			  
			  verify(serviceMock);
			  assertEquals("risk_add", view);
			  assertEquals("typeFail", modelStub.get("state"));
	  }
	  
	  @Test
	  public void testAddCategoryPOSTForInvalidLengthInput() throws Exception
	  {
		
		  	 Risk risk = new Risk();
			 ExtendedModelMap modelStub = new ExtendedModelMap();
			  IRiskManagement serviceMock = createMock(IRiskManagement.class) ;
			  expect(serviceMock.getRiskCategory()).andReturn(null);
			
			  riskController = new RiskController();
			  ReflectionTestUtils.setField(riskController, "service", serviceMock);
			 
			  replay(serviceMock);
			  
			  String view = riskController.addCategory("1233345345345345",risk, (Model) modelStub);
			  
			  verify(serviceMock);
			  assertEquals("risk_add", view);
			  assertEquals("typeFail", modelStub.get("state"));
	  }
	  
	  @Test
	  public void testAddCategoryPOSTForInvalidCharactersInput() throws Exception
	  {
		
		  	 Risk risk = new Risk();
			 ExtendedModelMap modelStub = new ExtendedModelMap();
			  IRiskManagement serviceMock = createMock(IRiskManagement.class) ;
			  expect(serviceMock.getRiskCategory()).andReturn(null);
			
			  riskController = new RiskController();
			  ReflectionTestUtils.setField(riskController, "service", serviceMock);
			 
			  replay(serviceMock);
			  
			  String view = riskController.addCategory("12333#45345345",risk, (Model) modelStub);
			  
			  verify(serviceMock);
			  assertEquals("risk_add", view);
			  assertEquals("typeFail", modelStub.get("state"));
	  }
	  
	  @Test
	  public void testAddCategoryPOSTForVALIDInput() throws Exception
	  {
		
		  	 Risk risk = new Risk();
			 ExtendedModelMap modelStub = new ExtendedModelMap();
			  IRiskManagement serviceMock = createMock(IRiskManagement.class) ;
			  expect(serviceMock.getRiskCategory()).andReturn(null).times(2);
			  expect(serviceMock.addRiskCategory("MAJOR12345")).andReturn("{\"MESSAGE\":\"SUCCESS\"}");
			//  EasyMock.expectLastCall();
			  
			  riskController = new RiskController();
			  ReflectionTestUtils.setField(riskController, "service", serviceMock);
			 
			  replay(serviceMock);
			  
			  String view = riskController.addCategory("MAJOR12345",risk, (Model) modelStub);
			  
			  verify(serviceMock);
			  assertEquals("risk_add", view);
			  assertEquals("typeSuccess", modelStub.get("state"));
	  }
	  
	  @Test
	  public void testAddCategoryPOSTForExistingCategory() throws Exception
	  {
		
		  	 Risk risk = new Risk();
			 ExtendedModelMap modelStub = new ExtendedModelMap();
			  IRiskManagement serviceMock = createMock(IRiskManagement.class) ;
			  expect(serviceMock.getRiskCategory()).andReturn("{\"riskCategory\":[\"MAJOR12345\",\"MAJOR\"]}").times(2);
			  
			  
			  riskController = new RiskController();
			  ReflectionTestUtils.setField(riskController, "service", serviceMock);
			 
			  replay(serviceMock);
			  
			  String view = riskController.addCategory("MAJOR12345",risk, (Model) modelStub);
			  
			  verify(serviceMock);
			  assertEquals("risk_add", view);
			  assertEquals("typeFail", modelStub.get("state"));
	  }
	  
	  @Test
	  public void testAddCategoryPOSTForNonExistingCategory() throws Exception
	  {
		
		  	 Risk risk = new Risk();
			 ExtendedModelMap modelStub = new ExtendedModelMap();
			  IRiskManagement serviceMock = createMock(IRiskManagement.class) ;
			  expect(serviceMock.getRiskCategory()).andReturn("{\"riskCategory\":[\"MAJOR1234\",\"MAJOR\"],\"MESSAGE\":\"SUCCESS\"}").times(2);
			  expect(serviceMock.addRiskCategory("MAJOR12345")).andReturn("{\"MESSAGE\":\"SUCCESS\"}");
			//  EasyMock.expectLastCall();
			  
			  
			  riskController = new RiskController();
			  ReflectionTestUtils.setField(riskController, "service", serviceMock);
			 
			  replay(serviceMock);
			  
			  String view = riskController.addCategory("MAJOR12345",risk, (Model) modelStub);
			  
			  verify(serviceMock);
			  assertEquals("risk_add", view);
			  assertEquals("typeSuccess", modelStub.get("state"));
	  }
	  
	  @Test
	  public void testAddRiskGETForNullCategory()
	  {
		
		  Risk risk = new Risk();
		 ExtendedModelMap modelStub = new ExtendedModelMap();
		  
		  riskController = new RiskController();
		
		  IRiskManagement serviceMock = createMock(IRiskManagement.class) ;
		  expect(serviceMock.getRiskCategory()).andReturn(null);
		  ReflectionTestUtils.setField(riskController, "service", serviceMock);
		  replay(serviceMock);
		  
		  String view = riskController.addRisk(risk, (Model) modelStub);
		  
		  verify(serviceMock);
		  
		  assertEquals("risk_add", view);
		  assertNotNull(modelStub.get("categories"));
		  assertNotNull(modelStub.get("state"));
		  assertEquals(modelStub.get("state"), "");
	  }
	  
	  @SuppressWarnings("unchecked")
	  @Test
	  public void testAddRiskGETForEmptyCategory()
	  {
		
		  Risk risk = new Risk();
		 ExtendedModelMap modelStub = new ExtendedModelMap();
		  
		  riskController = new RiskController();
		
		  IRiskManagement serviceMock = createMock(IRiskManagement.class) ;
		  expect(serviceMock.getRiskCategory()).andReturn("{}");
		  
		  ReflectionTestUtils.setField(riskController, "service", serviceMock);
		  replay(serviceMock);
		  
		  String view = riskController.addRisk(risk, (Model) modelStub);
		  
		  verify(serviceMock);
		  
		  assertEquals("risk_add", view);
		  assertNotNull(modelStub.get("categories"));
		  assertTrue(((LinkedHashMap<String, String>)modelStub.get("categories")).isEmpty());
		  assertNotNull(modelStub.get("state"));
		  assertEquals(modelStub.get("state"), "");
	  }
	  
	  
	  @SuppressWarnings("unchecked")
	  @Test
	  public void testAddRiskGETForValidCategory()
	  {
		
		  Risk risk = new Risk();
		 ExtendedModelMap modelStub = new ExtendedModelMap();
		  
		  riskController = new RiskController();
		
		  IRiskManagement serviceMock = createMock(IRiskManagement.class) ;
		  expect(serviceMock.getRiskCategory()).andReturn("{\"riskCategory\":[\"INFO\",\"MAJOR\"]}");
		  
		  ReflectionTestUtils.setField(riskController, "service", serviceMock);
		  replay(serviceMock);
		  
		  String view = riskController.addRisk(risk, (Model) modelStub);
		  
		  verify(serviceMock);
		  
		  assertEquals("risk_add", view);
		  assertNotNull(modelStub.get("categories"));
		  LinkedHashMap<String, String> linkedHashMap = (LinkedHashMap<String, String>)modelStub.get("categories");
		  
		  assertFalse(linkedHashMap.isEmpty());
		  assertEquals(linkedHashMap.size(), 2);
		  assertNotNull(modelStub.get("state"));
		  assertEquals(modelStub.get("state"), "");
	  }
	  
	  @Test
	  public void testAddRiskPOSTForValidInput() throws Exception
	  {
		
		  Risk risk = new Risk();
		 ExtendedModelMap modelStub = new ExtendedModelMap();
		 BindingResult mockResult = createMock(BindingResult.class);
		 expect(mockResult.hasErrors()).andReturn(false);
		  
		  riskController = new RiskController();
		
		  IRiskManagement serviceMock = createMock(IRiskManagement.class) ;
		  expect(serviceMock.addRisk(null, null, null, null)).andReturn("{\"MESSAGE\":\"SUCCESS\"}");
		  expect(serviceMock.getRiskCategory()).andReturn(null);
		  ReflectionTestUtils.setField(riskController, "service", serviceMock);
		  replay(serviceMock);
		  replay(mockResult);
		  
		  String view = riskController.addRisk(risk, mockResult,(Model) modelStub);
		  
		  verify(serviceMock);
		  
		  assertEquals("risk_add", view);
		  assertNotNull(modelStub.get("categories"));
		  assertNotNull(modelStub.get("state"));
		  assertEquals("success", modelStub.get("state"));
	  }
	  
	  @Test
	  public void testAddRiskPOSTForInvalidInput() throws Exception
	  {
		
		  Risk risk = new Risk();
			 ExtendedModelMap modelStub = new ExtendedModelMap();
			 BindingResult mockResult = createMock(BindingResult.class);
			 expect(mockResult.hasErrors()).andReturn(true);
			  
			  riskController = new RiskController();
			
			  IRiskManagement serviceMock = createMock(IRiskManagement.class) ;
			  expect(serviceMock.getRiskCategory()).andReturn(null);
			  ReflectionTestUtils.setField(riskController, "service", serviceMock);
			  replay(serviceMock);
			  replay(mockResult);
			  
			  String view = riskController.addRisk(risk, mockResult,(Model) modelStub);
			  
			  verify(serviceMock);
			  
			  assertEquals("risk_add", view);
			  assertNotNull(modelStub.get("categories"));
			  assertNotNull(modelStub.get("state"));
			  assertEquals("fail", modelStub.get("state"));
	  }
	  
	 

}
