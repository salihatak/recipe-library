package com.recipelibrary.api.service;

import java.util.List;

import com.recipelibrary.api.dto.ProductDto;

public interface ProductService {
	List<ProductDto> retrieveAll();
}
