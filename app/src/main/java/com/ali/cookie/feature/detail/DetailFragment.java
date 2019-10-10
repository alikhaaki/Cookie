package com.ali.cookie.feature.detail;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ali.cookie.R;
import com.ali.cookie.base.ObserverFragment;
import com.ali.cookie.http.ApiServiceProvider;
import com.ali.cookie.model.data.Home;
import com.ali.cookie.model.db.HomeDatabase;
import com.ali.cookie.model.repo.home.HomeCloudDataSource;
import com.ali.cookie.model.repo.home.HomeLocalDataSource;
import com.ali.cookie.model.repo.home.HomeRepository;
import com.ali.cookie.service.AssistantLoadingFresco;
import com.ali.cookie.service.ImageLoadingServiceInjector;

import org.greenrobot.eventbus.EventBus;

import androidx.annotation.Nullable;

public class DetailFragment extends ObserverFragment {
	private Home home;
	private HomeDetailViewModel homeDetailViewModel;
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		home=getArguments().getParcelable("key");
		if (home==null){
			Toast.makeText(getContext(), "Data not received", Toast.LENGTH_SHORT).show();
		}
		homeDetailViewModel=new HomeDetailViewModel(new HomeRepository(new HomeCloudDataSource(ApiServiceProvider.getApiServices())
		,new HomeLocalDataSource(HomeDatabase.getInstance(getContext()).homeDao())));
	}
	@Override
	public void subscribe() {

		AssistantLoadingFresco imageView=baseView.findViewById(R.id.image_detail_home);
		TextView textViewTitle=baseView.findViewById(R.id.text_detail_title);
		TextView textViewDesc=baseView.findViewById(R.id.text_detail_desc);
		TextView textViewIng1=baseView.findViewById(R.id.text_detail_rec1);
		TextView textViewIng2=baseView.findViewById(R.id.text_detail_rec2);
		TextView textViewIng3=baseView.findViewById(R.id.text_detail_rec3);
		TextView textViewIng4=baseView.findViewById(R.id.text_detail_rec4);
		TextView textCount1=baseView.findViewById(R.id.text_rec1_count);
		TextView textCount2=baseView.findViewById(R.id.text_rec2_count);
		TextView textCount3=baseView.findViewById(R.id.text_rec3_count);
		TextView textCount4=baseView.findViewById(R.id.text_rec4_count);
		ImageView imageBookmark=baseView.findViewById(R.id.image_star_bookmark);

		ImageLoadingServiceInjector.getImageLoadingServices().loadImage(imageView,home.getImage());

		textViewTitle.setText(home.getTitle());
		textViewDesc.setText(home.getDesc());

		textViewIng1.setText(home.getIngr1());
		textViewIng2.setText(home.getIngr2());
		textViewIng3.setText(home.getIngr3());
		textViewIng4.setText(home.getIngr4());

		textCount1.setText(home.getIng1Count());
		textCount2.setText(home.getIng2Count());
		textCount3.setText(home.getIng3Count());
		textCount4.setText(home.getIng4Count());

		imageBookmark.setOnClickListener(v->{
			
			home.setBookmark(!home.isBookmark());
			homeDetailViewModel.addBookmark(home);
			
			//for update status of bookmark icon
			EventBus.getDefault().post(home);


			imageBookmark.setImageResource(home.isBookmark()?R.drawable.ic_star_fill:R.drawable.ic_star_border_black_24dp);

			
			if (home.isBookmark()){
				Toast.makeText(getContext(), "Bookmarked", Toast.LENGTH_SHORT).show();
			}else {
				Toast.makeText(getContext(), "Removed", Toast.LENGTH_SHORT).show();
			}


		});
		
		
		imageBookmark.setImageResource(home.isBookmark()?R.drawable.ic_star_fill:R.drawable.ic_star_border_black_24dp);
		
		
	}

	@Override
	public int getLayoutRes() {
		return R.layout.fragemnt_detail_home;
	}
}
