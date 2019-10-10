package com.ali.cookie.feature.bookmark;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ali.cookie.R;
import com.ali.cookie.model.db.HomeDatabase;
import com.ali.cookie.model.repo.bookmark.BookmarkCloudDataSource;
import com.ali.cookie.model.repo.bookmark.BookmarkLocalDataSource;
import com.ali.cookie.model.repo.bookmark.BookmarkRepository;
import com.ali.cookie.service.ImageLoadingServiceInjector;

import org.reactivestreams.Subscription;

public class BookmarkFragment extends Fragment {
	
	private BookmarkViewModel bookmarkViewModel;
	private Subscription subscription;
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (subscription!=null){
			subscription.cancel();
		}
	}
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		bookmarkViewModel=new BookmarkViewModel(new BookmarkRepository(new BookmarkCloudDataSource(),new BookmarkLocalDataSource(HomeDatabase.getInstance(getContext()).homeDao())));
		
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_bookmark, container, false);
	}
	
	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		RecyclerView recyclerView=view.findViewById(R.id.recycler_bookmark);
		
		recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
		
		bookmarkViewModel.getBookmarkedList().subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.doOnNext(list -> {
				recyclerView.setAdapter(new BookmarkAdapter(list, ImageLoadingServiceInjector.getImageLoadingServices()));
			}).doOnSubscribe(subscription -> {
				this.subscription=subscription;
		}).doOnError(throwable -> {
		
		}).subscribe();
		
		
	}
}
