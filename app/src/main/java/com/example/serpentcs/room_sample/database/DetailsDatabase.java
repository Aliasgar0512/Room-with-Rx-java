package com.example.serpentcs.room_sample.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.serpentcs.room_sample.dao.DetailsDao;
import com.example.serpentcs.room_sample.entity.Details;

/**
 * Created by serpentcs on 30/12/17.
 */
@Database(entities = {Details.class},version = 1)
public abstract class DetailsDatabase extends RoomDatabase
{
    public static volatile DetailsDatabase INSTANCE;
    public abstract DetailsDao detailsDao();
    public static DetailsDatabase getInstance(Context context)
    {
        if(INSTANCE == null)
        {
            synchronized (DetailsDatabase.class)
            {
                if(INSTANCE == null)
                {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DetailsDatabase.class,"Details.db").build();
                }
            }
        }
        return INSTANCE;
    }
}
