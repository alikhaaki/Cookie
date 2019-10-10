package com.ali.cookie.model.repo.home;

import com.ali.cookie.http.ApiServices;
import com.ali.cookie.model.data.Home;

import java.util.List;

import io.reactivex.Flowable;

public class HomeCloudDataSource implements HomeDataSource {
	
	private ApiServices apiServices;
	public HomeCloudDataSource(ApiServices apiServices) {
		this.apiServices = apiServices;
	}
	
	@Override
	public Flowable<List<Home>> getList() {
		return apiServices.getHome();
	}
	@Override
	public void bookmark(Home home) {
	
	}
	@Override
	public Flowable<List<Home>> getOfflineList() {
		return null;
	}
}
