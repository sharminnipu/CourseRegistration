package com.yosufzamil.courseregistration.utils.sessionManager

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import com.yosufzamil.courseregistration.database.entites.Student
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map



class UserPreference(
    context: Context
) {
    private val applicationContext=context.applicationContext
    private val dataStore: DataStore<Preferences>

    init {

        dataStore=applicationContext.createDataStore(
            name = "my_data_store"
        )
    }
    val courseInserted: Flow<Boolean?>
        get() = dataStore.data.map {preferences ->
            preferences[KEY_COURSE]
        }
    val courseCompletedInserted: Flow<Boolean?>
        get() = dataStore.data.map {preferences ->
            preferences[KEY_COURSE_COMPLETED]
        }
    val courseMandatoryInserted: Flow<Boolean?>
        get() = dataStore.data.map {preferences ->
            preferences[KEY_COURSE_MANDATORY]
        }

    val authEmail: Flow<String?>
        get() = dataStore.data.map {preferences ->
            preferences[KEY_AUTH]
        }
    val authName: Flow<String?>
        get() = dataStore.data.map {preferences ->
            preferences[KEY_AUTH_NAME]
        }
    val authId: Flow<String?>
        get() = dataStore.data.map {preferences ->
            preferences[KEY_USER_ID]
        }

    suspend fun deleteUserEmail(){
        dataStore.edit {
            it.clear()
        }
    }

    suspend fun saveAuthUser(authEmail:String,authId:String,studentName:String){
        dataStore.edit {preferences ->
            preferences[KEY_AUTH]=authEmail
            preferences[KEY_USER_ID]=authId
            preferences[KEY_AUTH_NAME]=studentName


        }

    }
    suspend fun saveInsertCourse(isInsert:Boolean){
        dataStore.edit {preferences ->
            preferences[KEY_COURSE]=isInsert

        }
    }

    suspend fun saveInsertCompletedCourse(isInsert:Boolean){
        dataStore.edit {preferences ->
            preferences[KEY_COURSE_COMPLETED]=isInsert

        }
    }
    suspend fun saveInsertMandatoryCourse(isInsert:Boolean){
        dataStore.edit {preferences ->
            preferences[KEY_COURSE_MANDATORY]=isInsert

        }
    }


    companion object{
        private val KEY_AUTH= preferencesKey<String>("key_auth")
        private val KEY_AUTH_NAME= preferencesKey<String>("key_auth_name")
        private val KEY_COURSE= preferencesKey<Boolean>("key_all_course")
        private val KEY_COURSE_COMPLETED= preferencesKey<Boolean>("key_course_completed")
        private val KEY_COURSE_MANDATORY= preferencesKey<Boolean>("key_course_mandatory")
        private val KEY_USER_ID= preferencesKey<String>("key_student_Id")
    }


}