package com.getir.readingisgood.model.order;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.UpdateTimestamp;

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
//@Table(name = "book", uniqueConstraints = { @UniqueConstraint(columnNames = "bookId")})
public class Book {

	@Id
	@GeneratedValue
    private Long id;
    private String name;
    private Long currentStockCount;
    @UpdateTimestamp
    private LocalDateTime updateDate;
}
