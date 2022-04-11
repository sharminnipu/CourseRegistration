package com.yosufzamil.courseregistration.ui.home.avaiableCourse

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.yosufzamil.courseregistration.R
import com.yosufzamil.courseregistration.adapter.AvailableCourseAdapter
import com.yosufzamil.courseregistration.database.entites.Course
import com.yosufzamil.courseregistration.databinding.AvailableCourseRecycleviewBinding
import com.yosufzamil.courseregistration.viewModel.AvaiableViewModel
import com.yosufzamil.courseregistration.utils.AppConstant.courseDetails
import kotlinx.android.synthetic.main.fragment_home.*

class AvailableFragment : Fragment() {

    private lateinit var avaiableViewModel: AvaiableViewModel
    private lateinit var adapter: AvailableCourseAdapter
    private lateinit var courses:ArrayList<Course>

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        avaiableViewModel = ViewModelProvider(this).get(AvaiableViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        courses=ArrayList<Course>()
        avaiableViewModel.getAllCourse(requireContext())?.observe(viewLifecycleOwner, Observer {
            Log.e("course",it.toString())
            if(it.isEmpty()){

            }else{
                courses.clear()
                courses.addAll(it as ArrayList<Course>)
                fetchData(courses)
            }

        })
    }
    private fun fetchData(courses:ArrayList<Course>){

        adapter = AvailableCourseAdapter(courses)
        val llm = GridLayoutManager(requireContext(), 1)
        llm.orientation = GridLayoutManager.VERTICAL
        rvAvailableCourse.layoutManager = llm
        rvAvailableCourse.adapter = adapter
        adapter.onItemAction = {model,positon ->
            courseDetails=model
            findNavController().navigate(R.id.action_nav_home_to_courseDetailsFragment)
            //val intent= Intent(requireContext(),AvailableCourseDetails::class.java)
           // startActivity(intent)
            }

        adapter.onListViewAction={model, position ->
            courseDetails=model
            findNavController().navigate(R.id.action_nav_home_to_courseDetailsFragment)
           // val intent= Intent(applicationContext,AvailableCourseDetails::class.java)
          //  startActivity(intent)
        }
    }
}