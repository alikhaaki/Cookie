package com.ali.cookie.http;

import com.ali.cookie.model.data.Home;
import com.ali.cookie.model.data.Search;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface ApiServices {
	
	@GET("1881tm")
	Flowable<List<Home>> getHome();
	
	@GET("10wc6h")
	Flowable<List<Search>> getSearch();
	
}
