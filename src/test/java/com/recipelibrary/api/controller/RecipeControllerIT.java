package com.recipelibrary.api.controller;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.recipelibrary.api.dto.IngredientDto;
import com.recipelibrary.api.dto.IngredientRequestDto;
import com.recipelibrary.api.dto.RecipeDto;
import com.recipelibrary.api.dto.RecipeSummaryDto;
import com.recipelibrary.api.dto.request.RecipeCreateRequest;
import com.recipelibrary.api.dto.request.RecipeUpdateRequest;
import com.recipelibrary.api.dto.response.RecipeResponse;
import com.recipelibrary.api.dto.response.RecipeSearchResponse;
import com.recipelibrary.api.entity.Ingredient;
import com.recipelibrary.api.entity.Recipe;
import com.recipelibrary.api.entity.enums.Unit;
import com.recipelibrary.api.repository.IngredientRepository;
import com.recipelibrary.api.repository.RecipeRepository;

import static org.assertj.core.api.Assertions.assertThat;

class RecipeControllerIT extends BaseIT {

	private static final String BASE_PATH = "/recipe-library/v1/recipes";

	@Autowired
	private RecipeRepository recipeRepository;

	@Autowired
	private IngredientRepository ingredientRepository;

	@Test
	void should_create_recipe() {
		//given
		String title = "title";
		String instructions = "instructions";
		double amount = 1d;

		IngredientRequestDto ingredientDto = IngredientRequestDto.builder()
				.productId(1L)
				.unit(Unit.PACK)
				.amount(amount).build();
		RecipeCreateRequest request = RecipeCreateRequest.builder()
				.title(title)
				.instructions(instructions)
				.servings(4)
				.vegetarian(false)
				.ingredients(List.of(ingredientDto))
				.build();

		//when
		String baseUrl = "http://localhost:" + port + BASE_PATH;
		ResponseEntity<RecipeResponse> responseEntity = restTemplate.postForEntity(baseUrl, request, RecipeResponse.class);

		//then
		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		RecipeResponse recipeResponse = responseEntity.getBody();
		assertThat(recipeResponse).isNotNull();
		RecipeDto recipeDto = recipeResponse.getRecipe();
		assertThat(recipeDto).isNotNull();
		assertThat(recipeDto.getId()).isNotNull();
		assertThat(recipeDto.getDateCreated()).isNotNull();
		assertThat(recipeDto.getDateModified()).isNotNull();
		assertThat(recipeDto.getInstructions()).isEqualTo(instructions);
		assertThat(recipeDto.getTitle()).isEqualTo(title);
		assertThat(recipeDto.getVegetarian()).isFalse();
		assertThat(recipeDto.getIngredients()).isNotNull().hasSize(1);
		IngredientDto ingredient = recipeDto.getIngredients().get(0);
		assertThat(ingredient.getUnit()).isEqualTo(Unit.PACK);
		assertThat(ingredient.getAmount()).isEqualTo(amount);
		assertThat(ingredient.getProduct()).isNotNull();
		assertThat(ingredient.getProduct().getId()).isNotNull();

		Recipe recipeDB = recipeRepository.findById(recipeDto.getId()).get();
		assertThat(recipeDB).isNotNull();
	}

	@Test
	void should_update_recipe() {
		//given
		String title = "title-1";
		String instructions = "instructions-1";
		double amount = 5d;

		RecipeUpdateRequest request = buildUpdateRequest(amount, title, instructions);

		//when
		String baseUrl = "http://localhost:" + port + BASE_PATH + "/100";
		HttpEntity<RecipeUpdateRequest> requestEntity = new HttpEntity<>(request);
		ResponseEntity<RecipeResponse> responseEntity = restTemplate.exchange(baseUrl, HttpMethod.PUT, requestEntity, RecipeResponse.class);

		//then
		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		RecipeResponse recipeResponse = responseEntity.getBody();
		assertThat(recipeResponse).isNotNull();
		RecipeDto recipeDto = recipeResponse.getRecipe();
		assertThat(recipeDto).isNotNull();
		assertThat(recipeDto.getId()).isNotNull();
		assertThat(recipeDto.getDateCreated()).isNotNull();
		assertThat(recipeDto.getDateModified()).isNotNull();
		assertThat(recipeDto.getInstructions()).isEqualTo(instructions);
		assertThat(recipeDto.getTitle()).isEqualTo(title);
		assertThat(recipeDto.getVegetarian()).isTrue();
		assertThat(recipeDto.getIngredients()).isNotNull().hasSize(2);
		IngredientDto ingredient = recipeDto.getIngredients().get(0);
		assertThat(ingredient.getUnit()).isEqualTo(Unit.GRAMS);
		assertThat(ingredient.getAmount()).isEqualTo(amount);
		assertThat(ingredient.getProduct()).isNotNull();
		assertThat(ingredient.getProduct().getId()).isNotNull();

		IngredientDto ingredient1 = recipeDto.getIngredients().get(1);
		assertThat(ingredient1.getUnit()).isEqualTo(Unit.LITER);
		assertThat(ingredient1.getAmount()).isEqualTo(amount);
		assertThat(ingredient1.getProduct()).isNotNull();
		assertThat(ingredient1.getProduct().getId()).isNotNull();

		Recipe recipeDB = recipeRepository.findById(recipeDto.getId()).get();
		assertThat(recipeDB).isNotNull();

		List<Ingredient> allByRecipeId = ingredientRepository.findAllByRecipeId(recipeDto.getId());
		assertThat(allByRecipeId).isNotEmpty().hasSize(2);
	}

	@Test
	void should_update_recipe_return_not_found(){
		//given
		String title = "title-1";
		String instructions = "instructions-1";
		double amount = 5d;

		RecipeUpdateRequest request = buildUpdateRequest(amount, title, instructions);

		//when
		String baseUrl = "http://localhost:" + port + BASE_PATH + "/1000";
		HttpEntity<RecipeUpdateRequest> requestEntity = new HttpEntity<>(request);
		ResponseEntity<RecipeResponse> responseEntity = restTemplate.exchange(baseUrl, HttpMethod.PUT, requestEntity, RecipeResponse.class);

		//then
		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

	private RecipeUpdateRequest buildUpdateRequest(double amount, String title, String instructions) {
		IngredientRequestDto ingredientDto = IngredientRequestDto.builder()
				.productId(1L)
				.unit(Unit.GRAMS)
				.amount(amount).build();
		IngredientRequestDto ingredientDto1 = IngredientRequestDto.builder()
				.productId(1L)
				.unit(Unit.LITER)
				.amount(amount).build();
		RecipeUpdateRequest request = RecipeUpdateRequest.builder()
				.title(title)
				.instructions(instructions)
				.servings(5)
				.vegetarian(true)
				.ingredients(List.of(ingredientDto, ingredientDto1))
				.build();
		return request;
	}

	@Test
	void should_retrieve_recipe(){
		//given
		Long recipeId = 101L;

		//when
		String baseUrl = "http://localhost:" + port + BASE_PATH + "/" + recipeId;
		ResponseEntity<RecipeResponse> responseEntity = restTemplate.getForEntity(baseUrl, RecipeResponse.class);

		//then
		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		RecipeResponse recipeResponse = responseEntity.getBody();
		assertThat(recipeResponse).isNotNull();
		RecipeDto recipeDto = recipeResponse.getRecipe();
		assertThat(recipeDto).isNotNull();
		assertThat(recipeDto.getId()).isEqualTo(recipeId);
		assertThat(recipeDto.getDateCreated()).isNotNull();
		assertThat(recipeDto.getDateModified()).isNotNull();
		assertThat(recipeDto.getServings()).isEqualTo(2);
		assertThat(recipeDto.getInstructions()).isEqualTo("instructions");
		assertThat(recipeDto.getTitle()).isEqualTo("title");
		assertThat(recipeDto.getVegetarian()).isFalse();
		assertThat(recipeDto.getIngredients()).isNotNull().hasSize(1);
		IngredientDto ingredient = recipeDto.getIngredients().get(0);
		assertThat(ingredient.getUnit()).isEqualTo(Unit.LITER);
		assertThat(ingredient.getAmount()).isEqualTo(1L);
		assertThat(ingredient.getProduct()).isNotNull();
		assertThat(ingredient.getProduct().getId()).isNotNull();
	}

	@Test
	void should_retrieve_recipe_return_not_found() {
		//given
		Long recipeId = 1001L;

		//when
		String baseUrl = "http://localhost:" + port + BASE_PATH + "/" + recipeId;
		ResponseEntity<RecipeResponse> responseEntity = restTemplate.getForEntity(baseUrl, RecipeResponse.class);

		//then
		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

	@Test
	void should_delete_recipe(){
		//given
		Long recipeId = 102L;

		//when
		String baseUrl = "http://localhost:" + port + BASE_PATH + "/" + recipeId;
		ResponseEntity<RecipeResponse> responseEntity = restTemplate.exchange(baseUrl, HttpMethod.DELETE, new HttpEntity<>(null), RecipeResponse.class);

		//then
		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

		Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);
		assertThat(optionalRecipe.isPresent()).isFalse();
	}

	@Test
	void should_delete_recipe_return_not_found() {
		//given
		Long recipeId = 1002L;

		//when
		String baseUrl = "http://localhost:" + port + BASE_PATH + "/" + recipeId;
		ResponseEntity<RecipeResponse> responseEntity = restTemplate.exchange(baseUrl, HttpMethod.DELETE, new HttpEntity<>(null), RecipeResponse.class);

		//then
		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

	@Test
	void should_search_recipe_by_recipe_id(){
		//given
		Long recipeId = 101L;

		//when
		String baseUrl = "http://localhost:" + port + BASE_PATH + "?recipeId=" + recipeId;
		ResponseEntity<RecipeSearchResponse> responseEntity = restTemplate.getForEntity(baseUrl, RecipeSearchResponse.class);

		//then
		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		RecipeSearchResponse searchResponse = responseEntity.getBody();
		assertThat(searchResponse).isNotNull();
		assertThat(searchResponse.getRecipes()).isNotNull().hasSize(1);
		RecipeSummaryDto recipeSummaryDto = searchResponse.getRecipes().get(0);
		assertThat(recipeSummaryDto.getId()).isEqualTo(recipeId);
		assertThat(recipeSummaryDto.getDateCreated()).isNotNull();
		assertThat(recipeSummaryDto.getDateModified()).isNotNull();
		assertThat(recipeSummaryDto.getServings()).isEqualTo(2);
		assertThat(recipeSummaryDto.getInstructions()).isEqualTo("instructions");
		assertThat(recipeSummaryDto.getTitle()).isEqualTo("title");
		assertThat(recipeSummaryDto.getVegetarian()).isFalse();
	}

	@Test
	void should_search_recipe_by_recipe_is_vegetarian(){
		//given

		//when
		String baseUrl = "http://localhost:" + port + BASE_PATH + "?vegetarian=true";
		ResponseEntity<RecipeSearchResponse> responseEntity = restTemplate.getForEntity(baseUrl, RecipeSearchResponse.class);

		//then
		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		RecipeSearchResponse searchResponse = responseEntity.getBody();
		assertThat(searchResponse).isNotNull();
		assertThat(searchResponse.getRecipes()).isNotNull().hasSize(1);
		RecipeSummaryDto recipeSummaryDto = searchResponse.getRecipes().get(0);
		assertThat(recipeSummaryDto.getId()).isEqualTo(103L);
		assertThat(recipeSummaryDto.getDateCreated()).isNotNull();
		assertThat(recipeSummaryDto.getDateModified()).isNotNull();
		assertThat(recipeSummaryDto.getServings()).isEqualTo(3);
		assertThat(recipeSummaryDto.getInstructions()).isEqualTo("instructions-3");
		assertThat(recipeSummaryDto.getTitle()).isEqualTo("title-3");
		assertThat(recipeSummaryDto.getVegetarian()).isTrue();
	}

	@Test
	void should_search_recipe_by_recipe_introduction_contains(){
		//given

		//when
		String baseUrl = "http://localhost:" + port + BASE_PATH + "?keyword=oven";
		ResponseEntity<RecipeSearchResponse> responseEntity = restTemplate.getForEntity(baseUrl, RecipeSearchResponse.class);

		//then
		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		RecipeSearchResponse searchResponse = responseEntity.getBody();
		assertThat(searchResponse).isNotNull();
		assertThat(searchResponse.getRecipes()).isNotNull().hasSize(2);
		RecipeSummaryDto recipeSummaryDto = searchResponse.getRecipes().get(0);
		assertThat(recipeSummaryDto.getId()).isEqualTo(104L);
		assertThat(recipeSummaryDto.getDateCreated()).isNotNull();
		assertThat(recipeSummaryDto.getDateModified()).isNotNull();
		assertThat(recipeSummaryDto.getServings()).isEqualTo(3);
		assertThat(recipeSummaryDto.getInstructions()).isEqualTo("instructions-4 Oven cooked");
		assertThat(recipeSummaryDto.getTitle()).isEqualTo("title-4");
		assertThat(recipeSummaryDto.getVegetarian()).isFalse();
	}

	@Test
	void should_search_recipe_by_recipe_product_including_and_excluding(){
		//given

		//when
		String baseUrl = "http://localhost:" + port + BASE_PATH + "?including=3&excluding=4";
		ResponseEntity<RecipeSearchResponse> responseEntity = restTemplate.getForEntity(baseUrl, RecipeSearchResponse.class);

		//then
		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		RecipeSearchResponse searchResponse = responseEntity.getBody();
		assertThat(searchResponse).isNotNull();
		assertThat(searchResponse.getRecipes()).isNotNull().hasSize(1);
		RecipeSummaryDto recipeSummaryDto = searchResponse.getRecipes().get(0);
		assertThat(recipeSummaryDto.getId()).isEqualTo(105L);
		assertThat(recipeSummaryDto.getDateCreated()).isNotNull();
		assertThat(recipeSummaryDto.getDateModified()).isNotNull();
		assertThat(recipeSummaryDto.getServings()).isEqualTo(4);
		assertThat(recipeSummaryDto.getInstructions()).isEqualTo("instructions");
		assertThat(recipeSummaryDto.getTitle()).isEqualTo("title");
		assertThat(recipeSummaryDto.getVegetarian()).isFalse();
	}

	@Test
	void should_search_recipe_by_serving_four_persons_and_have_potatoes(){
		//given

		//when
		String baseUrl = "http://localhost:" + port + BASE_PATH + "?servings=4&including=3";
		ResponseEntity<RecipeSearchResponse> responseEntity = restTemplate.getForEntity(baseUrl, RecipeSearchResponse.class);

		//then
		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		RecipeSearchResponse searchResponse = responseEntity.getBody();
		assertThat(searchResponse).isNotNull();
		assertThat(searchResponse.getRecipes()).isNotNull().hasSize(2);
		RecipeSummaryDto recipeSummaryDto = searchResponse.getRecipes().get(0);
		assertThat(recipeSummaryDto.getId()).isEqualTo(105L);
		recipeSummaryDto = searchResponse.getRecipes().get(1);
		assertThat(recipeSummaryDto.getId()).isEqualTo(106L);
	}

	@Test
	void should_search_recipe_by_contains_keyword_oven_and_without_salmon(){
		//given

		//when
		String baseUrl = "http://localhost:" + port + BASE_PATH + "?keyword=oven&excluding=4";
		ResponseEntity<RecipeSearchResponse> responseEntity = restTemplate.getForEntity(baseUrl, RecipeSearchResponse.class);

		//then
		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		RecipeSearchResponse searchResponse = responseEntity.getBody();
		assertThat(searchResponse).isNotNull();
		assertThat(searchResponse.getRecipes()).isNotNull().hasSize(1);
		RecipeSummaryDto recipeSummaryDto = searchResponse.getRecipes().get(0);
		assertThat(recipeSummaryDto.getId()).isEqualTo(104L);
	}
}