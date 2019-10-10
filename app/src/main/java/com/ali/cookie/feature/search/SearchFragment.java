package com.ali.cookie.feature.search;

import android.os.Bundle;
import android.util.Log;

import com.ali.cookie.R;
import com.ali.cookie.base.ObserverFragment;
import com.ali.cookie.http.ApiServiceProvider;
import com.ali.cookie.model.db.SearchDatabase;
import com.ali.cookie.model.repo.search.SearchCloudDataSource;
import com.ali.cookie.model.repo.search.SearchLocalDataSource;
import com.ali.cookie.model.repo.search.SearchRepository;

import org.reactivestreams.Subscription;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchFragment extends ObserverFragment {
	
	private SearchViewModel searchViewModel;
	private Subscription subscription;
	private static final String TAG = "SearchFragment";
	
	@Override
	public void onDetach() {
		super.onDetach();
		if (subscription != null)
			subscription.cancel();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (subscription != null)
			subscription.cancel();
	}
	
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		searchViewModel = new SearchViewModel(new SearchRepository(new SearchCloudDataSource(ApiServiceProvider.getApiServices()),new SearchLocalDataSource(SearchDatabase.getInstance(getContext()).searchDao())));
		
	}
	
	@Override
	public void subscribe() {
		
		RecyclerView recyclerView = baseView.findViewById(R.id.recycler_search);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		
		searchViewModel.getOffline().subscribeOn(Schedulers.newThread())
			.observeOn(AndroidSchedulers.mainThread())
			.doOnNext(searches -> {
				
				recyclerView.setAdapter(new SearchAdapter(searches, getContext()));
			})
			.doOnError(throwable -> {
				Log.e(TAG, "subscribe: ", throwable);
			})
			.doOnSubscribe(subscription -> {
				this.subscription = subscription;
			})
			.subscribe();
	}
	
	@Override
	public int getLayoutRes() {
		return R.layout.fragemnt_search;
	}
}
