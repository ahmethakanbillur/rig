package com.getir.readingisgood.repository.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.getir.readingisgood.model.order.Book;

@Repository
public interface StockRepository  extends JpaRepository<Book, Long> {

}
