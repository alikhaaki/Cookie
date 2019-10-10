package com.ali.cookie.model.db;

import com.ali.cookie.model.data.Home;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public abstract class HomeDao {
	
	@Query("SELECT *FROM tbl_home")
	public abstract Flowable<List<Home>> getListData();
	
	@Query("SELECT COUNT(id) FROM tbl_home WHERE id LIKE 1")
	public abstract int getCount();
	
	@Query("SELECT *FROM tbl_home WHERE is_bookmark LIKE 1")
	public abstract Flowable<List<Home>> getBookmarkList();
	
	//we should choose ignore strategy because every time list not replace new item
	@Insert(onConflict = OnConflictStrategy.IGNORE)
	public abstract void saveList(List<Home> item);
	

	@Update
	public abstract void bookmark(Home item);
	
	
}
