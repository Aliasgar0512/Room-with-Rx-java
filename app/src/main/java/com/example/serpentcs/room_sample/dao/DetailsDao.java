package com.example.serpentcs.room_sample.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.serpentcs.room_sample.entity.Details;

import java.util.List;

/**
 * Created by serpentcs on 30/12/17.
 */
@Dao
public interface DetailsDao
{
    @Query("SELECT * FROM DETAILS")
    List<Details> getAllDetails();

    @Query("SELECT * FROM DETAILS WHERE name LIKE :name")
    Details findByName(String name);

    @Query("SELECT COUNT(*) FROM DETAILS")
    int totalSavedInfo();

    @Insert
    void insertAll(Details details);

    @Delete
    void deleteAll(Details details);

    @Query("DELETE FROM DETAILS WHERE name LIKE :name AND emailOrUsername LIKE :emailOrusername")
    void deleteOne(String name,String emailOrusername);
}
