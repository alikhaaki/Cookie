package com.ali.cookie.model.db;

import com.ali.cookie.model.data.Home;
import com.ali.cookie.model.data.Search;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Flowable;

@Dao
public abstract class SearchDao {
	
	@Query("SELECT *FROM tbl_search")
 	public abstract Flowable<List<Search>> getListData();
	
	@Query("SELECT COUNT(id) FROM tbl_search WHERE id LIKE 1")
	public abstract int getCount();
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	public abstract void saveList(List<Search> item);
	
	
}
