package com.recipelibrary.api.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.recipelibrary.api.entity.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long>, JpaSpecificationExecutor<Recipe> {

	@EntityGraph(value = "Recipe.ingredients")
	Recipe queryById(Long id);
}
