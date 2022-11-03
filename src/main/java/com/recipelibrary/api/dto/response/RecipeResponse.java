package com.recipelibrary.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.recipelibrary.api.dto.RecipeDto;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeResponse {
	private RecipeDto recipe;
}
