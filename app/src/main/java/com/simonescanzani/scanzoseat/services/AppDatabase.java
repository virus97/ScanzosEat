package com.simonescanzani.scanzoseat.services;

import android.content.Context;

import com.simonescanzani.scanzoseat.datamodels.Order;
import com.simonescanzani.scanzoseat.datamodels.OrderDao;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Order.class}, version=1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract OrderDao orderDao();

    public static AppDatabase createIstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "Order-database").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}
