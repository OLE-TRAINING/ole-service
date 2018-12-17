package com.ole.rentalstore.httpclient.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class UrlEncoderTest {

	@Test
	public void testGetEncodedUrlSpace() {
		assertThat(UrlEncoder.getEncodedUrl(" ")).isEqualTo("+");
	}
	
	@Test
	public void testGetEncodedUrlQuestionMark() {
		assertThat(UrlEncoder.getEncodedUrl("?")).isEqualTo("%3F");
	}
}