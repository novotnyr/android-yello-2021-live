package com.github.novotnyr.yello

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val description: String,
    val timestamp: Long = Date().time
)