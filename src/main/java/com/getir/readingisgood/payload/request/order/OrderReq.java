package com.getir.readingisgood.payload.request.order;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
public class OrderReq {

	@NotNull(message = "Customer field cannot be empty.Please enter customer")
	private Long customerId;
	@Valid
	@NotNull(message = "Book field cannot be empty.Please enter book")
	private List<OrderReqBook> orderBookList;
	
}
