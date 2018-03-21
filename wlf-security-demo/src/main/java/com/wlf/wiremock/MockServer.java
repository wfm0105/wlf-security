package com.wlf.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.removeAllMappings;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

public class MockServer {

	public static void main(String[] args) throws Exception{
		configureFor(8088);
		removeAllMappings();
	
		//stubFor(get(urlPathEqualTo("/order")).willReturn(aResponse().withBody("{\"id\":1}").withStatus(200)));
		
		mock("/order/1", "01.txt");
		mock("/order/2", "02.txt");
	}
	
	private static void mock(String url, String file) throws IOException {
		ClassPathResource resource = new ClassPathResource("mock/response/"+file);
		String content = FileUtils.readFileToString(resource.getFile(), "UTF-8");
		stubFor(get(urlPathEqualTo(url)).willReturn(aResponse().withBody(content).withStatus(200)));
	}
	
}
