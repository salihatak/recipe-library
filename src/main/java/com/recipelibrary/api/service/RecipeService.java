package com.recipelibrary.api.service;

import com.recipelibrary.api.dto.RecipeDto;
import com.recipelibrary.api.dto.request.RecipeCreateRequest;
import com.recipelibrary.api.dto.request.RecipeUpdateRequest;

public interface RecipeService {
	RecipeDto create(RecipeCreateRequest request);
	RecipeDto update(Long recipeId, RecipeUpdateRequest request);
	void delete(Long recipeId);
	RecipeDto retrieve(Long recipeId);
}
