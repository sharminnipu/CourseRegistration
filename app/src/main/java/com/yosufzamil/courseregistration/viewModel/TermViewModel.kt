package com.yosufzamil.courseregistration.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.yosufzamil.courseregistration.database.entites.EnrolledCourse
import com.yosufzamil.courseregistration.repository.LocalDBRepository

class TermViewModel: ViewModel() {

    fun getLiveDataEnrolledCourse(context: Context, studentId:String, term:Int): LiveData<List<EnrolledCourse>>? {
        return LocalDBRepository.getLiveDataEnrolledCourse(context, studentId, term)

    }

    fun enrolledCourseDelete(context: Context,enrolledCourse: EnrolledCourse):Boolean{
        LocalDBRepository.deleteEnrolledCourse(context,enrolledCourse)
        return true
    }

    fun getExitedInPrerequisiteColumn(context: Context,courseId:String,studentId: String):EnrolledCourse?{

        return  LocalDBRepository.getExitedInPrerequisiteColumn(context,courseId,studentId)
    }

}