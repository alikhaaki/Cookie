package com.ali.cookie.model.repo.bookmark;

import com.ali.cookie.model.data.Home;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class BookmarkRepository {
	
	private BookmarkCloudDataSource homeCloudDataSource;
	private BookmarkLocalDataSource homeLocalDataSource;
	
	private static final String TAG = "HomeRepository";
	
	public BookmarkRepository(BookmarkCloudDataSource homeCloudDataSource, BookmarkLocalDataSource homeLocalDataSource) {
		this.homeCloudDataSource = homeCloudDataSource;
		this.homeLocalDataSource = homeLocalDataSource;
	}
	
	public Flowable<List<Home>> getListBookmark(){
		return homeLocalDataSource.getBookmarkedList();
	}
}
