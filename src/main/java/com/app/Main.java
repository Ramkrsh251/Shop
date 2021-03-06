package com.app;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.app.dao.SearchServiceDAO;
import com.app.dao.impl.SearchServiceDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Customer;
import com.app.search.service.impl.SearchServiceImpl;
import com.search.service.SearchService;



public class Main {
	private static Logger log = Logger.getLogger(Main.class);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		log.info("Hey,Welcome");
		log.info("Please choose from below options");
		int ch=0;
		do {
			log.info("-----------------------------");
		log.info("1) Login as a User");
		log.info("2) Employee login");
		log.info("3) New User?Register your self");
		log.info("-----------------------------");
		try {
			ch = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
		}
		switch(ch) {
		case 1:	
			log.info("-----------------------------");
			log.info("        User Login           ");
			log.info("-----------------------------");
			SearchServiceDAO searchServiceDAO=new SearchServiceDAOImpl();
			SearchService search=new SearchServiceImpl();
			log.info("Enter your User-Name");
			String userName=sc.nextLine();
			Customer cust=new Customer();
			//cust.getCust_email();
			log.info("Enter your Password");
			String u_pwd=sc.nextLine();
			try {
				search.LoginService(userName,u_pwd);
				}
				catch(BusinessException e){
					log.warn(e);
				}
			try {
			int success=search.LoginService(userName,u_pwd);
			if(success==1) {
				log.info("-----------------------------");
				log.info("Logged-in as: "+userName);
				log.info("-----------------------------");
				log.info("Select from below options");
				log.info("1)  View the list of products with price."); 
				log.info("2) Add a product to the cart");
				log.info("3) Show my Cart");
				log.info("4)Mark my order as received");
				int userChoice=Integer.parseInt(sc.nextLine());
				switch(userChoice) {
				case 1:
					try {
						searchServiceDAO.viewProducts();
						}
						catch(BusinessException e){
							log.warn(e);
						}
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				default:
					break;
				}
				
				
				log.info("-----------------------------");
			}
			else {
				log.info("Invalid Credentials");
			}
			}
			catch(BusinessException e ) {
				log.warn(e.getMessage());
			}
			break;
		case 2:
			log.info("Employee login");
			break;
		case 3:
			SearchService searchService=new SearchServiceImpl();
			log.info("User Registration");
			Customer customer=new Customer();
			log.info("Please enter: User id,Password,User Name and User email");
			log.info("enter customer ID");
			customer.setCust_id(Integer.parseInt(sc.nextLine()));
			log.info("Enter password");
			customer.setCust_pwd(sc.nextLine());
			log.info("Enter UserName");
			customer.setCust_name(sc.nextLine());
			log.info("Enter emailID");
			customer.setCust_email(sc.nextLine());
			try {
			searchService.RegisterService(customer);
			}
			catch(BusinessException e){
				log.warn(e);
			}
			break;
		default:
			log.warn(
					"Invalid Search Option... Choice should be only number between 1-9 only. Kindly Retry ");
			break;
		}
		}
	 while (ch != 4);	
	}

}
