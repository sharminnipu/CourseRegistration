package com.yosufzamil.courseregistration.ui.home.courseDetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.yosufzamil.courseregistration.database.entites.Course
import com.yosufzamil.courseregistration.database.entites.EnrolledCourse
import com.yosufzamil.courseregistration.database.entites.StudentCourseCrossRef
import com.yosufzamil.courseregistration.databinding.CourseDetailsFragmentBinding
import com.yosufzamil.courseregistration.utils.AppConstant
import com.yosufzamil.courseregistration.utils.AppConstant.courseDetails
import com.yosufzamil.courseregistration.utils.sessionManager.UserPreference
import com.yosufzamil.courseregistration.viewModel.CourseDetailsViewModel

class CourseDetailsFragment : Fragment() {

   private lateinit var binding: CourseDetailsFragmentBinding
   private lateinit var userPreference:UserPreference
   private lateinit var course:Course
   private lateinit var enrolledCourse:EnrolledCourse

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
        userPreference= UserPreference(requireContext())
        binding.tvAvailableCourseName.text=course.courseName
        binding.tvAvailableCourseID.text="CourseId : "+course.courseId
        when (course.status) {
            0 -> {
                binding.tvPrerequisite.text="None"  //status 0 means there is no prerequisite
            }
            1 -> {
                binding.tvPrerequisite.text="CourseId : "+course.prerequisiteOne //status 1 means there is prerequisite only one course.
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
            if(course.year==1){
                Toast.makeText(requireContext(),"You have already taken this last year course",Toast.LENGTH_LONG).show()
            }else{
                viewModel.getEnrolledCourse(requireContext(),AppConstant.authUserId.toString(),course.term)?.observe(requireActivity(), Observer {termCourses->
                    if(termCourses.size==3){
                        Toast.makeText(requireContext(),"Sorry you can't register.Already registered 3 courses",Toast.LENGTH_SHORT).show()
                    }
                    else{
                        viewModel.getExistEnrolledCourse(requireContext(),course.courseId)?.observe(requireActivity(), Observer {existCourse->

                            if(existCourse!=null){
                                Toast.makeText(requireContext(),"You have already taken this course",Toast.LENGTH_LONG).show()
                            }else{
                                if(course.status==0){
                                    enrolledCourse= EnrolledCourse(0,AppConstant.authUserId.toString(),course)
                                    var result= viewModel.enrolledCourseToDb(requireContext(),enrolledCourse)
                                    if(result){
                                        Toast.makeText(requireContext()," Course Registration is successfully completed.",Toast.LENGTH_SHORT).show()
                                    }
                                    else{
                                        Toast.makeText(requireContext(),"Your registration isn't completed!!",Toast.LENGTH_LONG).show()
                                    }
                                }else{

                                   //if status not 0 this part have to done
                                }

                            }


                        })

                    }

                })

            }



          /*  if(AppConstant.authUserId!=null){
                    enrolledCourse= EnrolledCourse(0,AppConstant.authUserId.toString(),course)
                    var result= viewModel.enrolledCourseToDb(requireContext(),enrolledCourse)
                    if(result){
                        Toast.makeText(requireContext()," Course Registration is successfully completed.",Toast.LENGTH_SHORT).show()
                    }
                }  */

        }

    }

}