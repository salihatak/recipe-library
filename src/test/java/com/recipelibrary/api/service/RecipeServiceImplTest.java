package com.recipelibrary.api.service;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.recipelibrary.api.converter.mapper.IngredientMapper;
import com.recipelibrary.api.converter.mapper.RecipeMapper;
import com.recipelibrary.api.dto.IngredientRequestDto;
import com.recipelibrary.api.dto.RecipeDto;
import com.recipelibrary.api.dto.request.RecipeCreateRequest;
import com.recipelibrary.api.dto.request.RecipeUpdateRequest;
import com.recipelibrary.api.entity.Ingredient;
import com.recipelibrary.api.entity.Recipe;
import com.recipelibrary.api.entity.enums.RecipeStatus;
import com.recipelibrary.api.repository.IngredientRepository;
import com.recipelibrary.api.repository.RecipeRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecipeServiceImplTest {

	private RecipeService recipeService;

	@Mock
	private RecipeRepository recipeRepository;
	@Mock
	private IngredientRepository ingredientRepository;
	@Mock
	private RecipeMapper recipeMapper;
	@Mock
	private IngredientMapper ingredientMapper;
	@Captor
	private ArgumentCaptor<Recipe> recipeArgumentCaptor;

	@BeforeEach
	void setUp(){
		recipeService = new RecipeServiceImpl(recipeRepository,
				ingredientRepository,
				recipeMapper,
				ingredientMapper);
	}

	@Test
	void should_create_recipe(){
		//given
		RecipeCreateRequest request = RecipeCreateRequest.builder().build();
		Recipe mockRecipe = new Recipe();
		Ingredient mockIngredient = new Ingredient();
		mockRecipe.setIngredients(List.of(mockIngredient));
		Recipe mockRecipeSaved = new Recipe();

		when(recipeMapper.toRecipe(request)).thenReturn(mockRecipe);
		when(recipeRepository.save(mockRecipe)).thenReturn(mockRecipeSaved);
		when(recipeMapper.toRecipeDto(mockRecipeSaved)).thenReturn(new RecipeDto());

		//when
		RecipeDto recipeDto = recipeService.create(request);

		//then
		assertThat(recipeDto).isNotNull();

		verify(recipeMapper).toRecipe(request);
		verify(recipeRepository).save(mockRecipe);
		verify(ingredientRepository).save(mockIngredient);
		verify(recipeMapper).toRecipeDto(mockRecipeSaved);
		verifyNoMoreInteractions(recipeMapper, recipeRepository, ingredientRepository, recipeMapper);
	}

	@Test
	void should_update_recipe(){
		//given
		Long recipeId = 1L;
		IngredientRequestDto ingredientRequestDto = IngredientRequestDto.builder().build();
		int servings = 3;
		String instructions = "instructions";
		String title = "title";
		Boolean vegetarian = Boolean.TRUE;
		RecipeUpdateRequest request = RecipeUpdateRequest.builder()
				.instructions(instructions)
				.servings(servings)
				.title(title)
				.vegetarian(vegetarian)
				.ingredients(List.of(ingredientRequestDto))
				.build();
		Recipe mockRecipe = new Recipe();
		Ingredient mockIngredient = new Ingredient();
		mockRecipe.setIngredients(List.of(mockIngredient));

		when(recipeRepository.findById(recipeId)).thenReturn(Optional.of(mockRecipe));
		when(ingredientMapper.toIngredient(ingredientRequestDto)).thenReturn(mockIngredient);
		when(recipeMapper.toRecipeDto(mockRecipe)).thenReturn(new RecipeDto());

		//when
		RecipeDto recipeDto = recipeService.update(recipeId, request);

		//then
		assertThat(recipeDto).isNotNull();
		assertThat(mockRecipe.getServings()).isEqualTo(servings);
		assertThat(mockRecipe.getTitle()).isEqualTo(title);
		assertThat(mockRecipe.getInstructions()).isEqualTo(instructions);
		assertThat(mockRecipe.getVegetarian()).isEqualTo(vegetarian);

		verify(recipeRepository).findById(recipeId);
		verify(ingredientRepository).deleteAllById(any(List.class));
		verify(ingredientMapper).toIngredient(ingredientRequestDto);
		verify(ingredientRepository).save(mockIngredient);
		verify(recipeMapper).toRecipeDto(mockRecipe);
		verifyNoMoreInteractions(recipeRepository, ingredientRepository, ingredientMapper, ingredientRepository, recipeMapper);
	}

	@Test
	void should_retrieve_recipe(){
		//given
		Long recipeId = 1L;
		Recipe mockRecipe = new Recipe();
		Ingredient mockIngredient = new Ingredient();
		mockRecipe.setIngredients(List.of(mockIngredient));

		when(recipeRepository.findById(recipeId)).thenReturn(Optional.of(mockRecipe));
		when(recipeMapper.toRecipeDto(mockRecipe)).thenReturn(new RecipeDto());

		//when
		RecipeDto recipeDto = recipeService.retrieve(recipeId);

		//then
		assertThat(recipeDto).isNotNull();

		verify(recipeRepository).findById(recipeId);
		verify(recipeMapper).toRecipeDto(mockRecipe);
		verifyNoMoreInteractions(recipeRepository, recipeMapper);
	}

	@Test
	void should_delete_recipe(){
		//given
		Long recipeId = 1L;
		Recipe mockRecipe = new Recipe();
		Ingredient mockIngredient = new Ingredient();
		mockRecipe.setIngredients(List.of(mockIngredient));

		when(recipeRepository.findById(recipeId)).thenReturn(Optional.of(mockRecipe));

		//when
		recipeService.delete(recipeId);

		//then
		verify(recipeRepository).findById(recipeId);
		verify(recipeRepository).save(recipeArgumentCaptor.capture());
		Recipe captured = recipeArgumentCaptor.getValue();
		assertThat(captured).isNotNull();
		assertThat(captured.getStatus()).isEqualTo(RecipeStatus.DELETED);

		verifyNoMoreInteractions(recipeRepository, recipeRepository);
	}
}