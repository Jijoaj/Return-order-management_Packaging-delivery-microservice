package com.packagingandelivery.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Min;

import lombok.Getter;

@Entity
public class PackagingItem {
	@Id
	private int itemId;
	@Column(nullable = false, unique = true)
	private String itemName;
	@Getter
	@Column(nullable = false)
	@Min(value = 0)
	private Long packagingPrice;
	@ManyToMany
	private List<PackagingAndDeliveryCharge> packagingAndDeliveryCharge = new ArrayList<>();

}
