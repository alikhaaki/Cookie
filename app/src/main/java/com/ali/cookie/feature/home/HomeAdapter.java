package com.ali.cookie.feature.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ali.cookie.R;
import com.ali.cookie.model.data.Home;
import com.ali.cookie.service.AssistantLoadingFresco;
import com.ali.cookie.service.ImageLoadingServices;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HolderHome> {
	
	private List<Home> list;
	private ImageLoadingServices imageLoadingServices;
	private int pendingHomePosition;
	public HomeAdapter(List<Home> list, ImageLoadingServices imageLoadingServices) {
		this.list = list;
		this.imageLoadingServices = imageLoadingServices;
	}
	
	@NonNull
	@Override
	public HolderHome onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
		
		return new HolderHome(v);
	}
	@Override
	public void onBindViewHolder(@NonNull HolderHome holder, int position) {
		
		holder.BindHolder(list.get(position));
		
	}
	@Override
	public int getItemCount() {
		return list.size();
	}
	public class HolderHome extends RecyclerView.ViewHolder {
		
		private TextView textTitle;
		private AssistantLoadingFresco imageMain;
		public HolderHome(@NonNull View itemView) {
			super(itemView);
			textTitle = itemView.findViewById(R.id.text_title);
			imageMain = itemView.findViewById(R.id.image_main);
			
		}
		public void BindHolder(Home home) {
			
			textTitle.setText(home.getTitle());
			imageLoadingServices.loadImage(imageMain, home.getImage());
			Bundle bundle = new Bundle();
			bundle.putParcelable("key", home);
			itemView.setOnClickListener(v -> {
				pendingHomePosition=getAdapterPosition();
				Navigation.findNavController(v).navigate(R.id.action_home_to_detailFragment, bundle);
			});
		}
		
	}
	@Override
	public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
		super.onAttachedToRecyclerView(recyclerView);
		EventBus.getDefault().register(this);
	}
	@Override
	public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
		super.onDetachedFromRecyclerView(recyclerView);
		EventBus.getDefault().unregister(this);
		
	}
	/**we use Event bus for handling status of bookmark image in detail and in home list
	 * because if we don,t use this , every time list appear its refreshed and don,t bookmarked
	**/
	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onNewsBookmark(Home home){
		list.set(pendingHomePosition,home);
		notifyItemChanged(pendingHomePosition);
	}
}
