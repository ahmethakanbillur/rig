package com.getir.readingisgood.service.stock.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.getir.readingisgood.model.order.Book;
import com.getir.readingisgood.model.order.OrderBook;
import com.getir.readingisgood.model.order.OrderDetails;
import com.getir.readingisgood.repository.stock.StockRepository;
import com.getir.readingisgood.service.stock.StockService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

	private static final Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);

	private final StockRepository stockRepository;

	@Override
	public OrderDetails existOrNotBook(OrderDetails order) {

		int bookCounter = 0;
		Optional<Book> b = null;
		for (OrderBook orderBook : order.getOrderBookList()) {
			logger.info("checking if book is avaiable stock count or not with ->", orderBook.getBookId());
			b = stockRepository.findById(orderBook.getBookId());

			if (b.get().getCurrentStockCount() > orderBook.getOrderBookCount()) {
				orderBook.setAvaiableStockCount(true);
				bookCounter++;
			}
		}
		
		order.setTotalBookCount((long) bookCounter);

		return order;
	}

	@Override
	public OrderDetails updateStocks(OrderDetails order) {
		Book book = null;
		for (OrderBook orderBook : order.getOrderBookList()) {
			logger.info("Start to update stock value for ->", orderBook.getBookId());
			if (orderBook.isAvaiableStockCount() == true) {

				book = stockRepository.findById(orderBook.getBookId()).get();

				book.setCurrentStockCount(book.getCurrentStockCount() - orderBook.getOrderBookCount());
				book.setUpdateDate(LocalDateTime.now());
				stockRepository.save(book);
				logger.info("Finished updating stock value for ->", book.getId());
			}
		}

		return order;

	}

}
