package com.recipelibrary.api.controller;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recipelibrary.api.dto.ProductDto;
import com.recipelibrary.api.service.ProductService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/recipe-library/v1/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

	private final ProductService productService;

	@GetMapping
	public List<ProductDto> retrieveAll() {
		return productService.retrieveAll();
	}
}
