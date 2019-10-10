package com.ali.cookie.model.repo.search;

import com.ali.cookie.model.data.Search;
import com.ali.cookie.model.db.SearchDao;
import com.ali.cookie.model.repo.search.SearchDataSource;

import java.util.List;

import io.reactivex.Flowable;

public class SearchLocalDataSource implements SearchDataSource {
	private SearchDao searchDao;
	public SearchLocalDataSource(SearchDao searchDao) {
		this.searchDao = searchDao;
	}
	public int getCountList(){
		return searchDao.getCount();
	}
	public void saveSearchList(List<Search> search){
		searchDao.saveList(search);
	}
	@Override
	public Flowable<List<Search>> getList() {
		return null;
	}
	@Override
	public void bookmark(Search home) {
	
	}
	@Override
	public Flowable<List<Search>> getOfflineList() {
		return searchDao.getListData();
	}
}
