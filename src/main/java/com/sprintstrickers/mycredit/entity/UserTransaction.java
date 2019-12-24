package com.sprintstrickers.mycredit.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * UserTransaction Entity
 * @author akuthota.raghu
 * @since 23-12-2019
 */

@Setter
@Getter
@ToString
@Entity
public class UserTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer transactionId;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	private LocalDate transactionDate;
	private Double transactionAmount;

}
