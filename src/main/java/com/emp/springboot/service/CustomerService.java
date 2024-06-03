package com.emp.springboot.service;



import com.emp.springboot.model.Customer;



public  interface CustomerService {
	public abstract  String registerCustomer(Customer c);
	public Customer checkcustomerlogin(String email,String pwd);
}
