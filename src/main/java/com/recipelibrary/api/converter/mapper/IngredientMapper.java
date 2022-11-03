package com.recipelibrary.api.converter.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.recipelibrary.api.dto.IngredientDto;
import com.recipelibrary.api.dto.IngredientRequestDto;
import com.recipelibrary.api.entity.Ingredient;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface IngredientMapper {

	IngredientDto toIngredientDto(Ingredient ingredient);

	@Mapping(target="product.id", source="productId")
	Ingredient toIngredient(IngredientRequestDto ingredient);

}
