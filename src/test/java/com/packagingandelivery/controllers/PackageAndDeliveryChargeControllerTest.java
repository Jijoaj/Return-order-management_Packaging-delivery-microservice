package com.packagingandelivery.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.packagingandelivery.entity.Component;
import com.packagingandelivery.exceptions.ComponentChargeDetailsNotFoundException;
import com.packagingandelivery.services.ChargeCalculationService;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
class PackageAndDeliveryChargeControllerTest {

	@MockBean
	ChargeCalculationService chargeCalculationService;

	@Autowired
	private MockMvc mockMvc;
	String count;

	private Component existingComponent() {
		return new Component(1, "Accessory");
	}

	private Component nonExistentComponent() {
		return new Component(3, "Some other component");
	}

	@BeforeEach
	void setUp() {
		count = "1";
	}

	@Test
	void testGetPackagingDeliveryCharge() throws Exception {
		when(chargeCalculationService.calculateChargeforComponent(existingComponent().getComponentType(), 1))
				.thenReturn(200L);
		this.mockMvc
				.perform(get("/GetPackagingDeliveryCharge")
						.param("componentType", existingComponent().getComponentType()).param("count", count))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.packagingAndDeliveryCharge").value("200"));

	}

	@Test
	void testGetPackagingDeliveryChargeForNonExistentComponent() throws Exception {

		when(chargeCalculationService.calculateChargeforComponent(nonExistentComponent().getComponentType(), 1))
				.thenThrow(new ComponentChargeDetailsNotFoundException(
						"component with component type : " + nonExistentComponent().getComponentType() + " not found"));
		this.mockMvc
				.perform(get("/GetPackagingDeliveryCharge")
						.param("componentType", nonExistentComponent().getComponentType()).param("count", count))
				.andDo(print()).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.componentType").value(nonExistentComponent().getComponentType()))
				.andExpect(jsonPath("$.count").value(count));

	}

	@Test
	void testGetPackagingDeliveryChargeForNonExistentCharge() throws Exception {

		this.mockMvc
				.perform(get("/GetPackagingDeliveryCharge")
						.param("componentType", nonExistentComponent().getComponentType()).param("count", "-1"))
				.andDo(print()).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.componentType").value(nonExistentComponent().getComponentType()))
				.andExpect(jsonPath("$.count").value("-1"));

	}

}
