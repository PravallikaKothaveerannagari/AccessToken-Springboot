package com.myapp.restcall.controller;

import java.util.Collections;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestApiController {

	@GetMapping(value = "/gettoken")
	public static String getToken() throws Exception {
		String url = "https://login.microsoftonline.com/<Your_Tenant_ID>/token";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> inputMap = new LinkedMultiValueMap<>();

		inputMap.add("grant_type", "client_credentials");
		inputMap.add("client_id", "<Your_Client_ID>");
		inputMap.add("client_secret", "<Your_Client_Secret>");
		inputMap.add("resource", "https://management.core.windows.net/");
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(inputMap,
				headers);
		RestTemplate template = new RestTemplate();
		ResponseEntity<String> response = template.postForEntity(url, entity, String.class);
		JSONObject myjson = new JSONObject(response.getBody());
		String data = (String) myjson.get("access_token");
		System.out.println(data);
		String access_token = data;
		return access_token;

	}

	

}
