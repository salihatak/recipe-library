package com.recipelibrary.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.recipelibrary.api.entity.enums.Unit;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDto {
	private Unit unit;
	private double amount;
	private ProductDto product;
}
