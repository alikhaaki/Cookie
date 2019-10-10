package com.ali.cookie.model.repo.home;

import android.util.Log;

import com.ali.cookie.model.data.Home;
import com.ali.cookie.model.repo.home.HomeCloudDataSource;
import com.ali.cookie.model.repo.home.HomeLocalDataSource;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeRepository {
	
	private HomeCloudDataSource homeCloudDataSource;
	private HomeLocalDataSource homeLocalDataSource;
	
	private static final String TAG = "HomeRepository";
	
	public HomeRepository(HomeCloudDataSource homeCloudDataSource, HomeLocalDataSource homeLocalDataSource) {
		this.homeCloudDataSource = homeCloudDataSource;
		this.homeLocalDataSource = homeLocalDataSource;
	}
	
	public Flowable<List<Home>> getListHome() {
//		if (homeLocalDataSource.getCountedItem() > 0) {
//			homeLocalDataSource.getOfflineList().subscribeOn(Schedulers.newThread())
//				.observeOn(AndroidSchedulers.mainThread())
//				.subscribe();
//		}
		homeCloudDataSource.getList().subscribeOn(Schedulers.newThread())
			.observeOn(Schedulers.io())
			.doOnError(throwable -> {
				Log.e(TAG, "getListHome: ", throwable);
			})
			.doOnNext(homes -> {
				homeLocalDataSource.saveHome(homes);
			})
			.subscribe();
		
		return homeLocalDataSource.getOfflineList();
	}
	
	public void addToBookmark(Home home){
		homeLocalDataSource.bookmark(home);
	}
	
}
