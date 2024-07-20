package com.ucl.common.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UtilApiGet {
	
	public static StringBuilder apiGet(String apiUrl) throws Exception {
		//System.out.println("==============================================apiUrl:"+apiUrl);
		URL url = new URL(apiUrl);
		HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		httpURLConnection.setRequestMethod("GET");

		BufferedReader bufferedReader;
		if (httpURLConnection.getResponseCode() >= 200 && httpURLConnection.getResponseCode() <= 300) {
			bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
		} else {
			bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		String line;
		
		//System.out.println("====================================================bufferedReader.readLine():"+bufferedReader.readLine());
		while ((line = bufferedReader.readLine()) != null) {
			System.out.println("line: " + line);
			stringBuilder.append(line);
		}
		
		//System.out.println("====================================================stringBuilder:"+stringBuilder);
		
		bufferedReader.close();
		httpURLConnection.disconnect();
		
		return stringBuilder;
	}
}
