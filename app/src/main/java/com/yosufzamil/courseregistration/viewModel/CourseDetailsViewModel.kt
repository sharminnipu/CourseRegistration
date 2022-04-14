package com.yosufzamil.courseregistration.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.yosufzamil.courseregistration.database.entites.EnrolledCourse
import com.yosufzamil.courseregistration.repository.LocalDBRepository

class CourseDetailsViewModel : ViewModel() {

    fun enrolledCourseToDb(context: Context,enrolledCourse: EnrolledCourse):Boolean{
        LocalDBRepository.enrolledCourse(context,enrolledCourse)
        return true
    }


    fun getEnrolledCourse(context: Context, studentId:String,term:Int):List<EnrolledCourse>?{
        return  LocalDBRepository.getEnrolledCourse(context,studentId,term)

    }

    fun getExistEnrolledCourse(context: Context,courseId:String,studentId: String):EnrolledCourse?{
        return  LocalDBRepository.getExistEnrolledCourse(context,courseId,studentId)

    }

    fun getExitPrerequisiteCourse(context: Context,courseId:String,studentId: String):EnrolledCourse?{
        return  LocalDBRepository.getExistPrerequisiteCourse(context,courseId,studentId)

    }


    fun getExitPrerequisiteCourseOR(context: Context,prerequisiteOne:String,prerequisiteTwo:String,studentId: String):EnrolledCourse?{
        return  LocalDBRepository.getExistPrerequisiteCourseOR(context,prerequisiteOne,prerequisiteTwo,studentId)
    }

    fun getExitPrerequisiteCourseAND(context: Context,prerequisiteOne:String,prerequisiteTwo:String,studentId: String):List<EnrolledCourse>?{
        return  LocalDBRepository.getExistPrerequisiteCourseAND(context,prerequisiteOne,prerequisiteTwo,studentId)
    }
}