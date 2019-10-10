package com.ali.cookie.feature.detail;

import com.ali.cookie.model.data.Home;
import com.ali.cookie.model.repo.home.HomeRepository;

public class HomeDetailViewModel {
	
 
	private HomeRepository homeRepository;
	
	public HomeDetailViewModel(HomeRepository homeRepository) {
		this.homeRepository = homeRepository;
	}
	
	public void addBookmark(Home home){
		homeRepository.addToBookmark(home);
		
	}
}
