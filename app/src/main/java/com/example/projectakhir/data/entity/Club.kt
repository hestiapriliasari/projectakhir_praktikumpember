package com.example.projectakhir.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Club(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "club_name") var clubName: String?,
    @ColumnInfo(name = "julukan") var julukan: String?,
    @ColumnInfo(name = "stadion") var stadion: String?,
)