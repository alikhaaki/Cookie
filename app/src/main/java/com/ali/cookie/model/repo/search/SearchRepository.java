package com.ali.cookie.model.repo.search;

import android.util.Log;

import com.ali.cookie.model.data.Search;
import com.ali.cookie.model.repo.search.SearchCloudDataSource;
import com.ali.cookie.model.repo.search.SearchLocalDataSource;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchRepository {
	
	private SearchCloudDataSource searchCloudDataSource;
	private SearchLocalDataSource searchLocalDataSource;
	private static final String TAG = "SearchRepository";
	
	public SearchRepository(SearchCloudDataSource searchCloudDataSource, SearchLocalDataSource searchLocalDataSource) {
		this.searchCloudDataSource = searchCloudDataSource;
		this.searchLocalDataSource = searchLocalDataSource;
	}
	public Flowable<List<Search>> getOfflineSearch() {
		if (searchLocalDataSource.getCountList() > 0) {
			searchLocalDataSource.getOfflineList().subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe();
		}
		searchCloudDataSource.getList().subscribeOn(Schedulers.newThread())
			.observeOn(AndroidSchedulers.mainThread())
			.doOnError(throwable -> {
				Log.e(TAG, "getOfflineSearch: ", throwable);
			})
			.doOnNext(searches -> {
				searchLocalDataSource.saveSearchList(searches);
			})
			.subscribe();
		return searchLocalDataSource.getOfflineList();
	}
	public Flowable<List<Search>> getSearch() {
		return searchCloudDataSource.getList();
	}
}
