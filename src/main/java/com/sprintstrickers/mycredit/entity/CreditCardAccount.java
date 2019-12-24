package com.sprintstrickers.mycredit.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@SequenceGenerator(name = "accountsequence", initialValue = 10001000)
@SequenceGenerator(name = "sequence", initialValue = 100)
@Entity
public class CreditCardAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accountsequence")
	private Long creditCardNumber;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	private LocalDate validFrom;
	private LocalDate validTill;
	private Double creditLimit;

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
	private Integer cvv;
	private Double availableLimit;

}
