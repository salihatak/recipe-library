package com.recipelibrary.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ActiveProfiles(profiles = {"test"})
@SpringBootTest
class RecipeLibraryApplicationTests {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	void startsUpSuccessfully() {
		assertTrue(applicationContext.getStartupDate() > 0);
	}
}
