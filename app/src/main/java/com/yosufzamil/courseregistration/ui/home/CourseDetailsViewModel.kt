package com.yosufzamil.courseregistration.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.yosufzamil.courseregistration.database.entites.Student
import com.yosufzamil.courseregistration.database.entites.StudentCourseCrossRef
import com.yosufzamil.courseregistration.database.relation.StudentWithCourses
import com.yosufzamil.courseregistration.repository.LocalDBRepository

class CourseDetailsViewModel : ViewModel() {

    fun registerCourseToDb(context: Context, registrationCourse:StudentCourseCrossRef):Boolean{
        LocalDBRepository.registerCourse(context,registrationCourse)
        return true
    }

    fun getRegisterCourse(context: Context, studentId:String):LiveData<List<StudentWithCourses>>?{
        return  LocalDBRepository.getRegisterCourse(context,studentId)

    }
}