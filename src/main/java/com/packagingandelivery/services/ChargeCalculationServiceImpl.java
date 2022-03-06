package com.packagingandelivery.services;

import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packagingandelivery.entity.Component;
import com.packagingandelivery.entity.PackagingAndDeliveryCharge;
import com.packagingandelivery.entity.PackagingItem;
import com.packagingandelivery.exceptions.ComponentChargeDetailsNotFoundException;
import com.packagingandelivery.exceptions.ComponentNotFoundException;
import com.packagingandelivery.exceptions.PackagingAndDeliveryChargeNotFoundException;
import com.packagingandelivery.repository.ComponentRepository;
import com.packagingandelivery.repository.PackagingAndDeliveryChargeRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ChargeCalculationServiceImpl implements ChargeCalculationService {

	private static final String ERROR_FETCHING_DATA_FROM_DATABASE = "Error Fetching data from database";

	@Autowired
	ComponentRepository componentRepository;

	@Autowired
	PackagingAndDeliveryChargeRepo packagingAndDeliveryChargeRepo;

	@Override
	public Long calculateChargeforComponent(String componentType, @Min(value = 1) int count) {
		log.info("Started calculating charge for the component : {} and count : {}", componentType, count);
		try {
			log.info("Reading component details from Database for the component type :{}", componentType);
			Component selectedComponent = componentRepository.findByComponentType(componentType);

			log.info("Reading packaging and delivery charge details from database");
			PackagingAndDeliveryCharge selectedComponentCharges = packagingAndDeliveryChargeRepo
					.getChargeForComponentWithId(selectedComponent.getComponentId());

			log.info("calculating total opackaging and delivery charge for the component with count :{}", count);
			return calculateCharge(selectedComponentCharges) * count;
		} catch (PackagingAndDeliveryChargeNotFoundException packagingAndDeliveryChargeNotFoundException) {
			log.error("packaging and delivery charge not found for the given component");
			throw new ComponentChargeDetailsNotFoundException(packagingAndDeliveryChargeNotFoundException.getMessage());
		} catch (ComponentNotFoundException componentNotFoundException) {
			log.error("component with componenttype :{} not found", componentType);
			throw new ComponentChargeDetailsNotFoundException(componentNotFoundException.getMessage());
		} catch (Exception e) {
			log.error("some other error occured while fetching data from database");
			throw new ComponentChargeDetailsNotFoundException(ERROR_FETCHING_DATA_FROM_DATABASE);
		}
	}

	private Long calculateCharge(PackagingAndDeliveryCharge selectedComponentCharges) {
		log.info("setting initial total charge to zero");
		Long totalCharge = 0L;
		log.info("adding delivery charge to the total charge");
		totalCharge += selectedComponentCharges.getDeliveryItems().getItemDeliveryPrice();
		List<PackagingItem> packedItems = selectedComponentCharges.getPackagingItems();
		log.info("adding packaging charge for eached packed items to the total charge");
		for (PackagingItem packagingItem : packedItems) {
			totalCharge += packagingItem.getPackagingPrice();
		}
		log.info("total charge for the component's packing and delivery is {} for a single unit", totalCharge);
		return totalCharge;
	}

}
