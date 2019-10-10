package com.ali.cookie.model.db;

import android.content.Context;

import com.ali.cookie.model.data.Home;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Home.class}, version = 1, exportSchema = false)
public abstract class HomeDatabase extends RoomDatabase {
	
	private static HomeDatabase homeDatabase;
	public abstract HomeDao homeDao();
	
	public static HomeDatabase getInstance(Context context) {
		if (homeDatabase == null) {
			homeDatabase = Room.databaseBuilder(context, HomeDatabase.class, "db")
							.allowMainThreadQueries()
				               .build();
		}
		return homeDatabase;
	}
	
}
