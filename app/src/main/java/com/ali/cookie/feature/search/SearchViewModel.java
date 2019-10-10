package com.ali.cookie.feature.search;

import com.ali.cookie.model.data.Search;
import com.ali.cookie.model.repo.search.SearchRepository;

import java.util.List;

import io.reactivex.Flowable;

public class SearchViewModel {
 
	private SearchRepository searchRepository;
	public SearchViewModel(SearchRepository searchRepository) {
		this.searchRepository = searchRepository;
	}
	public Flowable<List<Search>> getOffline(){
		return searchRepository.getOfflineSearch();
	}
	public Flowable<List<Search>> getSearchList(){
		return searchRepository.getSearch();
	}
}
