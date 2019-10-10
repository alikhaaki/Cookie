package com.ali.cookie.feature.search;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ali.cookie.R;
import com.ali.cookie.model.data.Search;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.HolderSearch> {
	
	private List<Search> list;
	private Context context;
	
	public SearchAdapter(List<Search> list, Context context) {
		this.list = list;
		this.context = context;
	}
	@NonNull
	@Override
	public HolderSearch onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
		
		return new HolderSearch(v);
	}
	@Override
	public void onBindViewHolder(@NonNull HolderSearch holder, int position) {

		holder.BindSearch(list.get(position));
		
		if (position == 0) {
			
			holder.imageView.setImageResource(R.drawable.meat);
		} else if (position == 1) {
			holder.imageView.setImageResource(R.drawable.meat_dish);
		} else if (position == 2) {
			holder.imageView.setImageResource(R.drawable.fish);
		} else if (position == 3) {
			holder.imageView.setImageResource(R.drawable.vegetables);
		} else if (position == 4) {
			holder.imageView.setImageResource(R.drawable.juice_glass);
		}
		
		
		
	}
	
	@Override
	public int getItemCount() {
		return list.size();
	}
	
	public class HolderSearch extends RecyclerView.ViewHolder {
		
		private ImageView imageView;
		private TextView textView;
		private View relativeLayout;
		
		public HolderSearch(@NonNull View itemView) {
			super(itemView);
			imageView = itemView.findViewById(R.id.image_search);
			relativeLayout = itemView.findViewById(R.id.relative_search);
			textView = itemView.findViewById(R.id.text_search);
			
			
		}
		
		public void BindSearch (Search search){
			relativeLayout.setBackgroundColor(Color.parseColor(search.getColor()));
			textView.setText(search.getTitle());
			itemView.setOnClickListener(v->{
				Bundle bundle=new Bundle();
				bundle.putParcelable("search",search);
				Navigation.findNavController(v).navigate(R.id.action_search_to_detailSearch,bundle);
			});
		}
		
	}
	
}
