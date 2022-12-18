package com.getir.readingisgood.repository.order;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.getir.readingisgood.model.order.OrderDetails;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<OrderDetails, Long>, JpaRepository<OrderDetails, Long> {

	List<OrderDetails> findByCustomerId(Long customerId,Pageable paging);
	List<OrderDetails> findByCreateDateBetween(LocalDateTime startDate,LocalDateTime endDate);
}
