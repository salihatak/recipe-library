package com.recipelibrary.api.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Unit {

	SPOON("Spoon"),
	PACK("Pack"),
	TEASPOON("Teaspoon"),
	PEACE("Peace"),
	PINCH("Pinch"),
	GRAMS("Grams"),
	POUND("Pound"),
	LITER("Liter");

	private final String name;
}
