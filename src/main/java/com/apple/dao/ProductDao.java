package com.apple.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import com.apple.config.HibernateConfig;
import com.apple.entity.Product;
import com.apple.utility.ProductUtility;


public class ProductDao {

	static SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
	static Session session = sessionFactory.openSession();


	public static String saveProduct() {
		Product product = ProductUtility.getProductFromUser();
		boolean added =false;
		String msg="Not saved";
		Product prd = session.get(Product.class, product.getProductId());

		try {
			if(prd==null) {
				Transaction transaction = session.beginTransaction();
				session.save(product);
				transaction.commit();
				added=true;
			}
			else {
				msg ="product with id "+ product.getProductId() +" is already existed";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(added)
			msg = "saved !!";
		return msg;
	}

	public static Product getProductById(int id) {
		Product  product  =null;

		try {
			product = session.get(Product.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	public static String updateProduct(Product product) {
		int id = product.getProductId();
		boolean added =false;
		String msg="Not updted";
		try {
			Product prd = session.get(Product.class, id);
			if (prd!=null) {
				Session session = sessionFactory.openSession();
				session.update(product);
				Transaction transaction = session.beginTransaction();
				transaction.commit();
				added=true;	
			} else {
				msg ="product with id "+ id +" is not existed";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(added)
			msg = "updted !!";
		return msg;
	}

	public static String deleteProductById(int id){
		boolean added =false;
		String msg="Not deleted";
		try {
			Product product = session.get(Product.class, id);
			if (product!=null) {
				session.delete(product);
				Transaction transaction = session.beginTransaction();
				transaction.commit();
				added=true;
			} else {
				msg ="product with id "+ id +" is not existed";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(added)
			msg = "deleted !!";
		return msg;
	}

	public static List<Product> getAllProduct(){
		List<Product> list =null;
		try {
			Criteria criteria = session.createCriteria(Product.class);
			list = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<Product> getProductByOrder(String orderType,String orderBy){
		List<Product> list =null;
		try {
			int key = 0;
			if (orderBy.equals("productId")||orderBy.equals("category")||orderBy.equals("productName")||orderBy.equals("productPrice")||orderBy.equals("productQty")) {
				key=1;
			}
			switch (key) {
			case 1:
				Criteria criteria = session.createCriteria(Product.class);
				if ("asc".equalsIgnoreCase(orderType)) {
					Criteria order = criteria.addOrder(Order.asc(orderBy));
				}else if ("des".equalsIgnoreCase(orderType)) {
					Criteria order = criteria.addOrder(Order.desc(orderBy));
				} else {
					System.out.println("Invalid order Type");
				}
				list = criteria.list();
				break;
			default:
				System.out.println("Invalide column name ");
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<Product> pagination(int maxRecord){
		List<Product> list =null;
		try {
			Criteria criteria = session.createCriteria(Product.class);
			criteria.setFirstResult(0);
			criteria.setMaxResults(maxRecord);
			list = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	



}