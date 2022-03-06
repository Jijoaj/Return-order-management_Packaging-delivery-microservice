package com.packagingandelivery.repository;

import com.packagingandelivery.entity.PackagingAndDeliveryCharge;

public interface PackagingAndDeliveryChargeRepo {

	public PackagingAndDeliveryCharge getChargeForComponentWithId(Integer componentID);
}
