package com.yosufzamil.courseregistration.database.entites

import androidx.room.Entity

@Entity(primaryKeys = ["studentId","courseId"])
data class StudentCourseCrossRef(
    val studentId:String,
    val courseId:String

)
