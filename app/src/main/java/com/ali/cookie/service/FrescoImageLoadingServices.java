package com.ali.cookie.service;

import android.net.Uri;

public class FrescoImageLoadingServices implements ImageLoadingServices {
	
	@Override
	public void loadImage(AssistantLoadingFresco imageView, String url) {
		imageView.setImageURI(Uri.parse(url));
	}
}
