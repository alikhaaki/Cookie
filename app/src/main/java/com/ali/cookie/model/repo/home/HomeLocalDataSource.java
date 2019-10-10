package com.ali.cookie.model.repo.home;

import com.ali.cookie.model.data.Home;
import com.ali.cookie.model.db.HomeDao;
import com.ali.cookie.model.repo.home.HomeDataSource;

import java.util.List;

import io.reactivex.Flowable;

public class HomeLocalDataSource implements HomeDataSource {
	
	private HomeDao homeDao;
	
	public HomeLocalDataSource(HomeDao homeDao) {
		this.homeDao = homeDao;
	}
	
	public int getCountedItem() {
		return homeDao.getCount();
	}
	
	public void saveHome(List<Home> home) {
		homeDao.saveList(home);
	}
	
	@Override
	public Flowable<List<Home>> getList() {
		return null;
	}
	
	@Override
	public void bookmark(Home home) {
		homeDao.bookmark(home);
	}
	
	@Override
	public Flowable<List<Home>> getOfflineList() {
		return homeDao.getListData();
	}
	
}
