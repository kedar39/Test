package com.apple.utility;

import java.util.Scanner;
import com.apple.entity.Product;

public class ProductUtility {

	static Scanner sc = new Scanner(System.in);

	public static Product getProductFromUser() {

		System.out.println("Enter product ID");
		int productId = sc.nextInt();

		System.out.println("Enter product name");
		String productName = sc.next();

		System.out.println("Enter product Price ");
		double productPrice = sc.nextDouble();

		System.out.println("Enter product Qty");
		int productQty = sc.nextInt();

		System.out.println("Enter product category");
		String productCategory = sc.next();
		
		
		
		Product product = new Product(productId, productName, productPrice, productQty, productCategory);

		return product;

	}
	
	
}
