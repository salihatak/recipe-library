package com.recipelibrary.api.service;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.recipelibrary.api.converter.mapper.ProductMapper;
import com.recipelibrary.api.dto.ProductDto;
import com.recipelibrary.api.entity.Product;
import com.recipelibrary.api.repository.ProductRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

	private ProductService productService;

	@Mock
	private ProductRepository productRepository;

	@Mock
	private ProductMapper productMapper;

	@BeforeEach
	void setUp() {
		productService = new ProductServiceImpl(productRepository, productMapper);
	}

	@Test
	void should_retrieve_all() {
		//given
		Product mockProduct = new Product();

		when(productRepository.findAll()).thenReturn(List.of(mockProduct));
		when(productMapper.toProductDto(mockProduct)).thenReturn(ProductDto.builder().build());

		//when
		List<ProductDto> productDtos = productService.retrieveAll();

		//then
		assertThat(productDtos).isNotNull();
		verify(productRepository).findAll();
		verify(productMapper).toProductDto(mockProduct);
		verifyNoMoreInteractions(productRepository, productMapper);
	}
}