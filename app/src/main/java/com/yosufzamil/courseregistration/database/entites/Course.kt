package com.yosufzamil.courseregistration.database.entites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Course(
    @PrimaryKey(autoGenerate = false)
    val courseId:String,
    val courseName:String,
    val term:Int,
    val prerequisiteOne:String,
    val prerequisiteTwo:String,
    val status:Int,
    val year:Int,
    val mandatory:Int,
    val courseDescription:String,
    )