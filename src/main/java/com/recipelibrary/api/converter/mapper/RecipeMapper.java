package com.recipelibrary.api.converter.mapper;

import org.mapstruct.Mapper;

import com.recipelibrary.api.dto.RecipeDto;
import com.recipelibrary.api.dto.RecipeSummaryDto;
import com.recipelibrary.api.dto.request.RecipeCreateRequest;
import com.recipelibrary.api.dto.request.RecipeUpdateRequest;
import com.recipelibrary.api.entity.Recipe;

@Mapper(componentModel = "spring", uses = {IngredientMapper.class})
public interface RecipeMapper {

	Recipe toRecipe(RecipeCreateRequest request);

	Recipe toRecipe(RecipeUpdateRequest request);

	RecipeDto toRecipeDto(Recipe recipe);

	RecipeSummaryDto toRecipeSummaryDto(Recipe recipe);
}
