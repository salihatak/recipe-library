package com.recipelibrary.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.recipelibrary.api.dto.IngredientRequestDto;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeCreateRequest {

	@NotBlank
	@Size(min = 1, max = 50)
	private String title;

	@NotBlank
	@Size(min = 1, max = 500)
	private String instructions;

	@Min(value = 1)
	private Integer servings;

	private Boolean vegetarian = Boolean.FALSE;

	@NotEmpty
	@Valid
	private List<IngredientRequestDto> ingredients;
}
