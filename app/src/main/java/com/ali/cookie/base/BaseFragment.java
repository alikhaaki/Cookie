package com.ali.cookie.base;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.InterruptedIOException;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import io.reactivex.plugins.RxJavaPlugins;

public abstract class BaseFragment extends Fragment {
	
	private static final String TAG = "BaseFragment";
	
	//to handle one time create view and save state
	protected View baseView;
	
	//abstract definition layout
	@LayoutRes
	public abstract int getLayoutRes();
	
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		//handle Error in RxJava
		RxJavaPlugins.setErrorHandler(throwable -> {
			if (throwable instanceof InterruptedIOException) {
				Log.e(TAG, "InterruptedIOException : ", throwable);
			}else if (throwable instanceof  InterruptedException){
				Log.e(TAG, "InterruptedException",throwable );
			}else if (throwable instanceof Exception){
				Log.e(TAG, "Exception ",throwable );
			}else {
				Log.e(TAG, "Other exception: ",throwable );
			}
		});
		
		if (baseView == null) {
			baseView = inflater.inflate(getLayoutRes(), container, false);
		}
		return baseView;
	}
}
