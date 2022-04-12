package com.yosufzamil.courseregistration.database.roomDAO

import androidx.datastore.preferences.protobuf.ListValue
import androidx.lifecycle.LiveData
import androidx.room.*
import com.yosufzamil.courseregistration.database.entites.Course
import com.yosufzamil.courseregistration.database.entites.EnrolledCourse
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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertEnrolledCourse(enrolledCourse: EnrolledCourse)

    @Query("SELECT * FROM course")
    fun getAllCourse(): LiveData<List<Course>>


    @Query("SELECT * FROM student WHERE studentEmail= :email OR studentId= :id")
    fun getExistEmailAndID(email:String,id:String): LiveData<Student>


    @Query("SELECT * FROM student WHERE studentEmail= :email AND studentPassword= :password")
    fun getEmailAndPassword(email:String,password:String): LiveData<Student>

    @Query("SELECT * FROM enrolledcourse WHERE enrolledStudentId= :enrolledStudentId AND term= :term")
    fun getEnrolledCourseOfStudent(enrolledStudentId:String,term:Int) :LiveData<List<EnrolledCourse>>

    @Query("SELECT * FROM enrolledcourse WHERE courseId= :courseId")
    fun getExistCourseInEnrolled(courseId:String) :LiveData<EnrolledCourse>

    @Transaction
   // @Query("SELECT * FROM student JOIN course ON student.studentId = Album.artistId")
    @Query("SELECT * FROM student WHERE studentId= :studentId")
    fun getSubjectOfStudent(studentId:String) :LiveData<List<StudentWithCourses>>

  //  @Query("SELECT * FROM student INNER JOIN course ON studentId = studentId WHERE term=:term"
    // fun getSubjectOfStudent(studentId:String,term:String) :LiveData<List<StudentWithCourses>>



}