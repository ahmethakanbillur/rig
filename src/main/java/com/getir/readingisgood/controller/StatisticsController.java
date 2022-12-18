package com.getir.readingisgood.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.getir.readingisgood.mapper.order.OrderMapper;
import com.getir.readingisgood.payload.response.order.OrderListResp;
import com.getir.readingisgood.service.order.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/statistic")
@RequiredArgsConstructor
@Transactional
public class StatisticsController {

	private static final Logger logger = LoggerFactory.getLogger(StatisticsController.class);

	private final OrderService orderService;
	private final OrderMapper orderMapper;

	@GetMapping("/customer/{id}")
	public List<OrderListResp> getOrderListCustomerForMontly(@PathVariable("id") Long id,
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam LocalDateTime startDate,
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam LocalDateTime endDate) {

		logger.info("getOrderListCustomerForMontly request for this customer->", Long.toString(id));
		return orderMapper.mapToOrderResponseList(orderService.getOrderListCustomerForMontly(id,startDate,endDate));
	}
}
