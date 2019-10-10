package com.ali.cookie.http;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceProvider {
	
	private static ApiServices apiServices;
	private static final String BASE_URL = "https://api.myjson.com/bins/";
	
	public static ApiServices getApiServices() {
		if (apiServices == null) {
			Retrofit retrofit = new Retrofit.Builder()
				                    .baseUrl(BASE_URL)
				                    //Gson Converter For our model--Home
				                    .addConverterFactory(GsonConverterFactory.create())
				                    //Rxjava Converter For call api
				                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				                    .build();
			
			apiServices = retrofit.create(ApiServices.class);
			
		}
		return apiServices;
	}
}
