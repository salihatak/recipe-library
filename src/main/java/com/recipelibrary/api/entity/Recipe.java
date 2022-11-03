package com.recipelibrary.api.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Where;

import com.recipelibrary.api.entity.enums.RecipeStatus;

@Getter
@Setter
@Entity
@NamedEntityGraph(
		name = "Recipe.ingredients",
		attributeNodes = @NamedAttributeNode("ingredients")
)
@Table(name = "rl_recipe")
@Where(clause = "status != 'DELETED'")
public class Recipe extends BaseEntity {

	@Version
	private Long version;

	@Column(name = "title", nullable = false, length = 50)
	private String title;

	@Column(name = "instructions", nullable = false, length = 500)
	private String instructions;

	@Column(name = "servings", nullable = false)
	private Integer servings;

	@Column(name = "is_vegetarian", nullable = false)
	private Boolean vegetarian;

	@OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY)
	private List<Ingredient> ingredients;

	@Column(name = "status", length = 20, nullable = false)
	@Enumerated(EnumType.STRING)
	private RecipeStatus status = RecipeStatus.ACTIVE;
}
