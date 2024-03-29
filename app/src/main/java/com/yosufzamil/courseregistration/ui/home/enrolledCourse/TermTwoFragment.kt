package com.yosufzamil.courseregistration.ui.home.enrolledCourse

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.yosufzamil.courseregistration.adapter.TermAdapter
import com.yosufzamil.courseregistration.database.entites.EnrolledCourse
import com.yosufzamil.courseregistration.databinding.FragmentTermTwoBinding
import com.yosufzamil.courseregistration.utils.sessionManager.UserPreference
import com.yosufzamil.courseregistration.viewModel.TermViewModel

class TermTwoFragment : Fragment() {
    private lateinit var binding:FragmentTermTwoBinding

    private lateinit var userPreference: UserPreference
    private lateinit var adapter: TermAdapter
    private lateinit var studentId:String
    private lateinit var courses:ArrayList<EnrolledCourse>
    private lateinit var viewModel: TermViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentTermTwoBinding.inflate(inflater,container,false).also {
            binding=it
        }.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchData()

    }

    private fun fetchData(){
        userPreference= UserPreference(requireContext())
        courses=ArrayList<EnrolledCourse>()
        viewModel = ViewModelProvider(this).get(TermViewModel::class.java)
        userPreference.authId.asLiveData().observe(requireActivity(), {
            Log.e("authId", it.toString())
            if(it!=null){
                studentId=it
                viewModel.getLiveDataEnrolledCourse(requireContext(),studentId,2)?.observe(viewLifecycleOwner, Observer { it ->
                    Log.e("enrolledCourse:",it.toString())
                    if(it.isEmpty()){
                        binding.emtyMsg2.visibility=View.VISIBLE
                    }else{
                        courses.clear()
                        courses.addAll(it as ArrayList<EnrolledCourse>)
                        Log.e("course size check",courses.size.toString())
                        loadAdapter(courses)
                    } }) } })




        /*   Log.e("course size check",courses.size.toString())

           if(courses.isNotEmpty()){
               adapter = TermAdapter(courses as MutableList<Course>)
               val llm = GridLayoutManager(applicationContext, 1)
               llm.orientation = GridLayoutManager.VERTICAL
               rvEnrolledCourseTermOne.layoutManager = llm
               rvEnrolledCourseTermOne.adapter = adapter

               adapter.onDelete={modelList,position->
                   var result=db.getExitedInPrerequisiteColumn(modelList[position].courseId.toString())

                   Log.e("result checking", result.toString())
                   if(result.courseId!=null){
                       Log.e("dlete option","helloone")
                       val builder = AlertDialog.Builder(this)
                       builder.setTitle("Warning!!")
                       builder.setMessage("If you delete this course so remove will be also ${result.courseId.toString()}")

                       builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                           var takeCourseDelete= db.deleteRegisterCourse(result.courseId.toString())
                           var prerequisiteCourseDelete= db.deleteRegisterCourse(modelList[position].courseId.toString())
                           if(prerequisiteCourseDelete && takeCourseDelete){
                               modelList.removeAt(position)
                               adapter.notifyDataSetChanged()
                               Toast.makeText(applicationContext,
                                   "Delete successfully course!!", Toast.LENGTH_SHORT).show()
                           }
                       }

                       builder.setNegativeButton(android.R.string.no) { dialog, which ->
                           Toast.makeText(applicationContext,
                               android.R.string.no, Toast.LENGTH_SHORT).show()
                       }
                       builder.show()
                   }else{
                       Log.e("delete option","hello")
                       var prerequisiteCourseDelete= db.deleteRegisterCourse(modelList[position].courseId.toString())
                       if(prerequisiteCourseDelete){
                           modelList.removeAt(position)
                           adapter.notifyDataSetChanged()
                           Toast.makeText(applicationContext,
                               "Delete successfully course!!", Toast.LENGTH_SHORT).show()
                       }

                   }
               }
           }else{
               emtyMsg.visibility=View.VISIBLE
           }   */

    }
    private fun loadAdapter(courses:ArrayList<EnrolledCourse>){
        adapter = TermAdapter(courses)
        val llm = GridLayoutManager(context, 1)
        llm.orientation = GridLayoutManager.VERTICAL
        binding.rvEnrolledCourseTermTwo.layoutManager = llm
        binding.rvEnrolledCourseTermTwo.adapter = adapter

        adapter.onDelete={modelList,position->
            var result=viewModel.enrolledCourseDelete(requireContext(),modelList[position])
            if(result){
                modelList.removeAt(position)
                adapter.notifyDataSetChanged()
                Toast.makeText(requireActivity(),"Delete successfully!!", Toast.LENGTH_SHORT).show()
            }

        }
    }

}