package com.sample.risk.service;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sample.risk.dao.RiskDAO;
import com.sample.risk.dao.RiskData;
import com.sample.risk.dao.RiskSummaryBO;

@Component
public class RiskManagementService implements IRiskManagement{
	
	Logger logger = Logger.getLogger("RiskManagementService");
	
	@Autowired
	private RiskDAO riskDAO;
	
	@GET
	@Path("/riskCategory")
	public String getRiskCategory()
	{
		JSONObject jsonObj = new JSONObject();
		try
		{
			
		  List<?> categoryList =  riskDAO.getRiskCategory();
		  if(null != categoryList && !categoryList.isEmpty())
		  {
			  Iterator<?> categoryListIterator = categoryList.iterator();
			  
			  JSONArray array = new JSONArray();
			  
		      while(categoryListIterator.hasNext())
		      {
		      	String result = (String)categoryListIterator.next();
		      	array.put(result);
		      }
			
				
				try {
					jsonObj.put("riskCategory", array);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				jsonObj.put("MESSAGE", "SUCCESS");
				
				logger.log(Level.INFO,"json return string:" + jsonObj.toString());
				return jsonObj.toString();
		  }
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "system failure");
			try {
				jsonObj.put("MESSAGE", "SYSTEM FAILURE");
				return jsonObj.toString();
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return null;
	}
	
	@POST
	@Path("/riskCategory")
	public String addRiskCategory(@FormParam("riskType")String riskType)
	{
		  JSONObject summaryJSON = new JSONObject();
		  
		try
		{
			riskDAO.addRiskCategory(riskType);
			try {
				summaryJSON.put("MESSAGE", "SUCCESS");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "system failure");
			try {
				summaryJSON.put("MESSAGE", "SYSTEM FAILURE");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return summaryJSON.toString();
	}
	
	@GET
	@Path("/riskSummary")
	public String riskSummary()
	{
	        
	        JSONObject summaryJSON = new JSONObject();
	        
	        try
	        {
		        JSONArray array = new JSONArray();
			        
			        List<?> riskList = riskDAO.getRiskSummary();
			        Iterator<?> riskListIterator = riskList.iterator();
			        while(riskListIterator.hasNext())
			        {
			        	RiskSummaryBO result = (RiskSummaryBO)riskListIterator.next();
			        	try {
			        		JSONObject resultObject = new JSONObject();
							resultObject.put("numberOfRisks", result.getNoOfRisks());
							resultObject.put("type", result.getRiskCategory());
							resultObject.put("date", result.getRiskDate());
							resultObject.put("totalPoints", result.getTotalPoints());
							resultObject.put("totalFactor", result.getTotalFactors());
							
							array.put(resultObject);
						} catch (JSONException e) {
							e.printStackTrace();
						}
			        }
			        
			        try {
						summaryJSON.put("summary", array);
						summaryJSON.put("MESSAGE", "SUCCESS");
					} catch (JSONException e) {
						e.printStackTrace();
					}
		       
		      System.err.println(summaryJSON.toString());
	        }
	      catch(Exception e)
			{
				logger.log(Level.SEVERE, "system failure");
				try {
					summaryJSON.put("MESSAGE", "SYSTEM FAILURE");
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			return summaryJSON.toString();
	}
	
	
	@GET
	@Path("/riskSummary/{date}/{category}")
	public String riskDetails(@PathParam("date")String date,@PathParam("category")String category)
	{
        JSONObject summaryJSON = new JSONObject();
        try
        {
        JSONArray array = new JSONArray();
	        
	        List<?> riskList = riskDAO.getRisksByDateAndCategory(date, category);
	        Iterator<?> riskListIterator = riskList.iterator();
	        while(riskListIterator.hasNext())
	        {
	        	RiskData result = (RiskData)riskListIterator.next();
	        	try {
	        		
	        		JSONObject resultObject = new JSONObject();
					resultObject.put("description", result.getDescription());
					resultObject.put("point", result.getPoint());
					resultObject.put("factor", result.getFactor());
					array.put(resultObject);
				} catch (JSONException e) {
					e.printStackTrace();
				}
	        }
	        
	        try {
				summaryJSON.put("details", array);
				summaryJSON.put("MESSAGE", "SUCCESS");
			} catch (JSONException e) {
				e.printStackTrace();
			}
        }
        catch(Exception e)
		{
			logger.log(Level.SEVERE, "system failure");
			try {
				summaryJSON.put("MESSAGE", "SYSTEM FAILURE");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

			return summaryJSON.toString();
	}
	
	@POST
	@Path("/newRisks")
	public String addRisk(@FormParam("riskType")String riskType,
			@FormParam("riskDescription")String description,
			@FormParam("riskPoints")String point,
			@FormParam("riskFactor")String factor)
	{	
		  JSONObject summaryJSON = new JSONObject();
		  
		try
		{
			riskDAO.addRisk(riskType, description, point, factor);
			try {
				summaryJSON.put("MESSAGE", "SUCCESS");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "system failure");
			
			try {
				summaryJSON.put("MESSAGE", "SYSTEM FAILURE");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return summaryJSON.toString();
		
	}
		
}