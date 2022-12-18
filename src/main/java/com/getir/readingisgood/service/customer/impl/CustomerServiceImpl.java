package com.getir.readingisgood.service.customer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.getir.readingisgood.model.customer.Customer;
import com.getir.readingisgood.payload.response.customer.MessageResp;
import com.getir.readingisgood.payload.response.customer.SignInResp;
import com.getir.readingisgood.repository.customer.CustomerRepository;
import com.getir.readingisgood.security.service.CustomerDetailsImpl;
import com.getir.readingisgood.service.customer.CustomerService;
import com.getir.readingisgood.util.JwtUtils;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Override
	public ResponseEntity<?> authenticateCustomer(String userName, String password) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(userName, password));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		CustomerDetailsImpl customerDetails = (CustomerDetailsImpl) authentication.getPrincipal();

		ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(customerDetails);

		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(
				new SignInResp(customerDetails.getId(), customerDetails.getUsername(), customerDetails.getEmail()));
	}

	@Override
	public ResponseEntity<?> registerCustomer(String userName, String email, String pw) {

		if (customerRepository.existsByUsername(userName)) {
			return ResponseEntity.badRequest().body(new MessageResp("Error: Username is already taken!"));
		}

		if (customerRepository.existsByEmail(email)) {
			return ResponseEntity.badRequest().body(new MessageResp("Error: Email is already in use!"));
		}

		Customer customer = new Customer(null, userName, email, encoder.encode(pw));

		customerRepository.save(customer);

		return ResponseEntity.ok(new MessageResp("User registered successfully!"));
	}

	@Override
	public ResponseEntity<?> logoutCustomer() {
		ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
				.body(new MessageResp("You've been signed out!"));
	}

}
