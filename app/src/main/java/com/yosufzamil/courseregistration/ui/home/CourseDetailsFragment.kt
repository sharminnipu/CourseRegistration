package com.yosufzamil.courseregistration.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.yosufzamil.courseregistration.R
import com.yosufzamil.courseregistration.database.entites.Course
import com.yosufzamil.courseregistration.databinding.CourseDetailsFragmentBinding
import com.yosufzamil.courseregistration.utils.AppConstant.courseDetails
import kotlinx.android.synthetic.main.course_details_fragment.*

class CourseDetailsFragment : Fragment() {

private lateinit var binding: CourseDetailsFragmentBinding
   private lateinit var course:Course
    private var courses:List<Course> = ArrayList<Course>()
    private lateinit var viewModel: CourseDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return CourseDetailsFragmentBinding.inflate(inflater,container, false).also {
            binding=it
        }.root

      //  inflater.inflate(R.layout.course_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CourseDetailsViewModel::class.java)
        initState()

    }


    private fun initState(){
        course=courseDetails!!

        binding.tvAvailableCourseName.text=course.courseName
        binding.tvAvailableCourseID.text="CourseId : "+course.courseId
        when (course.status) {
            0 -> {
                binding.tvPrerequisite.text="None"
            }
            1 -> {
                binding.tvPrerequisite.text="CourseId : "+course.prerequisiteOne
            }
            2 -> {
                binding.tvPrerequisite.text="CourseId : "+course.prerequisiteOne+" or "+course.prerequisiteTwo
            }
            3 -> {
                binding.tvPrerequisite.text="CourseId : "+course.prerequisiteOne+" and "+course.prerequisiteTwo
            }
        }

        binding.tvTerm.text=course.term.toString()
       binding.tvCourseDescription.text=course.courseDescription

        binding.btnRegister.setOnClickListener {

        }

    }

}