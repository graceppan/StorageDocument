package com.document.storage.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class DocumentUtilTest {

	@Autowired
	DocumentUtil util;
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGetUuid() {
		String id = util.getUuid();
		String id2 = util.getUuid();
		assertNotNull(id);
		assertNotNull(id2);
		assertTrue(id.length() == 20);
		assertTrue( !id.equalsIgnoreCase(id2));
		
		
	}

}
