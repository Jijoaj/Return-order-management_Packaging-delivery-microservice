package com.packagingandelivery.repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.packagingandelivery.entity.Component;
import com.packagingandelivery.exceptions.ComponentNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class ComponentRepositoryImpl implements ComponentRepository {
	private static final String COMPONENT_TYPE = "componentType";
	@Autowired
	EntityManager entityManager;

	@Override
	public Component findByComponentType(String componentType) {
		try {

			log.info("fetching the component details for the component type : {}", componentType);
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Component> criteriaQuery = criteriaBuilder.createQuery(Component.class);
			Root<Component> componentRoot = criteriaQuery.from(Component.class);
			criteriaQuery.where(criteriaBuilder.equal(componentRoot.get(COMPONENT_TYPE), componentType.toUpperCase()));
			TypedQuery<Component> query = entityManager.createQuery(criteriaQuery.select(componentRoot));
			return query.getSingleResult();

		} catch (javax.persistence.NoResultException e) {
			log.error("component with component type {} doesnt exist", componentType);
			throw new ComponentNotFoundException("component with component type : " + componentType + " not found");
		}

	}

}
