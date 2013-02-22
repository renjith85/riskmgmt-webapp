package com.sample.risk.dao;

import java.util.List;

public interface RiskDAO 
{
	 public void addRisk(String riskType,String description,String point,String factor);
	 public List<?> getRiskSummary();
	 public List<?> getRisksByDateAndCategory(String date,String category);
	 public void addRiskCategory(String riskType);
	 public List<String> getRiskCategory();

}
