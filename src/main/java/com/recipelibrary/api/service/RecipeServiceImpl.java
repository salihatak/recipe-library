package com.recipelibrary.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.recipelibrary.api.converter.mapper.IngredientMapper;
import com.recipelibrary.api.converter.mapper.RecipeMapper;
import com.recipelibrary.api.dto.RecipeDto;
import com.recipelibrary.api.dto.request.RecipeCreateRequest;
import com.recipelibrary.api.dto.request.RecipeUpdateRequest;
import com.recipelibrary.api.entity.BaseEntity;
import com.recipelibrary.api.entity.Ingredient;
import com.recipelibrary.api.entity.Recipe;
import com.recipelibrary.api.entity.enums.RecipeStatus;
import com.recipelibrary.api.exception.ResourceNotFoundException;
import com.recipelibrary.api.repository.IngredientRepository;
import com.recipelibrary.api.repository.RecipeRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

	private final static String RECIPE_NOT_FOUND_MESSAGE = "Recipe not found for id: {}";

	private final RecipeRepository recipeRepository;
	private final IngredientRepository ingredientRepository;
	private final RecipeMapper recipeMapper;
	private final IngredientMapper ingredientMapper;

	@Transactional
	@Override
	public RecipeDto create(RecipeCreateRequest request) {
		Recipe recipe = recipeMapper.toRecipe(request);
		Recipe saved = recipeRepository.save(recipe);
		recipe.getIngredients().forEach(ingredient -> {
			ingredient.setRecipe(saved);
			ingredientRepository.save(ingredient);
		});
		return recipeMapper.toRecipeDto(saved);
	}

	@Transactional
	@Override
	public RecipeDto update(Long recipeId, RecipeUpdateRequest request) {
		Recipe recipe = recipeRepository.findById(recipeId)
				.orElseThrow(() -> new ResourceNotFoundException(RECIPE_NOT_FOUND_MESSAGE, recipeId));

		recipe.setInstructions(request.getInstructions());
		recipe.setServings(request.getServings());
		recipe.setTitle(request.getTitle());
		recipe.setVegetarian(request.getVegetarian());

		if(!CollectionUtils.isEmpty(recipe.getIngredients())){
			ingredientRepository.deleteAllById(recipe.getIngredients().stream().map(BaseEntity::getId).collect(Collectors.toList()));
		}
		if(!CollectionUtils.isEmpty(request.getIngredients())) {
			List<Ingredient> ingredientList = request.getIngredients().stream()
					.map(ingredientMapper::toIngredient).toList();

			ingredientList
					.forEach(ingredient -> {
						ingredient.setRecipe(recipe);
						ingredientRepository.save(ingredient);
					});
			recipe.setIngredients(ingredientList);
		}

		return recipeMapper.toRecipeDto(recipe);
	}

	@Override
	public void delete(Long recipeId) {
		Recipe recipe = recipeRepository.findById(recipeId)
				.orElseThrow(() -> new ResourceNotFoundException(RECIPE_NOT_FOUND_MESSAGE, recipeId));
		recipe.setStatus(RecipeStatus.DELETED);
		recipeRepository.save(recipe);
	}

	@Transactional
	@Override
	public RecipeDto retrieve(Long recipeId) {
		return recipeRepository.findById(recipeId)
				.map(recipeMapper::toRecipeDto)
				.orElseThrow(() -> new ResourceNotFoundException(RECIPE_NOT_FOUND_MESSAGE, recipeId));
	}
}
