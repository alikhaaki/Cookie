package com.ali.cookie.model.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_search")
public class Search implements Parcelable {
	
	@SerializedName("image")
	private String image;
	
	@SerializedName("color")
	private String color;
	
	@PrimaryKey
	@SerializedName("id")
	private int id;
	
	@SerializedName("title")
	private String title;
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.image);
		dest.writeString(this.color);
		dest.writeInt(this.id);
		dest.writeString(this.title);
	}
	public Search() {
	}
	protected Search(Parcel in) {
		this.image = in.readString();
		this.color = in.readString();
		this.id = in.readInt();
		this.title = in.readString();
	}
	public static final Parcelable.Creator<Search> CREATOR = new Parcelable.Creator<Search>() {
		
		@Override
		public Search createFromParcel(Parcel source) {
			return new Search(source);
		}
		@Override
		public Search[] newArray(int size) {
			return new Search[size];
		}
	};
}