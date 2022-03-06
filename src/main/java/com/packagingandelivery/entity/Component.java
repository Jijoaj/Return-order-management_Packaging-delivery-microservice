package com.packagingandelivery.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;

@Entity
@Getter
public class Component {

	@Id
	private int componentId;
	@Column(nullable = false, unique = true)
	private String componentType;

	public Component(int componentId, String componentType) {
		super();
		this.componentId = componentId;
		this.componentType = componentType;
	}

	public Component() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(componentId, componentType);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Component other = (Component) obj;
		return componentId == other.componentId && Objects.equals(componentType, other.componentType);
	}

}
