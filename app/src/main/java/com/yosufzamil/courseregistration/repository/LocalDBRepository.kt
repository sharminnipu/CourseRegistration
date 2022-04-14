package com.yosufzamil.courseregistration.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.yosufzamil.courseregistration.database.CourseRegistrationDatabase
import com.yosufzamil.courseregistration.database.entites.Course
import com.yosufzamil.courseregistration.database.entites.EnrolledCourse
import com.yosufzamil.courseregistration.database.entites.Student
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
        fun insertMandatoryCourse(context: Context, allCourse:List<EnrolledCourse>){
            courseRegistrationDatabase= initialDB(context)

            CoroutineScope(IO).launch {
                allCourse.forEach { courseRegistrationDatabase?.courseRegistrationDao()?.insertEnrolledCourse(it)}
            }
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



        fun getEnrolledCourse(context: Context,studentId:String,term: Int): List<EnrolledCourse>? {
            courseRegistrationDatabase= initialDB(context)

            return courseRegistrationDatabase?.courseRegistrationDao()?.getEnrolledCourseOfStudent(studentId,term,2)

        }

        fun getLiveDataEnrolledCourse(context: Context,studentId:String,term: Int): LiveData<List<EnrolledCourse>>? {
            courseRegistrationDatabase= initialDB(context)
            return   courseRegistrationDatabase?.courseRegistrationDao()?.getLiveDataEnrolledCourseOfStudent(studentId,term,2)
        }

        fun getExistEnrolledCourse(context: Context,courseId:String,studentId: String): EnrolledCourse? {
            courseRegistrationDatabase= initialDB(context)

            return  courseRegistrationDatabase?.courseRegistrationDao()?.getExistCourseInEnrolled(courseId,studentId)


        }

        fun getExistPrerequisiteCourse(context: Context,courseId:String,studentId: String): EnrolledCourse? {
            courseRegistrationDatabase= initialDB(context)
            return   courseRegistrationDatabase?.courseRegistrationDao()?.getPrerequisiteCompletedCourseBeforeTakenTheCourse(courseId,studentId)
        }

        fun getExistPrerequisiteCourseOR(context: Context,prerequisiteOne:String,prerequisiteTwo:String,studentId: String): EnrolledCourse? {
            courseRegistrationDatabase= initialDB(context)
            return   courseRegistrationDatabase?.courseRegistrationDao()?.getPrerequisiteCompletedCourseBeforeTakenTheCourseOROption(prerequisiteOne,prerequisiteTwo,studentId)


        }

        fun getExistPrerequisiteCourseAND(context: Context,prerequisiteOne:String,prerequisiteTwo:String,studentId: String): List<EnrolledCourse>? {
            courseRegistrationDatabase= initialDB(context)
            return  courseRegistrationDatabase?.courseRegistrationDao()?.getPrerequisiteCompletedCourseBeforeTakenTheCourseANDOption(prerequisiteOne,prerequisiteTwo,studentId)
        }

        fun getStudentCompletedCourse(context: Context,courseIdOne:String,courseIdTwo:String,studentId: String): List<EnrolledCourse>? {
            courseRegistrationDatabase= initialDB(context)
            return  courseRegistrationDatabase?.courseRegistrationDao()?.getStudentCompletedCourse(courseIdOne,courseIdTwo,studentId)
        }

        fun getStudentMandatoryCourse(context: Context,courseIdOne:String,courseIdTwo:String,studentId: String): List<EnrolledCourse>? {
            courseRegistrationDatabase= initialDB(context)
            return  courseRegistrationDatabase?.courseRegistrationDao()?.getStudentMandatoryCourse(courseIdOne,courseIdTwo,studentId)
        }

        fun getExitedInPrerequisiteColumn(context: Context,courseId: String,studentId: String): EnrolledCourse? {
            courseRegistrationDatabase= initialDB(context)

            return  courseRegistrationDatabase?.courseRegistrationDao()?.getExitedInPrerequisiteColumn(courseId,studentId)


        }

      fun getStudentEmailORId(context: Context, email:String, id:String): Student? {
            courseRegistrationDatabase= initialDB(context)

          return courseRegistrationDatabase?.courseRegistrationDao()?.getExistEmailAndID(email,id)

        }


        fun getEmailAndPassword(context: Context, email:String, password:String): Student? {
            courseRegistrationDatabase= initialDB(context)
            return courseRegistrationDatabase?.courseRegistrationDao()?.getEmailAndPassword(email,password)
        }


        fun deleteEnrolledCourse(context: Context,enrolledCourse:EnrolledCourse){
            courseRegistrationDatabase= initialDB(context)
            CoroutineScope(IO).launch {
                courseRegistrationDatabase?.courseRegistrationDao()?.delete(enrolledCourse)
            }

        }

    }
}