package com.yosufzamil.courseregistration.database.roomDAO

import androidx.datastore.preferences.protobuf.ListValue
import androidx.lifecycle.LiveData
import androidx.room.*
import com.yosufzamil.courseregistration.database.entites.Course
import com.yosufzamil.courseregistration.database.entites.Student
import com.yosufzamil.courseregistration.database.entites.StudentCourseCrossRef
import com.yosufzamil.courseregistration.database.relation.StudentWithCourses

@Dao
interface CourseRegistrationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertStudent(student: Student)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertCourse(course:Course)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertStudentAndCourse(crossRef: StudentCourseCrossRef)

    @Query("SELECT * FROM course")
    fun getAllCourse(): LiveData<List<Course>>


    @Query("SELECT * FROM student WHERE studentEmail= :email OR studentId= :id")
    fun getExistEmailAndID(email:String,id:String): LiveData<Student>


    @Query("SELECT * FROM student WHERE studentEmail= :email AND studentPassword= :password")
    fun getEmailAndPassword(email:String,password:String): LiveData<Student>

    @Transaction
    @Query("SELECT * FROM student WHERE studentId= :studentId")
     fun getSubjectOfStudent(studentId:String) :LiveData<List<StudentWithCourses>>


}