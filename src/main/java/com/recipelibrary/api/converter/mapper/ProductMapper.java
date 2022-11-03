package com.recipelibrary.api.converter.mapper;

import org.mapstruct.Mapper;

import com.recipelibrary.api.dto.ProductDto;
import com.recipelibrary.api.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

	ProductDto toProductDto(Product product);
}
