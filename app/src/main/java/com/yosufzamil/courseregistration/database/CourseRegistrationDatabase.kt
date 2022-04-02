package com.yosufzamil.courseregistration.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yosufzamil.courseregistration.database.entites.Student
import com.yosufzamil.courseregistration.database.roomDAO.CourseRegistrationDao

@Database(entities = [Student::class], version = 1)
abstract class CourseRegistrationDatabase : RoomDatabase(){
    abstract fun courseRegistrationDao():CourseRegistrationDao
    companion object {
        private const val DATABASE_NAME = "CourseRegistrationDatabase"

        @Volatile
        var instance: CourseRegistrationDatabase? = null

        fun getInstance(context: Context):CourseRegistrationDatabase? {
            if (instance == null) {
                synchronized(CourseRegistrationDatabase::class.java) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context, CourseRegistrationDatabase::class.java,
                            DATABASE_NAME
                        )
                                .allowMainThreadQueries()
                                .build()
                    }
                }

            }

            return instance

        }
    }
}