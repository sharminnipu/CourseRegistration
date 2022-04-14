package com.yosufzamil.courseregistration.database.roomDAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.yosufzamil.courseregistration.database.entites.Course
import com.yosufzamil.courseregistration.database.entites.EnrolledCourse
import com.yosufzamil.courseregistration.database.entites.Student

@Dao
interface CourseRegistrationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertStudent(student: Student)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertCourse(course:Course)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertEnrolledCourse(enrolledCourse: EnrolledCourse)

    @Query("SELECT * FROM course")
    fun getAllCourse(): LiveData<List<Course>>


    @Query("SELECT * FROM student WHERE studentEmail= :email OR studentId= :id")
    fun getExistEmailAndID(email:String,id:String):Student


    @Query("SELECT * FROM student WHERE studentEmail= :email AND studentPassword= :password")
    fun getEmailAndPassword(email:String,password:String):Student

    @Query("SELECT * FROM enrolledcourse WHERE enrolledStudentId= :enrolledStudentId AND term= :term AND year= :year")
     fun getEnrolledCourseOfStudent(enrolledStudentId:String,term:Int,year:Int) :List<EnrolledCourse>

    @Query("SELECT * FROM enrolledcourse WHERE enrolledStudentId= :enrolledStudentId AND term= :term AND year= :year")
     fun getLiveDataEnrolledCourseOfStudent(enrolledStudentId:String,term:Int,year:Int) :LiveData<List<EnrolledCourse>>

    @Query("SELECT * FROM enrolledcourse WHERE enrolledStudentId= :enrolledStudentId AND courseId= :courseId")
    fun getExistCourseInEnrolled(courseId:String,enrolledStudentId: String) :EnrolledCourse


    @Query("SELECT * FROM enrolledcourse WHERE enrolledStudentId= :enrolledStudentId AND courseId= :courseId")
    fun getPrerequisiteCompletedCourseBeforeTakenTheCourse(courseId:String,enrolledStudentId: String) :EnrolledCourse


    @Query("SELECT * FROM enrolledcourse WHERE enrolledStudentId= :enrolledStudentId AND courseId= :coursePrerequisiteOne OR courseId= :coursePrerequisiteTwo ")
     fun getPrerequisiteCompletedCourseBeforeTakenTheCourseOROption(coursePrerequisiteOne:String,coursePrerequisiteTwo:String,enrolledStudentId: String) :EnrolledCourse

    @Query("SELECT * FROM enrolledcourse WHERE enrolledStudentId= :enrolledStudentId AND  courseId IN (:coursePrerequisiteOne,:coursePrerequisiteTwo)")
     fun getPrerequisiteCompletedCourseBeforeTakenTheCourseANDOption(coursePrerequisiteOne:String,coursePrerequisiteTwo:String,enrolledStudentId: String) :List<EnrolledCourse>

    @Query("SELECT * FROM enrolledcourse WHERE enrolledStudentId= :enrolledStudentId AND prerequisiteOne= :courseId OR prerequisiteTwo= :courseId")
    fun getExitedInPrerequisiteColumn(courseId:String?,enrolledStudentId: String):EnrolledCourse

    @Query("SELECT * FROM enrolledcourse WHERE enrolledStudentId= :enrolledStudentId AND  courseId IN (:courseOne,:courseTwo)")
    fun getStudentCompletedCourse(courseOne:String,courseTwo:String,enrolledStudentId: String) :List<EnrolledCourse>

    @Query("SELECT * FROM enrolledcourse WHERE enrolledStudentId= :enrolledStudentId AND  courseId IN (:courseOne,:courseTwo)")
    fun getStudentMandatoryCourse(courseOne:String,courseTwo:String,enrolledStudentId: String) :List<EnrolledCourse>


   @Delete
    suspend fun delete(enrolledCourse:EnrolledCourse)


}