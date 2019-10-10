package com.ali.cookie.service;

public class ImageLoadingServiceInjector {
	private static ImageLoadingServices imageLoadingServices;
	
	public static ImageLoadingServices getImageLoadingServices() {
		if (imageLoadingServices==null){
			imageLoadingServices=new FrescoImageLoadingServices();
		}
		return imageLoadingServices;
	}
}
