package com.getir.readingisgood.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.getir.readingisgood.mapper.order.OrderMapper;
import com.getir.readingisgood.payload.request.order.OrderReq;
import com.getir.readingisgood.payload.response.order.OrderDetailsResp;
import com.getir.readingisgood.payload.response.order.OrderListResp;
import com.getir.readingisgood.payload.response.order.OrderResp;
import com.getir.readingisgood.service.order.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Transactional
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	private final OrderService orderService;
	private final OrderMapper orderMapper;

	@PostMapping("/saveOrder")
	public OrderResp saveOrder(@RequestBody @Valid OrderReq orderReq) {

		logger.info("saveOrder request ->", orderReq);
		return new OrderResp(orderService.saveOrder(orderMapper.mapToOrder(orderReq)));
	}

	@GetMapping(value = "/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public OrderDetailsResp getOrderDetails(@PathVariable("orderId") Long orderId) {
		logger.info("getOrderDetails request ->", orderId);
		return orderMapper.mapToOrderDetailResponse(orderService.getOrderDetails(orderId));
	}

	@GetMapping("/dateBetween")
	public List<OrderListResp> getOrderListIntervalDate(
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam LocalDateTime startDate,
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam LocalDateTime endDate) {
		logger.info("getOrderListIntervalDate request ->");
		return orderMapper.mapToOrderResponseList(orderService.getOrderListIntervalDate(startDate, endDate));
	}
}
