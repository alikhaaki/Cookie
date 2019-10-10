package com.ali.cookie.feature.bookmark;

import com.ali.cookie.model.data.Home;
import com.ali.cookie.model.repo.bookmark.BookmarkRepository;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class BookmarkViewModel {
	private BookmarkRepository bookmarkRepository;
	
	public BookmarkViewModel(BookmarkRepository bookmarkRepository) {
		this.bookmarkRepository = bookmarkRepository;
	}
	
	public Flowable<List<Home>> getBookmarkedList(){
		return bookmarkRepository.getListBookmark();
	}
	
}
