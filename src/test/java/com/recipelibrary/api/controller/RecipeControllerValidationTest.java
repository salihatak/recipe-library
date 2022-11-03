package com.recipelibrary.api.controller;

import java.util.List;

import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipelibrary.api.dto.request.RecipeCreateRequest;
import com.recipelibrary.api.service.RecipeSearchService;
import com.recipelibrary.api.service.RecipeService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@WebMvcTest(RecipeController.class)
@AutoConfigureMockMvc(addFilters = false)
class RecipeControllerValidationTest {

	private static final String BASE_PATH = "/recipe-library/v1/recipes";
	private static final String RESOURCE_PATH = "/recipe-library/v1/recipes/1";

	@MockBean
	private RecipeService recipeService;

	@MockBean
	private RecipeSearchService recipeSearchService;

	@Autowired
	private RecipeController recipeController;

	@Autowired
	private MockMvc mockMvc;

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Test
	void should_return_bad_request_error_response_while_creating_recipe_if_request_null() throws Exception {
		//given

		//when
		ResultActions resultActions = mockMvc.perform(post(BASE_PATH));

		//then
		resultActions
				.andExpect(status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message", Is.is("Required request body is missing.")));
	}


	@Test
	void should_return_bad_request_error_response_while_creating_recipe_if_request_attributes_null() throws Exception {
		//given
		RecipeCreateRequest request = RecipeCreateRequest.builder().build();

		//when
		ResultActions resultActions = mockMvc.perform(post(BASE_PATH)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)));

		//then
		resultActions
				.andExpect(status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.startsWith("Validation failed")));
	}

	@Test
	void should_return_bad_request_error_response_while_creating_recipe_if_ingredients_null() throws Exception {
		//given
		RecipeCreateRequest request = RecipeCreateRequest.builder()
				.title("title")
				.instructions("instructions")
				.servings(1)
				.vegetarian(Boolean.TRUE).build();

		//when
		ResultActions resultActions = mockMvc.perform(post(BASE_PATH, request)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)));

		//then
		resultActions
				.andExpect(status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.startsWith("Validation failed")));
	}

	@Test
	void should_return_bad_request_error_response_while_creating_recipe_if_ingredients_empty() throws Exception {
		//given
		RecipeCreateRequest request = RecipeCreateRequest.builder()
				.title("title")
				.instructions("instructions")
				.servings(1)
				.ingredients(List.of())
				.vegetarian(Boolean.TRUE).build();


		//when
		ResultActions resultActions = mockMvc.perform(post(BASE_PATH, request)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)));

		//then
		resultActions
				.andExpect(status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.startsWith("Validation failed")));
	}


	@Test
	void should_return_bad_request_error_response_while_updating_recipe_if_request_null() throws Exception {
		//given

		//when
		ResultActions resultActions = mockMvc.perform(put(RESOURCE_PATH));

		//then
		resultActions
				.andExpect(status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message", Is.is("Required request body is missing.")));
	}


	@Test
	void should_return_bad_request_error_response_while_updating_recipe_if_request_attributes_null() throws Exception {
		//given
		RecipeCreateRequest request = RecipeCreateRequest.builder().build();

		//when
		ResultActions resultActions = mockMvc.perform(put(RESOURCE_PATH)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)));

		//then
		resultActions
				.andExpect(status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.startsWith("Validation failed")));
	}

	@Test
	void should_return_bad_request_error_response_while_updating_recipe_if_ingredients_null() throws Exception {
		//given
		RecipeCreateRequest request = RecipeCreateRequest.builder()
				.title("title")
				.instructions("instructions")
				.servings(1)
				.vegetarian(Boolean.TRUE).build();

		//when
		ResultActions resultActions = mockMvc.perform(put(RESOURCE_PATH, request)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)));

		//then
		resultActions
				.andExpect(status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.startsWith("Validation failed")));
	}

	@Test
	void should_return_bad_request_error_response_while_updating_recipe_if_ingredients_empty() throws Exception {
		//given
		RecipeCreateRequest request = RecipeCreateRequest.builder()
				.title("title")
				.instructions("instructions")
				.servings(1)
				.ingredients(List.of())
				.vegetarian(Boolean.TRUE).build();


		//when
		ResultActions resultActions = mockMvc.perform(put(RESOURCE_PATH, request)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)));

		//then
		resultActions
				.andExpect(status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.startsWith("Validation failed")));
	}

}