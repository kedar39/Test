package com.apple.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import com.apple.config.HibernateConfig;
import com.apple.entity.Product;

public class ProductDao2 {
	
	 static int add() {
		 System.out.println("AAAAAAA");
		return 10;
	 }
	

	static SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
	static Session session = sessionFactory.openSession();

	public static Product getProductByName(String productName ) {
		Product product = null;
		SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Criteria criteria = session.createCriteria(Product.class);
			criteria.add(Restrictions.eq("productName", productName));
			product = (Product) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	public static List<Product> getProductNamesByCharacters(String characters) {
		List<Product> list = null;
		try {
			Criteria criteria = session.createCriteria(Product.class);
			criteria.add(Restrictions.ilike("productName","%"+characters+"%"));
			criteria.add(Restrictions.in("productId", 1,2));
			list = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Product> getProductIfPriceGreaterThan(double price){
		List<Product> list = null;
		try {
			Criteria criteria = session.createCriteria(Product.class);
			criteria.add(Restrictions.gt("productPrice", price));
			list = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Product> getProductIfPriceBetween(double price1,double price2){
		List<Product> list = null;
		try {
			Criteria criteria = session.createCriteria(Product.class);
			criteria.add(Restrictions.between("productPrice", price1,price2));
			list = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
}