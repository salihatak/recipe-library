package com.recipelibrary.api.dto;

import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class RecipeSummaryDto {
	private Long id;
	private Instant dateCreated;
	private Instant dateModified;
	private String title;
	private String instructions;
	private Integer servings;
	private Boolean vegetarian;
}
