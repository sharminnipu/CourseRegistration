package com.yosufzamil.courseregistration.database.entites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    @PrimaryKey(autoGenerate = false)
    val studentId:String,
    val studentName:String,
    val studentEmail:String,
    val studentPhone:String,
    val studentPassword:String
    )
