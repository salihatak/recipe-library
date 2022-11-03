package com.recipelibrary.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recipelibrary.api.entity.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

	List<Ingredient> findAllByRecipeId(Long recipeId);
}
