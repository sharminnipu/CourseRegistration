package com.yosufzamil.courseregistration.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yosufzamil.courseregistration.R
import com.yosufzamil.courseregistration.database.entites.Course
import kotlinx.android.synthetic.main.available_course_recycleview.view.*

class AvailableCourseAdapter(val dataList: ArrayList<Course>): RecyclerView.Adapter<AvailableCourseAdapter.FeedViewHolder>() {

    var onItemAction: ((model: Course, position: Int) -> Unit)? = null
    var onListViewAction: ((model: Course, position: Int) -> Unit)? = null
    //var onEventAction:((model: Event)-> Unit)?=null

    var context: Context?=null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FeedViewHolder {
        context=parent.context
        return FeedViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.available_course_recycleview, parent, false)

        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }



    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {

        val course = dataList[position]
        holder.tvCourseName.text=course.courseName
        holder.tvCourseId.text="CourseId :  "+course.courseId
        holder.btnNext.setOnClickListener {
            val course=dataList[position]
            onItemAction?.invoke(course,position)
        }
        holder.itemView.setOnClickListener {
            val course=dataList[position]
            onListViewAction?.invoke(course,position)
        }


    }



    inner class FeedViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCourseName=view.tvCourseName
        val tvCourseId=view.tvCourseId
        val btnNext=view.nextBtn




    }




}