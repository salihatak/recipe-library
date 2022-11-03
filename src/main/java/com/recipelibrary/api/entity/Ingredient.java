package com.recipelibrary.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.recipelibrary.api.entity.enums.Unit;

@Getter
@Setter
@Entity
@Table(name = "rl_ingredient")
public class Ingredient extends BaseEntity {

	@Column(name = "unit", length = 20, nullable = false)
	@Enumerated(EnumType.STRING)
	private Unit unit;

	private double amount;

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@ManyToOne
	private Recipe recipe;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Ingredient )) return false;
		return this.id != null && id.equals(((Ingredient) o).getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
