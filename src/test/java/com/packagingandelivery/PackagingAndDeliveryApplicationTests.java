package com.packagingandelivery;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.packagingandelivery.controllers.PackageAndDeliveryChargeController;

@SpringBootTest
class PackagingAndDeliveryApplicationTests {

	@Autowired
	PackageAndDeliveryChargeController packageAndDeliveryChargeController;

	@Test
	void contextLoads() {
		assertThat(packageAndDeliveryChargeController).isNotNull();
	}

}
