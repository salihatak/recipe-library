package com.recipelibrary.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FilterParamsDto {
	private Long recipeId;
	private Integer servings;
	private Boolean vegetarian;
	private List<Long> including;
	private List<Long> excluding;
	private String keyword;
}
