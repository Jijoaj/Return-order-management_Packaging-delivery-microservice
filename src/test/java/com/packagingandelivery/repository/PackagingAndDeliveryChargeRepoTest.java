package com.packagingandelivery.repository;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.packagingandelivery.entity.Component;
import com.packagingandelivery.entity.PackagingAndDeliveryCharge;
import com.packagingandelivery.exceptions.PackagingAndDeliveryChargeNotFoundException;

@SpringBootTest
@ActiveProfiles("dev")
class PackagingAndDeliveryChargeRepoTest {
	@Autowired
	PackagingAndDeliveryChargeRepo packagingAndDeliveryChargeRepo;

	private Component existingComponent() {
		return new Component(1, "ACCESSORY");
	}

	private Component nonExistentComponent() {
		return new Component(3, "Some other component");
	}

	@Test
	void testGetChargeForComponentWithId() {
		PackagingAndDeliveryCharge extractedComponent = packagingAndDeliveryChargeRepo
				.getChargeForComponentWithId(existingComponent().getComponentId());
		assertEquals(existingComponent(), extractedComponent.getComponent());
	}

	@Test
	void testGetChargeForComponentWithIdNegative() {
		int componentId = nonExistentComponent().getComponentId();
		assertThatExceptionOfType(PackagingAndDeliveryChargeNotFoundException.class).isThrownBy(() -> {
			packagingAndDeliveryChargeRepo.getChargeForComponentWithId(componentId);
		});
	}

}
