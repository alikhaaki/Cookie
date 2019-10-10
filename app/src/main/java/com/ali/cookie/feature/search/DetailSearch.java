package com.ali.cookie.feature.search;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ali.cookie.R;
import com.ali.cookie.base.ObserverFragment;
import com.ali.cookie.model.data.Search;
import com.ali.cookie.service.AssistantLoadingFresco;
import com.ali.cookie.service.ImageLoadingServiceInjector;
import com.ali.cookie.service.ImageLoadingServices;

import androidx.annotation.Nullable;

public class DetailSearch extends ObserverFragment {
	private Search search;
	
	
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		search=getArguments().getParcelable("search");
		if (search==null){
			Toast.makeText(getContext(), "Data not received", Toast.LENGTH_SHORT).show();
			
		}
	}
	@Override
	public void subscribe() {
		AssistantLoadingFresco image=baseView.findViewById(R.id.image_detail_search);
		TextView textView=baseView.findViewById(R.id.text_detail_search);
		
		ImageLoadingServiceInjector.getImageLoadingServices().loadImage(image,search.getImage());
		textView.setText(search.getTitle());
		
		
	}
	@Override
	public int getLayoutRes() {
		return R.layout.fragment_detail_search;
	}
}
