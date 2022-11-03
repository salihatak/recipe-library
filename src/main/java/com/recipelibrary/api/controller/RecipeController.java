package com.recipelibrary.api.controller;

import lombok.RequiredArgsConstructor;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.recipelibrary.api.dto.FilterParamsDto;
import com.recipelibrary.api.dto.RecipeDto;
import com.recipelibrary.api.dto.request.RecipeCreateRequest;
import com.recipelibrary.api.dto.request.RecipeUpdateRequest;
import com.recipelibrary.api.dto.response.RecipeResponse;
import com.recipelibrary.api.dto.response.RecipeSearchResponse;
import com.recipelibrary.api.service.RecipeSearchService;
import com.recipelibrary.api.service.RecipeService;
import com.recipelibrary.api.util.ParameterParser;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/recipe-library/v1/recipes", produces = MediaType.APPLICATION_JSON_VALUE)
public class RecipeController {

	private final RecipeService recipeService;
	private final RecipeSearchService recipeSearchService;

	@GetMapping
	public RecipeSearchResponse search(FilterParamsDto filters,
										  @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
										  @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
										  @RequestParam(value = "sort", required = false, defaultValue = "dateModified,desc") List<String> sort) {
		Pageable pageable = PageRequest.of(page, size, ParameterParser.parseSort(sort));
		return recipeSearchService.search(filters, pageable);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RecipeResponse create(@Valid @RequestBody RecipeCreateRequest request) {
		RecipeDto recipe = recipeService.create(request);
		return RecipeResponse.builder().recipe(recipe).build();
	}

	@GetMapping("/{recipeId}")
	public RecipeResponse retrieve(@PathVariable(value = "recipeId") Long recipeId) {
		RecipeDto recipe = recipeService.retrieve(recipeId);
		return RecipeResponse.builder().recipe(recipe).build();
	}

	@PutMapping("/{recipeId}")
	public RecipeResponse update(@PathVariable(value = "recipeId") Long recipeId,
								 @Valid @RequestBody RecipeUpdateRequest request) {
		RecipeDto recipe = recipeService.update(recipeId, request);
		return RecipeResponse.builder().recipe(recipe).build();
	}

	@DeleteMapping ("/{recipeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(value = "recipeId") Long recipeId) {
		recipeService.delete(recipeId);
	}
}
