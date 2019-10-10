package com.ali.cookie.base;

import io.reactivex.disposables.CompositeDisposable;

public abstract class ObserverFragment extends BaseFragment {
	
	//handle disposable if you use Single Observer RxJava
	protected CompositeDisposable compositeDisposable = new CompositeDisposable();
	//for create view one time
	protected boolean isSubscribe=false;
	
	@Override
	public void onStart() {
		super.onStart();
		//subscribe when view started if that is not subscribed yet
		if (!isSubscribe){
			subscribe();
			isSubscribe=true;
		}
 	}
	@Override
	public void onStop() {
		super.onStop();
		//unSubscribe when view destroyed
		unSubscribe();
	}
	
	public abstract void subscribe();
	
	public void unSubscribe() {
		//clear compositeDisposable if fragment destroyed
		compositeDisposable.clear();
	}
	
}
