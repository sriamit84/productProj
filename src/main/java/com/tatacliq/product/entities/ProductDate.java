package com.tatacliq.product.entities;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class ProductDate {

	@Temporal(TemporalType.TIMESTAMP)
	private Date $date;
}
