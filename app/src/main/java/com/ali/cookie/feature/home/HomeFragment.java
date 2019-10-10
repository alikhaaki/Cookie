package com.ali.cookie.feature.home;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.ali.cookie.R;
import com.ali.cookie.base.ObserverFragment;
import com.ali.cookie.http.ApiServiceProvider;
import com.ali.cookie.model.data.Home;
import com.ali.cookie.model.db.HomeDatabase;
import com.ali.cookie.model.repo.home.HomeCloudDataSource;
import com.ali.cookie.model.repo.home.HomeLocalDataSource;
import com.ali.cookie.model.repo.home.HomeRepository;
import com.ali.cookie.service.ImageLoadingServiceInjector;
import com.yalantis.pulltomakesoup.PullToRefreshView;

import org.reactivestreams.Subscription;

import java.util.List;
import java.util.function.Consumer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeFragment extends ObserverFragment {
	
	private HomeViewModel homeViewModel;
	private static final String TAG = "HomeFragment";
	private RecyclerView recyclerView;
	private Subscription subscription;
	private PullToRefreshView pullToRefreshView;
	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		homeViewModel = new HomeViewModel(new HomeRepository(new HomeCloudDataSource(ApiServiceProvider.getApiServices()), new HomeLocalDataSource(HomeDatabase.getInstance(getContext()).homeDao())));
	}
	@Override
	public void onStart() {
		super.onStart();
		
	}
	@Override
	public void subscribe() {
		
		recyclerView = baseView.findViewById(R.id.recycler_home);
//		ProgressBar progressBar=baseView.findViewById(R.id.progress_bar_home);
		// TODO: 10/10/2019 create progress bar
		// TODO: 10/10/2019 create checking for internet connection
		pullToRefreshView = baseView.findViewById(R.id.swipe_soup);
		
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
	 
		refreshing();
	 
		homeViewModel.getHomeList().subscribeOn(Schedulers.newThread())
			.observeOn(AndroidSchedulers.mainThread())
			.doOnSubscribe(subscription1 -> {
				this.subscription = subscription1;
			})
			.doOnNext(homes -> {
					recyclerView.setAdapter(new HomeAdapter(homes,ImageLoadingServiceInjector.getImageLoadingServices()));

					
					
				}
			)
			.doOnError(throwable -> Log.e(TAG, "subscribe: ", throwable))
			.subscribe();
		
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (subscription != null)
			subscription.cancel();
	}
	@Override
	public void onDetach() {
		super.onDetach();
		if (subscription != null)
			subscription.cancel();
	}
	private void refreshing() {
		
		
		pullToRefreshView.setOnRefreshListener(() ->
			    pullToRefreshView.postDelayed(()
				    -> pullToRefreshView.setRefreshing(false), 3000));
	}
	@Override
	public int getLayoutRes() {
		return R.layout.fragemnt_home;
	}
}
