package com.sample.risk.dao;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="risk_metadata")
public class RiskCategoryBO {
	
	@Column(name="risk_type")
	private String category;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	public RiskCategoryBO()
	{}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
