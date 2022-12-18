package com.getir.readingisgood.payload.response.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.getir.readingisgood.model.order.OrderBook;
import com.getir.readingisgood.model.order.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsResp {
	private Long id;
	private Long customerId;
	private List<OrderBook> orderBookList = new ArrayList<>();
	private LocalDateTime createDate;
	private Long totalBookCount;
	private Status orderStatus;
}
