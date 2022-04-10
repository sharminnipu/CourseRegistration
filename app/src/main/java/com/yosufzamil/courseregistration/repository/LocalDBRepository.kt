package com.yosufzamil.courseregistration.repository

import android.content.Context
import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import com.yosufzamil.courseregistration.database.CourseRegistrationDatabase
import com.yosufzamil.courseregistration.database.entites.Course
import com.yosufzamil.courseregistration.database.entites.EnrolledCourse
import com.yosufzamil.courseregistration.database.entites.Student
import com.yosufzamil.courseregistration.database.entites.StudentCourseCrossRef
import com.yosufzamil.courseregistration.database.relation.StudentWithCourses
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class LocalDBRepository {

    companion object{

        private  var courseRegistrationDatabase:CourseRegistrationDatabase?=null

        private fun initialDB(context: Context):CourseRegistrationDatabase?{

            return CourseRegistrationDatabase.getInstance(context)
        }

        fun insertCourse(context: Context, allCourse:List<Course>){
            courseRegistrationDatabase= initialDB(context)

            CoroutineScope(IO).launch {
                allCourse.forEach { courseRegistrationDatabase?.courseRegistrationDao()?.insertCourse(it)}
            }
        }

        fun registerCourse(context: Context,crossRef: StudentCourseCrossRef){
            courseRegistrationDatabase= initialDB(context)

            CoroutineScope(IO).launch {
                courseRegistrationDatabase?.courseRegistrationDao()?.insertStudentAndCourse(crossRef)}
            }

        fun enrolledCourse(context: Context,enrolledCourse: EnrolledCourse){
            courseRegistrationDatabase= initialDB(context)

            CoroutineScope(IO).launch {
                courseRegistrationDatabase?.courseRegistrationDao()?.insertEnrolledCourse(enrolledCourse)}
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

        fun getRegisterCourse(context: Context,studentId:String,term:Int): LiveData<List<StudentWithCourses>>? {
            courseRegistrationDatabase= initialDB(context)
            return courseRegistrationDatabase?.courseRegistrationDao()?.getSubjectOfStudent(studentId)
        }

        fun getEnrolledCourse(context: Context,studentId:String,term: Int): LiveData<List<EnrolledCourse>>? {
            courseRegistrationDatabase= initialDB(context)
            return courseRegistrationDatabase?.courseRegistrationDao()?.getEnrolledCourseOfStudent(studentId,term)
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