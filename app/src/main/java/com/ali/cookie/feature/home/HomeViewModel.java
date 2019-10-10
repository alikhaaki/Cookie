package com.ali.cookie.feature.home;

import com.ali.cookie.model.data.Home;
import com.ali.cookie.model.repo.home.HomeRepository;

import java.util.List;

import io.reactivex.Flowable;

public class HomeViewModel {
	
	private HomeRepository homeRepository;
	
	public HomeViewModel(HomeRepository homeRepository) {
		this.homeRepository = homeRepository;
	}
	
	public Flowable<List<Home>> getHomeList(){
		return homeRepository.getListHome();
	}
}
