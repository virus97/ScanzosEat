package com.simonescanzani.scanzoseat.datamodels;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface OrderDao {

    @Query("SELECT * FROM 'Order'")
    List<Order> getAll();

    @Query("DELETE FROM 'Order'")
    void deleteAll();

    @Insert
    void insert(Order order);

    @Delete
    void delete(Order order);
}
