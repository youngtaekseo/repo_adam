package com.ucl.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ucl.common.util.UtilApiGet;


@Controller
public class testController {
	@RequestMapping(value = "/test")
	public String test(Model model) throws Exception {
		String apiUrl = "http://apis.data.go.kr/1471000/CovidDagnsRgntProdExprtStusService/getCovidDagnsRgntProdExprtStusInq?serviceKey=AxoMTvfWYhTeXN7w25Q%2BD6yx9%2FIUhjTRI4CH8sl0uPM2Ek2wSd75vv%2BuSPyRzk02rWDqD2ZqxGNPH67nuS20Ig%3D%3D&numOfRows=3&pageNo=1&type=json";
		StringBuilder stringBuilder = UtilApiGet.apiGet(apiUrl);
		
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode node = objectMapper.readTree(stringBuilder.toString());
		
		System.out.println("node.get(\"header\").get(\"resultCode\").asText(): " + node.get("header").get("resultCode").asText());
		System.out.println("node.get(\"header\").get(\"resultMsg\").asText(): " + node.get("header").get("resultMsg").asText());
		System.out.println("node.get(\"body\").get(\"items\").get(\"KIT_PROD_QTY\").asText(): " + node.get("body").get("items").get(0).get("KIT_PROD_QTY").asText());
		System.out.println("node.get(\"body\").get(\"items\").get(\"KIT_EXPRT_QTY\").asText(): " + node.get("body").get("items").get(0).get("KIT_EXPRT_QTY").asText());
		System.out.println("node.get(\"body\").get(\"items\").get(\"KIT_STOCK_QTY\").asText(): " + node.get("body").get("items").get(0).get("KIT_STOCK_QTY").asText());
		
		model.addAttribute("node", node);
		
		return "infra/v1/test/test";
	}
	
	@RequestMapping(value = "/test2")
	public String test2(Model model) throws Exception {
		String apiUrl = "http://apis.data.go.kr/1471000/CovidDagnsRgntProdExprtStusService/getMmCovidDagnsRgntExprtStusInq?serviceKey=AxoMTvfWYhTeXN7w25Q%2BD6yx9%2FIUhjTRI4CH8sl0uPM2Ek2wSd75vv%2BuSPyRzk02rWDqD2ZqxGNPH67nuS20Ig%3D%3D&numOfRows=3&pageNo=1&type=json";
		StringBuilder stringBuilder = UtilApiGet.apiGet(apiUrl);
		
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode node = objectMapper.readTree(stringBuilder.toString());
		
		System.out.println("node.get(\"header\").get(\"resultCode\").asText(): " + node.get("header").get("resultCode").asText());
		System.out.println("node.get(\"header\").get(\"resultMsg\").asText(): " + node.get("header").get("resultMsg").asText());
		System.out.println("node.get(\"body\").get(\"items\").get(\"KIT_EXPRT_QTY\").asText(): " + node.get("body").get("items").get(0).get("KIT_EXPRT_QTY").asText());
		
		model.addAttribute("node", node);
		
		return "infra/v1/test/test2";
	}
	
	@RequestMapping(value = "/test3")
	public String test3(Model model) throws Exception {
		String apiUrl = "http://apis.data.go.kr/1471000/CovidDagnsRgntProdExprtStusService/getCovidDagnsRgntProdExprtStusInq?serviceKey=AxoMTvfWYhTeXN7w25Q%2BD6yx9%2FIUhjTRI4CH8sl0uPM2Ek2wSd75vv%2BuSPyRzk02rWDqD2ZqxGNPH67nuS20Ig%3D%3D&numOfRows=3&pageNo=1&type=json";
		StringBuilder stringBuilder = UtilApiGet.apiGet(apiUrl);
		
		ObjectMapper objectMapper = new ObjectMapper();
      
		Map<String, Object> map = objectMapper.readValue(stringBuilder.toString(), Map.class);
      
		System.out.println("######## Map");
		for (String key : map.keySet()) {
			String value = String.valueOf(map.get(key));	// ok
			System.out.println("[key]:" + key + ", [value]:" + value);
		}
		
		Map<String, Object> header = new HashMap<String, Object>();
		header = (Map<String, Object>) map.get("header");
		
		System.out.println("######## Header");
		for (String key : header.keySet()) {
			String value = String.valueOf(header.get(key));	// ok
			System.out.println("[key]:" + key + ", [value]:" + value);
		}
		
		String aaa = (String) header.get("resultCode");
		
		System.out.println("header.get(\"resultCode\"): " + header.get("resultCode"));
		System.out.println("header.get(\"resultMsg\"): " + header.get("resultMsg"));
		
		Map<String, Object> body = new HashMap<String, Object>();
		body = (Map<String, Object>) map.get("body");
		
//		List<Home> items = new ArrayList<Home>();
//		items = (List<Home>) body.get("items");
//		
//		System.out.println("items.size(): " + items.size());
//		
//		System.out.println("items.toString(): " + items.toString());
		
//		model.addAllAttributes(map);
		model.addAllAttributes(header);
		model.addAllAttributes(body);
		
//		model.addAttribute("header", header);
//		model.addAttribute("body", body);
		
		return "infra/v1/test/test3";
	}
	
	@RequestMapping(value = "/test4")
	public String test4(Model model) throws Exception {
		String apiUrl = "http://apis.data.go.kr/1471000/CovidDagnsRgntProdExprtStusService/getMmCovidDagnsRgntExprtStusInq?serviceKey=AxoMTvfWYhTeXN7w25Q%2BD6yx9%2FIUhjTRI4CH8sl0uPM2Ek2wSd75vv%2BuSPyRzk02rWDqD2ZqxGNPH67nuS20Ig%3D%3D&numOfRows=3&pageNo=1&type=json";
		StringBuilder stringBuilder = UtilApiGet.apiGet(apiUrl);
		
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> map = objectMapper.readValue(stringBuilder.toString(), Map.class);
      
		System.out.println("######## Map");
		for (String key : map.keySet()) {
			String value = String.valueOf(map.get(key));	// ok
			System.out.println("[key]:" + key + ", [value]:" + value);
		}
		
		Map<String, Object> header = new HashMap<String, Object>();
		header = (Map<String, Object>) map.get("header");
		
		System.out.println("######## Header");
		for (String key : header.keySet()) {
			String value = String.valueOf(header.get(key));	// ok
			System.out.println("[key]:" + key + ", [value]:" + value);
		}
		
		String aaa = (String) header.get("resultCode");
		
		System.out.println("header.get(\"resultCode\"): " + header.get("resultCode"));
		System.out.println("header.get(\"resultMsg\"): " + header.get("resultMsg"));
		
		Map<String, Object> body = new HashMap<String, Object>();
		body = (Map<String, Object>) map.get("body");
		
//		List<Home> items = new ArrayList<Home>();
//		items = (List<Home>) body.get("items");
//		
//		System.out.println("items.size(): " + items.size());
//		
//		System.out.println("items.toString(): " + items.toString());
		
//		model.addAllAttributes(map);
		model.addAllAttributes(header);
		model.addAllAttributes(body);
		
//		model.addAttribute("header", header);
//		model.addAttribute("body", body);
		
		return "infra/v1/test/test4";
	}
	
}
