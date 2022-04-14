package com.yosufzamil.courseregistration.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yosufzamil.courseregistration.database.entites.Course
import com.yosufzamil.courseregistration.database.entites.EnrolledCourse
import com.yosufzamil.courseregistration.database.entites.Student
import com.yosufzamil.courseregistration.repository.LocalDBRepository
import com.yosufzamil.courseregistration.utils.sessionManager.UserPreference
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {


    private lateinit var userPreference: UserPreference

    fun saveInsertCourse(context:Context,isInsertCourse:Boolean):Boolean{
        userPreference= UserPreference(context)
        viewModelScope.launch {
            userPreference.saveInsertCourse(isInsertCourse)
        }

        return true

    }

    fun saveInsertCompletedCourse(context:Context,isInsertCourse:Boolean):Boolean{
        userPreference= UserPreference(context)
        viewModelScope.launch {
            userPreference.saveInsertCompletedCourse(isInsertCourse)
        }

        return true

    }

    fun saveInsertMandatoryCourse(context:Context,isInsertCourse:Boolean):Boolean{
        userPreference= UserPreference(context)
        viewModelScope.launch {
            userPreference.saveInsertMandatoryCourse(isInsertCourse)
        }

        return true

    }

    fun insertCourseTODB(context: Context,allCourse:List<Course>):Boolean {
        LocalDBRepository.insertCourse(context,allCourse)
        return true
    }

    fun insertMandatoryCourse(context: Context,allCourse:List<EnrolledCourse>):Boolean {
        LocalDBRepository.insertMandatoryCourse(context,allCourse)
        return true
    }


}