package com.ali.cookie.model.repo;

import java.util.List;

import io.reactivex.Flowable;

public interface BaseDataSource<T,E> {
	
	Flowable<List<T>> getList();
	
	void bookmark(T home);
	
	Flowable<List<T>> getOfflineList();
}
