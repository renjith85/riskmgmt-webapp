package com.sample.risk.dao;

import java.math.BigDecimal;
import java.util.Date;

public class RiskSummaryBO {
	
	private String riskCategory;
	private Number noOfRisks;
	private BigDecimal totalPoints;
	private Number totalFactors;
	private Date riskDate;
	
	public RiskSummaryBO()
	{}
	
	public String getRiskCategory() {
		return riskCategory;
	}
	public void setRiskCategory(String riskCategory) {
		this.riskCategory = riskCategory;
	}
	public Number getNoOfRisks() {
		return noOfRisks;
	}
	public void setNoOfRisks(Number noOfRisks) {
		this.noOfRisks = noOfRisks;
	}
	public BigDecimal getTotalPoints() {
		return totalPoints;
	}
	public void setTotalPoints(BigDecimal totalPoints) {
		this.totalPoints = totalPoints;
	}
	public Number getTotalFactors() {
		return totalFactors;
	}
	public void setTotalFactors(Number totalFactors) {
		this.totalFactors = totalFactors;
	}
	public Date getRiskDate() {
		return riskDate;
	}
	public void setRiskDate(Date riskDate) {
		this.riskDate = riskDate;
	}

}
