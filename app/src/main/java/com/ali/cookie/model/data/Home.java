package com.ali.cookie.model.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_home")
public class Home implements Parcelable {
	
	@SerializedName("image")
	private String image;
	
	@ColumnInfo(name = "is_bookmark")
	private boolean isBookmark;
	
	@SerializedName("ingr3")
	private String ingr3;
	
	@SerializedName("ingr2")
	private String ingr2;
	
	@SerializedName("ingr1")
	private String ingr1;
	
	@SerializedName("ing1_count")
	private String ing1Count;
	
	@SerializedName("ing2_count")
	private String ing2Count;

	@SerializedName("ing3_count")
	private String ing3Count;
	
	@SerializedName("ing4_count")
	private String ing4Count;
	
	@PrimaryKey
	@SerializedName("id")
	private int id;
	
	@SerializedName("title")
	private String title;
	
	@SerializedName("ingr4")
	private String ingr4;
	
	@SerializedName("desc")
	private String desc;
	
	public String getIng4Count() {
		return ing4Count;
	}
	public void setIng4Count(String ing4Count) {
		this.ing4Count = ing4Count;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setIngr3(String ingr3) {
		this.ingr3 = ingr3;
	}
	
	public String getIngr3() {
		return ingr3;
	}
	
	public void setIngr2(String ingr2) {
		this.ingr2 = ingr2;
	}
	
	public String getIngr2() {
		return ingr2;
	}
	
	public void setIngr1(String ingr1) {
		this.ingr1 = ingr1;
	}
	
	public String getIngr1() {
		return ingr1;
	}
	
	public void setIng1Count(String ing1Count) {
		this.ing1Count = ing1Count;
	}
	
	public String getIng1Count() {
		return ing1Count;
	}
	
	public void setIng2Count(String ing2Count) {
		this.ing2Count = ing2Count;
	}
	
	public String getIng2Count() {
		return ing2Count;
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
	
	public void setIngr4(String ingr4) {
		this.ingr4 = ingr4;
	}
	
	public String getIngr4() {
		return ingr4;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getDesc() {
		return desc;
	}
	public Home() {
	}
	public String getIng3Count() {
		return ing3Count;
	}
	public void setIng3Count(String ing3Count) {
		this.ing3Count = ing3Count;
	}
	public boolean isBookmark() {
		return isBookmark;
	}
	public void setBookmark(boolean bookmark) {
		isBookmark = bookmark;
	}
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.image);
		dest.writeByte(this.isBookmark ? (byte) 1 : (byte) 0);
		dest.writeString(this.ingr3);
		dest.writeString(this.ingr2);
		dest.writeString(this.ingr1);
		dest.writeString(this.ing1Count);
		dest.writeString(this.ing2Count);
		dest.writeString(this.ing3Count);
		dest.writeString(this.ing4Count);
		dest.writeInt(this.id);
		dest.writeString(this.title);
		dest.writeString(this.ingr4);
		dest.writeString(this.desc);
	}
	protected Home(Parcel in) {
		this.image = in.readString();
		this.isBookmark = in.readByte() != 0;
		this.ingr3 = in.readString();
		this.ingr2 = in.readString();
		this.ingr1 = in.readString();
		this.ing1Count = in.readString();
		this.ing2Count = in.readString();
		this.ing3Count = in.readString();
		this.ing4Count = in.readString();
		this.id = in.readInt();
		this.title = in.readString();
		this.ingr4 = in.readString();
		this.desc = in.readString();
	}
	public static final Creator<Home> CREATOR = new Creator<Home>() {
		
		@Override
		public Home createFromParcel(Parcel source) {
			return new Home(source);
		}
		@Override
		public Home[] newArray(int size) {
			return new Home[size];
		}
	};
}