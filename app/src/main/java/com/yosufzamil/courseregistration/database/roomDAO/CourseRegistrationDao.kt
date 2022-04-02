package com.yosufzamil.courseregistration.database.roomDAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yosufzamil.courseregistration.database.entites.Student

@Dao
interface CourseRegistrationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertStudent(student: Student)

    @Query("SELECT * FROM student WHERE studentEmail= :email OR studentId= :id")
    fun getExistEmailAndID(email:String,id:String): LiveData<Student>


    @Query("SELECT * FROM student WHERE studentEmail= :email AND studentPassword= :password")
    fun getEmailAndPassword(email:String,password:String): LiveData<Student>




}