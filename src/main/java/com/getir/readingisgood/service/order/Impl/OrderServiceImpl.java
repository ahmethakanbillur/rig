package com.getir.readingisgood.service.order.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.getir.readingisgood.model.order.OrderDetails;
import com.getir.readingisgood.model.order.Status;
import com.getir.readingisgood.repository.order.OrderRepository;
import com.getir.readingisgood.service.order.OrderService;
import com.getir.readingisgood.service.stock.StockService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

	private final StockService stockService;
	private final OrderRepository orderRepository;

	@Override
	public Long saveOrder(OrderDetails order) {

		OrderDetails orderDetails = stockService.existOrNotBook(order);

		logger.info("Start saving order->", order);
		OrderDetails savedOrderDetails = orderRepository.save(orderDetails);
		logger.info("Finish order saved->", order);

		logger.info("Start updating order's stock->", order);
		savedOrderDetails = stockService.updateStocks(savedOrderDetails);
		logger.info("Finished updating order's stock->", order);

		savedOrderDetails.setOrderStatus(Status.AVAILABLE);
		orderRepository.save(savedOrderDetails);

		return savedOrderDetails.getId();
	}

	@Override
	public List<OrderDetails> getAllOrdersListOfCustomer(Long customerId, Pageable paging) {

		logger.info("get all order of customer->", customerId);
		return orderRepository.findByCustomerId(customerId, paging);
	}

	@Override
	public OrderDetails getOrderDetails(Long orderId) {
		logger.info("retrieving order details for orderId ->", orderId);
		Optional<OrderDetails> orderDetails = orderRepository.findById(orderId);
		if (orderDetails.isEmpty())
			throw new ObjectNotFoundException(orderId, "Order not found with orderId");
		return orderDetails.get();
	}

	@Override
	public List<OrderDetails> getOrderListIntervalDate(LocalDateTime startDate, LocalDateTime endDate) {
		logger.info("get order list between date->");
		return orderRepository.findByCreateDateBetween(startDate, endDate);
	}

	@Override
	public List<OrderDetails> getOrderListCustomerForMontly(Long id,LocalDateTime startDate, LocalDateTime endDate) {
		logger.info("get order list of customer for montly->");
		return orderRepository.findByCreateDateBetween(startDate, endDate);
	}

}
