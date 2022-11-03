package com.recipelibrary.api.service;

import org.springframework.data.domain.Pageable;

import com.recipelibrary.api.dto.FilterParamsDto;
import com.recipelibrary.api.dto.response.RecipeSearchResponse;

public interface RecipeSearchService {
	RecipeSearchResponse search(FilterParamsDto filters, Pageable pageable);
}
