package com.recipelibrary.api.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Page {
	private Integer totalPages;
	private Integer currentPage;
	private Integer pageSize;
	private Long totalElements;
}
