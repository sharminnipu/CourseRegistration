package com.yosufzamil.courseregistration.repository

import android.content.Context
import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import com.yosufzamil.courseregistration.database.CourseRegistrationDatabase
import com.yosufzamil.courseregistration.database.entites.Course
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

        fun insertCourse(context: Context, allCourse:List<Course>){
            courseRegistrationDatabase= initialDB(context)

            CoroutineScope(IO).launch {
                allCourse.forEach { courseRegistrationDatabase?.courseRegistrationDao()?.insertCourse(it)}
            }
        }

        fun insertStudent(context: Context,student: Student){
           courseRegistrationDatabase= initialDB(context)
           CoroutineScope(IO).launch {
               courseRegistrationDatabase?.courseRegistrationDao()?.insertStudent(student)
           }

        }
        fun getAllCourse(context: Context): LiveData<List<Course>>? {
            courseRegistrationDatabase= initialDB(context)
            return courseRegistrationDatabase?.courseRegistrationDao()?.getAllCourse()
        }

        fun getStudentEmailORId(context: Context, email:String, id:String): LiveData<Student>? {
            courseRegistrationDatabase= initialDB(context)
             return courseRegistrationDatabase?.courseRegistrationDao()?.getExistEmailAndID(email,id)
        }


        fun getEmailAndPassword(context: Context, email:String, password:String): LiveData<Student>? {
            courseRegistrationDatabase= initialDB(context)
            return courseRegistrationDatabase?.courseRegistrationDao()?.getEmailAndPassword(email,password)
        }

    }
}