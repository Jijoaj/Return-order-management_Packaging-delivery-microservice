package com.packagingandelivery.repository;

import com.packagingandelivery.entity.Component;

public interface ComponentRepository {

	public Component findByComponentType(String componentType);

}
