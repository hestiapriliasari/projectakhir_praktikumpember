package com.example.projectakhir.data.dao

import androidx.room.*
import com.example.projectakhir.data.entity.Club


@Dao
interface ClubDao {

    // Mengambil semua data
    @Query("SELECT * FROM club")
    fun getAll(): List<Club>

    @Query("SELECT * FROM club WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Club>


    @Insert
    fun insertAll(vararg clubs: Club)

    @Delete
    fun delete(club : Club)

    @Query("SELECT * FROM club WHERE id= :id")
    fun get(id: Int) : Club

    @Update
    fun update(club: Club)

}