package com.recipelibrary.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.hibernate.internal.util.collections.CollectionHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.recipelibrary.api.converter.RecipeListToRecipeSearchResponseConverter;
import com.recipelibrary.api.dto.FilterParamsDto;
import com.recipelibrary.api.dto.response.RecipeSearchResponse;
import com.recipelibrary.api.entity.Ingredient;
import com.recipelibrary.api.entity.Recipe;
import com.recipelibrary.api.repository.RecipeRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecipeSearchServiceImpl implements RecipeSearchService {

	private final RecipeRepository recipeRepository;
	private final RecipeListToRecipeSearchResponseConverter recipeListToRecipeSearchResponseConverter;

	@Override
	public RecipeSearchResponse search(FilterParamsDto filters, Pageable pageable) {
		log.info("Search Recipe by filters: {}, pageable: {}", filters, pageable);

		Specification<Recipe> specification = buildSpecification(filters);
		Page<Recipe> recipes = recipeRepository.findAll(specification, pageable);
		return recipeListToRecipeSearchResponseConverter.apply(recipes);
	}

	public Specification<Recipe> buildSpecification(FilterParamsDto filters) {
		return (root, query, builder) -> {
			List<Predicate> predicateList = new ArrayList<>();
			if(Objects.nonNull(filters.getRecipeId())){
				predicateList.add(builder.equal(root.get("id"), filters.getRecipeId()));
			}
			if(Objects.nonNull(filters.getServings())){
				predicateList.add(builder.equal(root.get("servings"), filters.getServings()));
			}
			if(Objects.nonNull(filters.getVegetarian())){
				predicateList.add(builder.equal(root.get("vegetarian"), filters.getVegetarian()));
			}
			if(Objects.nonNull(filters.getKeyword())){
				predicateList.add(builder.like(builder.lower(root.get("instructions")), contains(filters.getKeyword())));
			}
			if(CollectionHelper.isNotEmpty(filters.getIncluding())){
				Subquery<Ingredient> ingredientSubquery = query.subquery(Ingredient.class);
				Root<Ingredient> ingredientRoot = ingredientSubquery.from(Ingredient.class);
				ingredientSubquery.select(ingredientRoot.get("recipe").get("id"));
				Predicate predicate = builder.trim(ingredientRoot.get("product").get("id")).in(filters.getIncluding());
				ingredientSubquery.where(predicate);
				predicateList.add(root.get("id").in(ingredientSubquery));
			}
			if(CollectionHelper.isNotEmpty(filters.getExcluding())){
				Subquery<Ingredient> ingredientSubquery = query.subquery(Ingredient.class);
				Root<Ingredient> ingredientRoot = ingredientSubquery.from(Ingredient.class);
				ingredientSubquery.select(ingredientRoot.get("recipe").get("id"));
				Predicate predicate = builder.trim(ingredientRoot.get("product").get("id")).in(filters.getExcluding());
				ingredientSubquery.where(predicate);
				predicateList.add(builder.not(root.get("id").in(ingredientSubquery)));
			}

			return builder.and(predicateList.toArray(Predicate[]::new));
		};
	}

	private String contains(String expression) {
		return MessageFormat.format("%{0}%", expression);
	}
}
