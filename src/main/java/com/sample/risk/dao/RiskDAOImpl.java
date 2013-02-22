package com.sample.risk.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class RiskDAOImpl implements RiskDAO {

	Logger logger = Logger.getLogger("RiskDAOImpl");

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void addRisk(String riskType, String description, String point,
			String factor) {
		try {

			RiskData riskData = new RiskData();
			riskData.setDate(new Date());
			riskData.setDescription(description.trim());
			if(null == point || point.trim().isEmpty())
			{
				riskData.setPoint(new BigDecimal("0"));
			}else{
				riskData.setPoint(new BigDecimal(point));
			}
			riskData.setType(riskType.trim());

			this.sessionFactory.getCurrentSession().save(riskData);
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Transactional
	public void addRiskCategory(String riskType) {
		try {
		RiskCategoryBO riskCategory = new RiskCategoryBO();
		riskCategory.setCategory(riskType);
		this.sessionFactory.getCurrentSession().save(riskCategory);
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	@SuppressWarnings("rawtypes")
	@Transactional(readOnly=true)
	public List<RiskSummaryBO> getRiskSummary() {
		
		SQLQuery query = this.sessionFactory.getCurrentSession().createSQLQuery("SELECT count(r.type) AS noOfRisks,r.type AS riskCategory," +
				"r.date AS riskDate,sum(r.point) AS totalPoints," +
				"sum(datediff(CURDATE(),r.date) * r.point/2) AS totalFactors FROM risk_data r WHERE r.date > DATE_SUB(CURDATE(),INTERVAL 3 DAY) " +
				"GROUP BY r.type,r.date").addScalar("noOfRisks").
				addScalar("riskCategory").addScalar("riskDate").addScalar("totalPoints").addScalar("totalFactors");
		
		
		query.setResultTransformer(Transformers.aliasToBean(RiskSummaryBO.class));
		List l = query.list();
		Iterator queryIterator = l.iterator();

		List<RiskSummaryBO> riskSummaryList = new ArrayList<RiskSummaryBO>();
		while (queryIterator.hasNext()) {
			RiskSummaryBO result = (RiskSummaryBO) queryIterator.next();
			riskSummaryList.add(result);
		}
		return riskSummaryList;
	}

	@SuppressWarnings("rawtypes")
	@Transactional(readOnly=true)
	public List<RiskData> getRisksByDateAndCategory(String date, String category) {
			
		SQLQuery query = this.sessionFactory.getCurrentSession().createSQLQuery("SELECT r.description AS description,r.point AS point," +
				"(datediff(r.date,CURDATE()) * r.point/2) AS factor FROM risk_data r WHERE r.type=:type AND r.date =:date ORDER BY r.description").addScalar("description").
				addScalar("point").addScalar("factor");
		
		
		query.setParameter("type", category.trim());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date modifiedDate = null ;
		try {
			modifiedDate = format.parse(date.trim());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		query.setParameter("date", modifiedDate);
		
		query.setResultTransformer(Transformers.aliasToBean(RiskData.class));
	
		List l = query.list();
		Iterator queryIterator = l.iterator();

		List<RiskData> riskList = new ArrayList<RiskData>();
		while (queryIterator.hasNext()) {
			RiskData result = (RiskData) queryIterator.next();
			riskList.add(result);
		}
		return riskList;
	}


	@Transactional(readOnly=true)
	public List<String> getRiskCategory() {
		Query query = this.sessionFactory.getCurrentSession().createQuery(
						"Select riskCategoryBO.category FROM RiskCategoryBO riskCategoryBO");

		Iterator<?> queryIterator = query.iterate();
		List<String> riskList = new ArrayList<String>();
		while (queryIterator.hasNext()) {
			String result = (String) queryIterator.next();
			riskList.add(result);
		}
		return riskList;
	}

}
