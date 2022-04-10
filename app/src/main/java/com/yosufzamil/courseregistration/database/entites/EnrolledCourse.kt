package com.yosufzamil.courseregistration.database.entites

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EnrolledCourse(
        @PrimaryKey(autoGenerate = true)
        val enrolledCourseId:Int,
        val enrolledStudentId:String,
        @Embedded val course: Course
)
