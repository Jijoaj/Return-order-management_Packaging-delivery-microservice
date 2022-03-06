package com.packagingandelivery.services;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.packagingandelivery.entity.Component;
import com.packagingandelivery.exceptions.ComponentChargeDetailsNotFoundException;
import com.packagingandelivery.exceptions.ComponentNotFoundException;
import com.packagingandelivery.repository.ComponentRepository;

@SpringBootTest
@ActiveProfiles("dev")
class ChargeCalculationServiceTest {

	@MockBean()
	ComponentRepository componentRepository;

	@Autowired
	ChargeCalculationService chargeCalculationService;

	private Component existingComponent() {
		return new Component(1, "ACCESSORY");
	}

	private Component nonExistentComponent() {
		return new Component(3, "Some other component");
	}

	@Test
	void testCalculateChargeforComponentExistingComponent() {
		when(componentRepository.findByComponentType(existingComponent().getComponentType()))
				.thenReturn(existingComponent());
		Long calculatedChargeForExistingComponent = chargeCalculationService
				.calculateChargeforComponent(existingComponent().getComponentType(), 1);
		assertEquals(200L, calculatedChargeForExistingComponent);
	}

	@Test
	void testCalculateChargeforComponentNonExistingComponent() {
		Component nonExistentComponent = nonExistentComponent();
		String componentType = nonExistentComponent.getComponentType();
		when(componentRepository.findByComponentType(any())).thenThrow(
				new ComponentNotFoundException("component with component type : " + componentType + " not found"));

		assertThatExceptionOfType(ComponentChargeDetailsNotFoundException.class).isThrownBy(() -> {

			chargeCalculationService.calculateChargeforComponent(componentType, 1);
		});
	}

	@Test
	void testCalculateChargeforComponentGeneralException() {
		String componentType = nonExistentComponent().getComponentType();
		when(componentRepository.findByComponentType(any())).thenThrow(RuntimeException.class);
		assertThatExceptionOfType(ComponentChargeDetailsNotFoundException.class).isThrownBy(() -> {
			chargeCalculationService.calculateChargeforComponent(componentType, 1);
		});
	}

}
