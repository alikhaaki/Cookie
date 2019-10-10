package com.ali.cookie.feature.bookmark;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ali.cookie.R;
import com.ali.cookie.model.data.Home;
import com.ali.cookie.service.AssistantLoadingFresco;
import com.ali.cookie.service.ImageLoadingServices;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.HolderBookmark> {
	private List<Home> list;
	private ImageLoadingServices imageLoadingServices;
	public BookmarkAdapter(List<Home> list, ImageLoadingServices imageLoadingServices) {
		this.list = list;
		
		this.imageLoadingServices = imageLoadingServices;
	}
	
	@NonNull
	@Override
	public HolderBookmark onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bookmark,parent,false);
		
		return new HolderBookmark(v);
	}
	@Override
	public void onBindViewHolder(@NonNull HolderBookmark holder, int position) {
		holder.BindHolder(list.get(position));
		
	}
	@Override
	public int getItemCount() {
		return list.size();
	}
	public class HolderBookmark extends RecyclerView.ViewHolder {
		private AssistantLoadingFresco imageView;
		private TextView textView;
		public HolderBookmark(@NonNull View itemView) {
			super(itemView);
			imageView=itemView.findViewById(R.id.image_bookmark);
			textView=itemView.findViewById(R.id.text_bookmark);
			
		}
		public void BindHolder (Home home){
			

			
			imageLoadingServices.loadImage(imageView,home.getImage());
			textView.setText(home.getTitle());
			
			itemView.setOnClickListener(v->{
				Bundle bundle=new Bundle();
				bundle.putParcelable("key",home);
				
				Navigation.findNavController(v).navigate(R.id.action_bookmark_frg_to_detailFragment2,bundle);
			});
			
		}
	}
	
}
