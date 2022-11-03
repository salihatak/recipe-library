package com.recipelibrary.api.util;

import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;

@UtilityClass
public class ParameterParser {

	public Sort parseSort(List<String> sort) {
		if (sort == null) {
			return Sort.unsorted();
		}
		if (sort.size() == 2 && !sort.get(0).contains(",")) {
			return Sort.by(Sort.Direction.fromString(sort.get(1)), sort.get(0));
		}
		List<Sort.Order> orders = sort.stream()
			.map(sortString -> {
				String[] items = sortString.split(",");
				return new Sort.Order(Sort.Direction.fromString(items[1]), items[0]);
			})
			.collect(Collectors.toList());
		return Sort.by(orders);
	}
}
