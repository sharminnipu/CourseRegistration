package com.yosufzamil.courseregistration.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yosufzamil.courseregistration.R
import com.yosufzamil.courseregistration.database.entites.Course
import com.yosufzamil.courseregistration.database.entites.EnrolledCourse
import kotlinx.android.synthetic.main.registered_course_view.view.*

class TermAdapter(val dataList: ArrayList<EnrolledCourse>): RecyclerView.Adapter<TermAdapter.FeedViewHolder>() {

    var onDelete: ((dataList:ArrayList<EnrolledCourse>, position: Int) -> Unit)? = null


    var context: Context?=null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FeedViewHolder {
        context=parent.context
        return FeedViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.registered_course_view, parent, false)

        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }



    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {

        val enrolledCourse = dataList[position]
        holder.tvCourseName.text=enrolledCourse.course.courseName
        holder.tvCourseId.text="CourseId :  "+enrolledCourse.course.courseId


        when (enrolledCourse.course.status) {
            0 -> {//0 means there is no prerequisite
                holder.tvCoursePrerequisite.text = "Prerequisite : None"
            }
            1 -> {//1 means there is single prerequisite
                holder.tvCoursePrerequisite.text = "Prerequisite : " + enrolledCourse.course.prerequisiteOne
            }
            2 -> {//2 means there have prerequisite with or condition
                holder.tvCoursePrerequisite.text = "Prerequisite : " + enrolledCourse.course.prerequisiteOne + " or " + enrolledCourse.course.prerequisiteTwo
            }
            3 -> {//3 means there have prerequisite with and condition
                holder.tvCoursePrerequisite.text = "Prerequisite : " + enrolledCourse.course.prerequisiteOne + " and " + enrolledCourse.course.prerequisiteTwo
            }
        }


        if(enrolledCourse.course.mandatory==0){
            holder.btnDelete.setOnClickListener {
                onDelete?.invoke(dataList,position)
            }
        }else{
            holder.tvMandatoryMsg.visibility= View.VISIBLE
            holder.btnDelete.visibility= View.INVISIBLE
        }


        holder.tvCourseTerm.text="Term :  "+enrolledCourse.course.term.toString()


    }

    inner class FeedViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCourseName=view.tvRegisterCourseName
        val tvCourseId=view.tvRegisterCourseId
        val tvCoursePrerequisite=view.tvRegisterPrerequisite
        val tvCourseTerm=view.tvRegisterTerm
        val btnDelete=view.deleteCourseBtn
        val tvMandatoryMsg=view.tvMandatoryMsg

    }




}