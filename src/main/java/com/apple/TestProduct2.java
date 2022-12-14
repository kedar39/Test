package com.apple;

import java.util.List;
import java.util.Scanner;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.build.AllowSysOut;
import com.apple.dao.ProductDao;
import com.apple.dao.ProductDao2;
import com.apple.dao.ProductDao3;
import com.apple.entity.Product;
import com.apple.utility.ProductUtility;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

public class TestProduct2 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		char ch;

		do {
			System.out.println(" ");
			
			int input = sc.nextInt(); 

			switch (input) {
			case 1:
				
				break;
				
			default:
				break;
			}
			System.out.println("Do you want to continue y/n");
			ch =sc.next().charAt(0);
		} while (ch=='y'|| ch =='Y');

		System.out.println("terminated");
		sc.close();
	}

}
