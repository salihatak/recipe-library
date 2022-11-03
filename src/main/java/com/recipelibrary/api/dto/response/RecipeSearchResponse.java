package com.recipelibrary.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.recipelibrary.api.dto.RecipeDto;
import com.recipelibrary.api.dto.RecipeSummaryDto;
import com.recipelibrary.api.dto.common.Page;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeSearchResponse {
	private List<RecipeSummaryDto> recipes;
	private Page page;
}
