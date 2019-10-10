package com.ali.cookie.model.db;

import android.content.Context;

import com.ali.cookie.model.data.Search;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Search.class}, version = 1, exportSchema = false)
public abstract class SearchDatabase extends RoomDatabase {
	
	private static SearchDatabase searchDatabase;
	public abstract SearchDao searchDao();
	
	public static SearchDatabase getInstance(Context context) {
		if (searchDatabase == null) {
			searchDatabase = Room.databaseBuilder(context, SearchDatabase.class, "db_s")
							.allowMainThreadQueries()
				               .build();
		}
		return searchDatabase;
	}
	
}
