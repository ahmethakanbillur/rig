package com.getir.readingisgood.payload.request.order;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

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
public class OrderReqBook {

	@NotNull
    private Long bookId;
    private String bookName;
    @Min(1)
    @Max(5)
    @NotNull
    private Long orderBookCount;
//    @Null
//    private boolean isAvaiableStockCount;
}
