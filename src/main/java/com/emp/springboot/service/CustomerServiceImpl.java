package com.emp.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.springboot.model.Customer;
import com.emp.springboot.repository.CustomerRepository;

@Service
public  class CustomerServiceImpl implements CustomerService{
	@Autowired
	CustomerRepository customerRepository;
	@Override
	public String registerCustomer(Customer c) {
		customerRepository.save(c);
		return "Customer Registered Successfully";
	}
	@Override
	public Customer checkcustomerlogin(String email, String pwd) {
		 return customerRepository.checkcustomerlogin(email, pwd);
	}

}
