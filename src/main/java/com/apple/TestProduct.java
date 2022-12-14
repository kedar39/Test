package com.apple;

import java.util.List;
import java.util.Scanner;
import com.apple.dao.ProductDao;
import com.apple.dao.ProductDao2;
import com.apple.dao.ProductDao3;
import com.apple.entity.Product;
import com.apple.utility.ProductUtility;

public class TestProduct {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		char ch;

		do {
			System.out.println("Press 1 to save");
			System.out.println("Press 2 to get Product By Id");
			System.out.println("Press 3 to update Product ");
			System.out.println("Press 4 to delete Product ");
			System.out.println("Press 5 to get all Products ");
			System.out.println("Press 6 to get Products in order ");
			System.out.println("Press 7 to perform pagination ");
			System.out.println("Press 8 to get Product By Name");
			System.out.println("Press 9 to get Product names By charaters");
			System.out.println("Press 10 to get Product If Price Greater Than");
			System.out.println("Press 11 to get get Product If Price is in between");

			System.out.println("Press 12 for projection example : Sum or max or avg  ");
			
			
			int input = sc.nextInt(); 

			switch (input) {
			case 1:
				String newProduct = ProductDao.saveProduct();
				System.out.println(newProduct);
				break;

			case 2:
				System.out.println("Enter Id");
				int id = sc.nextInt();
				Product product = ProductDao.getProductById(id);
				if(product!=null) {
					System.out.println(product);
				}else 
					System.out.println("product with id "+ id +" is not existed");
				break;

			case 3:
				Product updateProduct = ProductUtility.getProductFromUser();
				String message = ProductDao.updateProduct(updateProduct);
				System.out.println(message);
				break;

			case 4:
				System.out.println("Enter Id");
				int productId = sc.nextInt();
				String deleteProduct = ProductDao.deleteProductById(productId);
				System.out.println(deleteProduct);
				break;

			case 5:
				List<Product> list = ProductDao.getAllProduct();
				for (Product product1 : list) {
					System.out.println(product1);	
				}
				break;

			case 6:
				System.out.println("Enter order Type is asc(assending) or des(desending)");
				String orderType = sc.next();
				System.out.println("Enter orderBy productId or category or productName or productPrice or productQty ");
				String orderBy = sc.next();
				List<Product> orderedList = ProductDao.getProductByOrder(orderType, orderBy);
				if(orderedList!=null) 
					for (Product product2 : orderedList) {
						System.out.println(product2);
					}else 
						System.out.println("Column name {"+ orderBy+"}is invalid");
				break;
			case 7:
				System.out.println("How many records do you want?");
				int maxRecords = sc.nextInt();
				List<Product> listOfRecords = ProductDao.pagination(maxRecords);
				for (Product product2 : listOfRecords) {
					System.out.println(product2);
				}
				break;
			case 8:
				System.out.println("Enter Product name");
				String productName = sc.next();
				Product product1 = ProductDao2.getProductByName(productName);
				if(product1!=null) {
					System.out.println(product1);
				}
				System.out.println("Product name {"+productName+ "} is not existed");
				break;

			case 9:
				System.out.println("Enter characters in product name to get products");
				String characters = sc.next();
				List<Product> products = ProductDao2.getProductNamesByCharacters(characters);
				for (Product prd22 : products) {
					System.out.println(prd22);
				}
				break;
			case 10:
				System.out.println("Enter price , Product above this price will be shown");
				double price = sc.nextDouble();
				List<Product> product3 = ProductDao2.getProductIfPriceGreaterThan(price);
				for (Product prd11 : product3) {
					System.out.println(prd11);
				}
				
			case 11:
				System.out.println("Enter price1");
				double price1 = sc.nextDouble();
				System.out.println("Enter price2");
				double price2 = sc.nextDouble();
				List<Product> product4 = ProductDao2.getProductIfPriceBetween(price1, price2);
				for (Product prd11 : product4) {
					System.out.println(prd11);
				}
				
			case 12:
				System.out.println("Enter property(Column) name");
				String propertyName = sc.next();
				String projection = ProductDao3.projectionEx1(propertyName);
				System.out.println(projection);
				break;
				
			default:
				System.out.println("Invalide input press any number from 1 to 12");
				break;
			}
			System.out.println("Do you want to continue y/n");
			ch =sc.next().charAt(0);
		} while (ch=='y'|| ch =='Y');

		System.out.println("terminated");
		sc.close();
	}

}
