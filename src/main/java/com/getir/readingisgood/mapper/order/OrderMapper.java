package com.getir.readingisgood.mapper.order;

import java.util.List;

import org.mapstruct.Mapper;

import com.getir.readingisgood.model.order.OrderDetails;
import com.getir.readingisgood.payload.request.order.OrderReq;
import com.getir.readingisgood.payload.response.order.OrderDetailsResp;
import com.getir.readingisgood.payload.response.order.OrderListResp;

@Mapper
public interface OrderMapper {

	OrderDetails mapToOrder(OrderReq order);
	List<OrderListResp> mapToOrderResponseList(List<OrderDetails> orders);
	OrderDetailsResp mapToOrderDetailResponse(OrderDetails orderDetails);
}
