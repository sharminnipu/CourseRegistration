package com.yosufzamil.courseregistration.repository

import android.content.Context
import android.provider.ContactsContract
import com.yosufzamil.courseregistration.database.CourseRegistrationDatabase
import com.yosufzamil.courseregistration.database.entites.Student
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class LocalDBRepository {
    companion object{
        private  var courseRegistrationDatabase:CourseRegistrationDatabase?=null

        fun initialDB(context: Context):CourseRegistrationDatabase?{

            return CourseRegistrationDatabase.getInstance(context)
        }

        fun insertStudent(context: Context,student: Student){
           courseRegistrationDatabase= initialDB(context)
           CoroutineScope(IO).launch {
               courseRegistrationDatabase?.courseRegistrationDao()?.insertStudent(student)
           }

        }
        fun getStudentEmailORId(context: Context,email:String,id:String){
            courseRegistrationDatabase= initialDB(context)
            CoroutineScope(IO).launch {
               courseRegistrationDatabase?.courseRegistrationDao()?.getExistEmailAndID(email,id)
            }

        }

    }
}