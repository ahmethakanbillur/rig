package com.getir.readingisgood.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.getir.readingisgood.mapper.order.OrderMapper;
import com.getir.readingisgood.payload.request.customer.SignInReq;
import com.getir.readingisgood.payload.request.customer.SignUpReq;
import com.getir.readingisgood.payload.response.order.OrderListResp;
import com.getir.readingisgood.service.customer.CustomerService;
import com.getir.readingisgood.service.order.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
@Transactional
public class CustomerController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	private final CustomerService customerService;

	private final OrderService orderService;

	private final OrderMapper orderMapper;

	@GetMapping(value = "/health")
	public String custom() {
		return "live";
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateCustomer(@Valid @RequestBody SignInReq signInReq) {

		logger.info("authenticateCustomer for customer->", signInReq.getUsername());
		return customerService.authenticateCustomer(signInReq.getUsername(), signInReq.getPassword());
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerCustomer(@Valid @RequestBody SignUpReq signUpReq) {

		logger.info("registerCustomer for customer->", signUpReq.getUsername());
		return customerService.registerCustomer(signUpReq.getUsername(), signUpReq.getEmail(), signUpReq.getPassword());
	}

	@PostMapping("/signout")
	public ResponseEntity<?> logoutCustomer() {

		logger.info("logoutCustomer");
		return customerService.logoutCustomer();
	}

	@GetMapping("/customer/{id}")
	public List<OrderListResp> getOrderListCustomer(@PathVariable("id") Long id, @RequestParam() int page,
			@RequestParam() int size) {

		logger.info("getOrderListCustomer request for this customer->", Long.toString(id));
		Pageable paging = PageRequest.of(page, size);
		return orderMapper.mapToOrderResponseList(orderService.getAllOrdersListOfCustomer(id, paging));
	}
}