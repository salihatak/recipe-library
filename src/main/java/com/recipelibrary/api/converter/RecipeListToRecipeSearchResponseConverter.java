package com.recipelibrary.api.converter;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.recipelibrary.api.converter.mapper.RecipeMapper;
import com.recipelibrary.api.dto.RecipeSummaryDto;
import com.recipelibrary.api.dto.response.RecipeSearchResponse;
import com.recipelibrary.api.entity.Recipe;

@Component
@RequiredArgsConstructor
public class RecipeListToRecipeSearchResponseConverter implements Function<Page<Recipe>, RecipeSearchResponse> {

	private final RecipeMapper recipeMapper;

	@Override
	public RecipeSearchResponse apply(Page<Recipe> recipes) {
		List<RecipeSummaryDto> recipeList = recipes.get()
				.map(recipeMapper::toRecipeSummaryDto)
				.collect(Collectors.toList());
		return RecipeSearchResponse.builder()
				.recipes(recipeList)
				.page(buildPage(recipes))
				.build();
	}

	private com.recipelibrary.api.dto.common.Page buildPage(Page<Recipe> recipes) {
		return com.recipelibrary.api.dto.common.Page.builder()
				.pageSize(recipes.getSize())
				.totalPages(recipes.getTotalPages())
				.totalElements(recipes.getTotalElements())
				.currentPage(recipes.getNumber())
				.build();
	}
}
