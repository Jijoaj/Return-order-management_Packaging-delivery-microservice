package com.packagingandelivery.repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.packagingandelivery.entity.PackagingAndDeliveryCharge;
import com.packagingandelivery.exceptions.PackagingAndDeliveryChargeNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class PackagingAndDeliveryChargeRepoImpl implements PackagingAndDeliveryChargeRepo {

	private static final String NO_PACKAGING_AND_DELIVERY_CHARGE_DETAILS_FOUND_FOR_THE_GIVEN_COMPONENT = "no  packaging and delivery charge details found for the given component";
	private static final String COMPONENT_ID = "componentID";
	@Autowired
	EntityManager entityManager;

	@Override
	public PackagingAndDeliveryCharge getChargeForComponentWithId(Integer componentID) {
		log.info("fetching packaging and delivery charge for the component with id : {}", componentID);
		try {

			TypedQuery<PackagingAndDeliveryCharge> query = entityManager.createQuery(
					"select p from PackagingAndDeliveryCharge p where p.component.id = :componentID",
					PackagingAndDeliveryCharge.class);

			return query.setParameter(COMPONENT_ID, componentID).getSingleResult();

		} catch (javax.persistence.NoResultException e) {
			log.error("no  packaging and delivery charge details found for the component with id :{}", componentID);
			throw new PackagingAndDeliveryChargeNotFoundException(
					NO_PACKAGING_AND_DELIVERY_CHARGE_DETAILS_FOUND_FOR_THE_GIVEN_COMPONENT);
		}

	}
}
