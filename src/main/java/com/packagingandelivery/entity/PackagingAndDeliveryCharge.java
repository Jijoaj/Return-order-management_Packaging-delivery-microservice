package com.packagingandelivery.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import lombok.Getter;

@Entity
public class PackagingAndDeliveryCharge {
	@Id
	private int id;
	@Getter
	@ManyToMany(mappedBy = "packagingAndDeliveryCharge", fetch = FetchType.EAGER)
	private List<PackagingItem> packagingItems = new ArrayList<>();
	@OneToOne
	@Getter
	private DeliveryItem deliveryItems;
	@OneToOne
	@Getter
	private Component component;

}
