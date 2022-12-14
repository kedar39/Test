package com.apple.dao;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import com.apple.config.HibernateConfig;
import com.apple.entity.Product;

public class ProductDao3 {

//	static {
//		System.out.println(" Hello ");
//	}
	
	static 	int  a =	ProductDao2.add();
	

	static SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
	static Session session = sessionFactory.openSession();


	static	Scanner sc = new Scanner(System.in);

	public static  String projectionEx1( String propertyName){
		double sum=0;
		double max=0;
		double avg=0;

		List<Double> list = null;
		System.out.println("Select Condition  max or sum or avg ");
		String condition = sc.next();

		try {
			Criteria criteria = session.createCriteria(Product.class);
		
			if(condition.equals("sum")) {
				Criteria criteria1 = criteria.setProjection(Projections.sum(propertyName));
				list = criteria1.list();
				sum = list.get(0);
				return "sum "+sum;
			} 
			if(condition.equals("max")) {
				Criteria criteria1 = criteria.setProjection(Projections.max(propertyName));
				list = criteria1.list();
				max = list.get(0);
				return "max "+max;
			}
			if(condition.equals("avg")) {
				Criteria criteria1 = criteria.setProjection(Projections.avg(propertyName));
				list = criteria1.list();
				avg = list.get(0);
				return "avg "+avg;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Please enter : 'sum' or 'max' or 'avg'";
	}

	public static List<Product> getProductsUsingAndLogic(String category , double price){
		List<Product> list = null;
		try {
			Criteria criteria = session.createCriteria(Product.class);
			criteria.add(Restrictions.and(Restrictions.eq("category", category),Restrictions.gt("productPrice",price )));
			list = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	

}