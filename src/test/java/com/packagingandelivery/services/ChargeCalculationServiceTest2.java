package com.packagingandelivery.services;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.packagingandelivery.entity.Component;
import com.packagingandelivery.exceptions.ComponentChargeDetailsNotFoundException;
import com.packagingandelivery.exceptions.PackagingAndDeliveryChargeNotFoundException;
import com.packagingandelivery.repository.PackagingAndDeliveryChargeRepo;

@SpringBootTest
@ActiveProfiles("dev")
class TestChargeCalculationService2 {

	@MockBean()
	PackagingAndDeliveryChargeRepo packagingAndDeliveryChargeRepo;

	@Autowired
	ChargeCalculationService chargeCalculationService;

	private Component existingComponent() {
		return new Component(1, "ACCESSORY");
	}

	@Test
	void testCalculateChargeforComponentNonExistingComponent() {
		String componentType = existingComponent().getComponentType();
		when(packagingAndDeliveryChargeRepo.getChargeForComponentWithId(existingComponent().getComponentId()))
				.thenThrow(new PackagingAndDeliveryChargeNotFoundException("packaging and delivery charge not found"));
		assertThatExceptionOfType(ComponentChargeDetailsNotFoundException.class).isThrownBy(() -> {

			chargeCalculationService.calculateChargeforComponent(componentType, 1);
		});
	}

}
