package com.ali.cookie.model.repo.search;

import com.ali.cookie.http.ApiServices;
import com.ali.cookie.model.data.Search;

import java.util.List;

import io.reactivex.Flowable;

public class SearchCloudDataSource implements SearchDataSource {
	private ApiServices apiServices;
	public SearchCloudDataSource(ApiServices apiServices) {
		this.apiServices = apiServices;
	}
	
	@Override
	public Flowable<List<Search>> getList() {
		return apiServices.getSearch();
	}
	@Override
	public void bookmark(Search home) {
	
	}
	@Override
	public Flowable<List<Search>> getOfflineList() {
		return null;
	}
}
