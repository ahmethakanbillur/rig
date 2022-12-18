package com.getir.readingisgood.service.stock;

import com.getir.readingisgood.model.order.OrderDetails;

public interface StockService {

	public OrderDetails existOrNotBook(OrderDetails order);

	public OrderDetails updateStocks(OrderDetails order);

}
