package com.getir.readingisgood.model.order;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OrderBook {

	@Id
	private Long bookId;
	private String bookName;
	private Long orderBookCount;
	private boolean isAvaiableStockCount;
}
