package com.yosufzamil.courseregistration.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.yosufzamil.courseregistration.database.entites.Student
import com.yosufzamil.courseregistration.repository.LocalDBRepository

class AuthenticationViewModel:ViewModel() {
    fun insertStudentToDb(context: Context, student: Student):Boolean{
        LocalDBRepository.insertStudent(context,student)
        return true
    }

    fun getExistEmailORId(context: Context, studentEmail:String,studentId:String) {
        return LocalDBRepository.getStudentEmailORId(context,studentEmail,studentId)
    }
}