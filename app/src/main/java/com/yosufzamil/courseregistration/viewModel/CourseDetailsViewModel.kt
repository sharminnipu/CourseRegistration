package com.yosufzamil.courseregistration.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.yosufzamil.courseregistration.database.entites.Course
import com.yosufzamil.courseregistration.database.entites.EnrolledCourse
import com.yosufzamil.courseregistration.database.entites.Student
import com.yosufzamil.courseregistration.database.entites.StudentCourseCrossRef
import com.yosufzamil.courseregistration.database.relation.StudentWithCourses
import com.yosufzamil.courseregistration.repository.LocalDBRepository

class CourseDetailsViewModel : ViewModel() {

    fun registerCourseToDb(context: Context, registrationCourse:StudentCourseCrossRef):Boolean{
        LocalDBRepository.registerCourse(context,registrationCourse)
        return true
    }

    fun enrolledCourseToDb(context: Context,enrolledCourse: EnrolledCourse):Boolean{
        LocalDBRepository.enrolledCourse(context,enrolledCourse)
        return true
    }

    fun getRegisterCourse(context: Context, studentId:String,term:Int):LiveData<List<StudentWithCourses>>?{
        return  LocalDBRepository.getRegisterCourse(context,studentId,term)

    }

    fun getEnrolledCourse(context: Context, studentId:String,term:Int):LiveData<List<EnrolledCourse>>?{
        return  LocalDBRepository.getEnrolledCourse(context,studentId,term)

    }


    fun getExistEnrolledCourse(context: Context,courseId:String):LiveData<EnrolledCourse>?{
        return  LocalDBRepository.getExistEnrolledCourse(context,courseId)

    }
}