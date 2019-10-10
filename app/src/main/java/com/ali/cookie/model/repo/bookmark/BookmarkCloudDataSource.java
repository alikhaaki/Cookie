package com.ali.cookie.model.repo.bookmark;

import com.ali.cookie.model.data.Home;
import com.ali.cookie.model.repo.BaseDataSource;

import java.util.List;

import io.reactivex.Flowable;

public class BookmarkCloudDataSource implements BookmarkDataSource  {
	
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
