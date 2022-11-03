package com.recipelibrary.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.recipelibrary.api.entity.enums.Unit;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IngredientRequestDto {

	@NotNull
	private Unit unit;

	@NotNull
	@Positive
	private double amount;

	@NotNull
	@Positive
	private Long productId;
}
