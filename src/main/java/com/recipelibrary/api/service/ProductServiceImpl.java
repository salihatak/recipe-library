package com.recipelibrary.api.service;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.recipelibrary.api.converter.mapper.ProductMapper;
import com.recipelibrary.api.dto.ProductDto;
import com.recipelibrary.api.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

	private final ProductRepository productRepository;
	private final ProductMapper productMapper;

	@Override
	public List<ProductDto> retrieveAll() {
		return productRepository.findAll().stream()
				.map(productMapper::toProductDto)
				.collect(Collectors.toList());
	}
}
