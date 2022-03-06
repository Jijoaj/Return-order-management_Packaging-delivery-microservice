package com.packagingandelivery.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;

import lombok.Getter;

@Entity
public class DeliveryItem {

	@Id
	private int deliveryId;
	@Column(nullable = false, unique = true)
	private String deliveryItemName;
	@Column(nullable = false)
	@Min(value = 0)
	@Getter
	private Long itemDeliveryPrice;
	@OneToOne(mappedBy = "deliveryItems")
	private PackagingAndDeliveryCharge packagingAndDeliveryCharge;

}
