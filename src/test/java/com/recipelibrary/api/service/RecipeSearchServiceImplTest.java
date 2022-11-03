package com.recipelibrary.api.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.recipelibrary.api.converter.RecipeListToRecipeSearchResponseConverter;
import com.recipelibrary.api.dto.FilterParamsDto;
import com.recipelibrary.api.dto.response.RecipeSearchResponse;
import com.recipelibrary.api.entity.Recipe;
import com.recipelibrary.api.repository.RecipeRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecipeSearchServiceImplTest {

	private RecipeSearchService recipeSearchService;

	@Mock
	private RecipeRepository recipeRepository;

	@Mock
	private RecipeListToRecipeSearchResponseConverter recipeListToRecipeSearchResponseConverter;


	@BeforeEach
	void setUp() {
		recipeSearchService = new RecipeSearchServiceImpl(recipeRepository, recipeListToRecipeSearchResponseConverter);
	}

	@Test
	void should_search() {
		//given
		FilterParamsDto filters = FilterParamsDto.builder().build();
		Pageable pageable = Pageable.ofSize(10);
	 	Page<Recipe> recipePage = mock(Page.class);
		RecipeSearchResponse response = mock(RecipeSearchResponse.class);

		when(recipeRepository.findAll(any(Specification.class), eq(pageable))).thenReturn(recipePage);
		when(recipeListToRecipeSearchResponseConverter.apply(recipePage)).thenReturn(response);

		//when
		RecipeSearchResponse searchResponse = recipeSearchService.search(filters, pageable);

		//then
		assertThat(searchResponse).isNotNull();

		verify(recipeRepository).findAll(any(Specification.class), eq(pageable));
		verify(recipeListToRecipeSearchResponseConverter).apply(recipePage);
		verifyNoMoreInteractions(recipeRepository, recipeListToRecipeSearchResponseConverter);
	}
}