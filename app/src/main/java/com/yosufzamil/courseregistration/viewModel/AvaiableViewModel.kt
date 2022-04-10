package com.yosufzamil.courseregistration.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yosufzamil.courseregistration.database.entites.Course
import com.yosufzamil.courseregistration.repository.LocalDBRepository

class AvaiableViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text



    fun getAllCourse(context: Context):LiveData<List<Course>>? {
        return LocalDBRepository.getAllCourse(context)
    }
}