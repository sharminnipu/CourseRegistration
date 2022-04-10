package com.yosufzamil.courseregistration.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yosufzamil.courseregistration.database.entites.Student
import com.yosufzamil.courseregistration.repository.LocalDBRepository
import com.yosufzamil.courseregistration.utils.sessionManager.UserPreference
import kotlinx.coroutines.launch

class AuthenticationViewModel:ViewModel() {

    private lateinit var userPreference: UserPreference

    fun saveAuthStudent(context:Context, authUserEmail:String,authUserId:String){
         userPreference= UserPreference(context)
         viewModelScope.launch {
             userPreference.saveAuthUser(authUserEmail,authUserId)
         }

     }
    fun deleteAuthEmail(context:Context){
        userPreference= UserPreference(context)
        viewModelScope.launch {
            userPreference.deleteUserEmail()
        }

    }
    fun registerStudentToDb(context: Context, student: Student):Boolean{
        LocalDBRepository.insertStudent(context,student)
        return true
    }
    fun getExistEmailORId(context: Context, studentEmail:String,studentId:String):LiveData<Student>? {
        return LocalDBRepository.getStudentEmailORId(context,studentEmail,studentId)
    }
    fun getEmailAndPassword(context: Context, studentEmail:String,studentPassword:String):LiveData<Student>? {
        return LocalDBRepository.getEmailAndPassword(context,studentEmail,studentPassword)
    }


}