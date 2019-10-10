package com.ali.cookie.model.repo.bookmark;

import com.ali.cookie.model.data.Home;
import com.ali.cookie.model.db.HomeDao;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class BookmarkLocalDataSource implements BookmarkDataSource {
	private HomeDao homeDao;
	public BookmarkLocalDataSource(HomeDao homeDao) {
		this.homeDao = homeDao;
	}
	
	public Flowable<List<Home>> getBookmarkedList(){
		return homeDao.getBookmarkList();
	}
	
	@Override
	public Flowable<List<Home>> getList() {
		return null;
	}
	@Override
	public void bookmark(Home home) {
	
	}
	@Override
	public Flowable<List<Home>> getOfflineList() {
		return null;
	}
}
