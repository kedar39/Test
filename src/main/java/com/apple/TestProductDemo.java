package com.apple;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import com.apple.config.HibernateConfig;
import com.apple.entity.Product;

public class TestProductDemo {
	static SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
	static Session session = sessionFactory.openSession();

	static	Scanner sc = new Scanner(System.in);

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

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

				System.out.println("Enter category");
				String category = sc.next();
				System.out.println("Enter price");
				double price = sc.nextDouble();
				List<Product> output = getProductsUsingAndLogic(category, price);
				System.out.println(output.isEmpty());
				
				for (Product product : output) {
					
					System.out.println(product);
				}

		


	}


}
