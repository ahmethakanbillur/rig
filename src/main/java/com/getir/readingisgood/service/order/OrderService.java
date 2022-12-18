package com.getir.readingisgood.service.order;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.getir.readingisgood.model.order.OrderDetails;

public interface OrderService {

	public Long saveOrder(OrderDetails order);
	public List<OrderDetails> getAllOrdersListOfCustomer(Long customerId, Pageable paging);
	public OrderDetails getOrderDetails(Long orderId);
	public List<OrderDetails> getOrderListIntervalDate(LocalDateTime startDate, LocalDateTime endDate);
	public List<OrderDetails> getOrderListCustomerForMontly(Long id,LocalDateTime startDate, LocalDateTime endDate);
}
