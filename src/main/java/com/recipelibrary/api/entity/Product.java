package com.recipelibrary.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "rl_product")
public class Product extends BaseEntity {

	@Column(name = "name", nullable = false, length = 50)
	private String name;
}
