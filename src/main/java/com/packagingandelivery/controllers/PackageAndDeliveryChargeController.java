package com.packagingandelivery.controllers;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.packagingandelivery.model.PackagingAndDeliveryChargeResponse;
import com.packagingandelivery.services.ChargeCalculationService;

import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Validated
public class PackageAndDeliveryChargeController {

	@Autowired
	ChargeCalculationService chargeCalculationService;

	@CrossOrigin
	@GetMapping("/GetPackagingDeliveryCharge")
	public PackagingAndDeliveryChargeResponse getPackagingDeliveryCharge(
			@Valid @RequestParam @ApiParam(required = true, value = "Component type , for example : Accessory , Integral item") String componentType,
			@Valid @RequestParam @Min(value = 1) @Max(value = Integer.MAX_VALUE) @ApiParam(value = "count of the component ,should be greater than 1") Integer count) {
		log.info("request for packaging and delivery charge is recieved for component ->{} quantity ->{}",
				componentType, count);
		return new PackagingAndDeliveryChargeResponse(
				chargeCalculationService.calculateChargeforComponent(componentType, count));
	}

}
