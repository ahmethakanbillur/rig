package com.getir.readingisgood.service.customer;

import org.springframework.http.ResponseEntity;

public interface CustomerService {
	
	ResponseEntity<?> authenticateCustomer(String userName,String password);

	ResponseEntity<?> registerCustomer(String userName,String password, String pw);

	ResponseEntity<?> logoutCustomer();
}

