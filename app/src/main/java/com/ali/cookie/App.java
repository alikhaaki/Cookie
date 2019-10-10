package com.ali.cookie;

import com.ali.cookie.service.AssistantLoadingFresco;
import com.facebook.drawee.backends.pipeline.Fresco;

import androidx.multidex.MultiDexApplication;

public class App extends MultiDexApplication {
	
 	@Override
	public void onCreate() {
		super.onCreate();
		Fresco.initialize(this);
		
		
 	}
}
