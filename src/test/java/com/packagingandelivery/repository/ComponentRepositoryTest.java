package com.packagingandelivery.repository;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.packagingandelivery.entity.Component;
import com.packagingandelivery.exceptions.ComponentNotFoundException;

@SpringBootTest
@ActiveProfiles("dev")
class ComponentRepositoryTest {

	@Autowired
	ComponentRepository componentRepository;

	@Test
	void testFindByComponentTypePositive() {
		Component extractedComponent = componentRepository.findByComponentType(existingComponent().getComponentType());
		assertEquals(existingComponent(), extractedComponent);
	}

	@Test
	void testFindByComponentTypeNegative() {
		String componentType = nonExistentComponent().getComponentType();
		assertThatExceptionOfType(ComponentNotFoundException.class).isThrownBy(() -> {
			componentRepository.findByComponentType(componentType);
		});
	}

	private Component existingComponent() {
		return new Component(1, "ACCESSORY");
	}

	private Component nonExistentComponent() {
		return new Component(3, "Some other component");
	}

}
