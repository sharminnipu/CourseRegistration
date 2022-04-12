package com.yosufzamil.courseregistration.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.yosufzamil.courseregistration.R
import com.yosufzamil.courseregistration.database.entites.Course
import com.yosufzamil.courseregistration.database.entites.EnrolledCourse
import com.yosufzamil.courseregistration.repository.LocalDBRepository
import com.yosufzamil.courseregistration.ui.authentication.AuthenticationActivity
import com.yosufzamil.courseregistration.utils.AppConstant
import com.yosufzamil.courseregistration.utils.sessionManager.UserPreference
import com.yosufzamil.courseregistration.viewModel.AuthenticationViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var viewModel: AuthenticationViewModel
    private lateinit var userPreference: UserPreference
    lateinit var tvName: TextView
    lateinit var tvEmail: TextView
    lateinit var ivPhoto: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        userPreference= UserPreference(this)
        setSupportActionBar(toolbar)
        drawerNav()
        allCourseInsertedToDb()
        completedCourseLastYear()
        mandatoryCourseFor2ndYear()
    }

    private fun drawerNav(){
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_term_one, R.id.nav_term_two
        ), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        viewModel= ViewModelProvider(this).get(AuthenticationViewModel::class.java)

        //Todo get user profile attribute
        val headerView = navView.getHeaderView(0)
        // ivPhoto = headerView.findViewById(R.id.ivPhoto)

        tvName = headerView.findViewById(R.id.tvStudentName)
        tvEmail = headerView.findViewById(R.id.tvStudentEmail)
        studentName()
        studentEmail()
        studentId()

    }
    private  fun studentName(){
        if(AppConstant.authUserName==null){
            userPreference.authName.asLiveData().observe(this, {
                Log.e("authName", it.toString())
                if(it!=null){
                    AppConstant.authUserName=it
                    tvName.text=AppConstant.authUserName

                }

            })
        }else{
            tvName.text=AppConstant.authUserName
        }

    }
    private fun  studentEmail(){
        if(AppConstant.authUserEmail==null){
            userPreference.authEmail.asLiveData().observe(this, {
                Log.e("authEmail", it.toString())
                if(it!=null){
                    AppConstant.authUserEmail=it
                    tvEmail.text=AppConstant.authUserEmail
                }
            })
        }
        else{
            tvEmail.text=AppConstant.authUserEmail
        }
    }
    private fun  studentId(){
        if(AppConstant.authUserId==null){
            userPreference.authId.asLiveData().observe(this, {
                Log.e("authId", it.toString())
                if(it!=null){
                    AppConstant.authUserId=it
                }
            })
        }


    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_logout->{
                viewModel.deleteAuthEmail(this)
                finish()
                startActivity(Intent(this@MainActivity,AuthenticationActivity::class.java))

                Toast.makeText(this,"Successfully Logout !!",Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    /*
    There is insert all course data in the database.Data collected from assignment pdf file.I used status column for making condition course prerequisite.Where

    status 0=no prerequisite,
    status  1=single prerequisite,
    status 2=or condition,
    status 3=and condition
AND

    Mandatory column used for checking which course is mandatory course for per term.Where
    mandatory 0=no,this course is not mandatory for the term.
    mandatory 1=yes,this course is mandatory for the semester.So every student have to must take this course in the semester.
    */
    private fun allCourseInsertedToDb(){
        userPreference.courseInserted.asLiveData().observe(this, {
            Log.e("insertCourse: ",it.toString())
            if(it==null||it==false){
                val courses = listOf(
                    Course("CS128","Introduction to Coding",2,"None","None",0,2,0,
                        "Coding is telling a computer what to do, in a way that, with a bit of translation, it can understand. You give computers instructions in what is known as 'code', in a similar way to how you might have a recipe for how to cook something."),
                    Course("CS161","Introduction to Programming",1,"None","None",0,1,0,
                        "Coding is telling a computer what to do, in a way that, with a bit of translation, it can understand. You give computers instructions in what is known as 'code', in a similar way to how you might have a recipe for how to cook something."),
                    Course("CS162","Programming and Data Structure",2,"CS161","None",1,1,0,
                        "Coding is telling a computer what to do, in a way that, with a bit of translation, it can understand. You give computers instructions in what is known as 'code', in a similar way to how you might have a recipe for how to cook something."),
                    Course("CS215","Social Issues",2,"None","None",0,2,0,
                        "Coding is telling a computer what to do, in a way that, with a bit of translation, it can understand. You give computers instructions in what is known as 'code', in a similar way to how you might have a recipe for how to cook something."),
                    Course("CS225","Health Analytic",1,"CS161","cs128",2,2,0,
                        "Coding is telling a computer what to do, in a way that, with a bit of translation, it can understand. You give computers instructions in what is known as 'code', in a similar way to how you might have a recipe for how to cook something."),
                    Course("CS223","Data Science",1,"CS161","None",1,2,0,
                        "Coding is telling a computer what to do, in a way that, with a bit of translation, it can understand. You give computers instructions in what is known as 'code', in a similar way to how you might have a recipe for how to cook something."),
                    Course("CS255","Advanced Data Structure",1,"CS162","None",1,2,1,
                        "Coding is telling a computer what to do, in a way that, with a bit of translation, it can understand. You give computers instructions in what is known as 'code', in a similar way to how you might have a recipe for how to cook something."),
                    Course("CS263","Computer Architecture and Organization",2,"CS255","None",1,2,1,
                        "Coding is telling a computer what to do, in a way that, with a bit of translation, it can understand. You give computers instructions in what is known as 'code', in a similar way to how you might have a recipe for how to cook something."),
                    Course("CS275","Database Management Systems",2,"CS162","None",1,2,0,
                        "Coding is telling a computer what to do, in a way that, with a bit of translation, it can understand. You give computers instructions in what is known as 'code', in a similar way to how you might have a recipe for how to cook something."),
                    Course("CS277","Discrete Structure",1,"Math101","CS162",2,2,0,
                        "Coding is telling a computer what to do, in a way that, with a bit of translation, it can understand. You give computers instructions in what is known as 'code', in a similar way to how you might have a recipe for how to cook something."),
                    Course("CS340","Evolutionary Computation",1,"None","None",0,2,0,
                        "Coding is telling a computer what to do, in a way that, with a bit of translation, it can understand. You give computers instructions in what is known as 'code', in a similar way to how you might have a recipe for how to cook something."),
                    Course("CS356","Theory of Computing",1,"CS255","CS277",3,2,0,
                        "Coding is telling a computer what to do, in a way that, with a bit of translation, it can understand. You give computers instructions in what is known as 'code', in a similar way to how you might have a recipe for how to cook something."),
                    Course("CS356","Theory of Computing",2,"CS255","CS277",3,2,0,
                        "Coding is telling a computer what to do, in a way that, with a bit of translation, it can understand. You give computers instructions in what is known as 'code', in a similar way to how you might have a recipe for how to cook something."),
                    Course("CS364","Mobile App Development",1,"CS162","None",1,2,0,
                        "Coding is telling a computer what to do, in a way that, with a bit of translation, it can understand. You give computers instructions in what is known as 'code', in a similar way to how you might have a recipe for how to cook something."),
                    Course("CS368","Data Communications and Networking",1,"CS255","None",1,2,0,
                        "Coding is telling a computer what to do, in a way that, with a bit of translation, it can understand. You give computers instructions in what is known as 'code', in a similar way to how you might have a recipe for how to cook something."),
                    Course("CS375","Operating Systems",1,"CS255","None",1,2,0,
                        "Coding is telling a computer what to do, in a way that, with a bit of translation, it can understand. You give computers instructions in what is known as 'code', in a similar way to how you might have a recipe for how to cook something.")

                )
                LocalDBRepository.insertCourse(this,courses)
                lifecycleScope.launch {
                    userPreference.saveInsertCourse(true)
                }

            }

        })

    }
    private fun completedCourseLastYear(){
      userPreference.courseCompletedInserted.asLiveData().observe(this, {
          Log.e("insertCompletedCourse: ",it.toString())
          if(it==null||it==false){
              val courses = listOf(
                      Course(
                              "CS161", "Introduction to Programming", 1, "None", "None", 0, 1, 0,
                              "Coding is telling a computer what to do, in a way that, with a bit of translation, it can understand. You give computers instructions in what is known as 'code', in a similar way to how you might have a recipe for how to cook something."),
                      Course(
                              "CS162", "Programming and Data Structure", 2, "CS161", "None", 1, 1, 0,
                              "Coding is telling a computer what to do, in a way that, with a bit of translation, it can understand. You give computers instructions in what is known as 'code', in a similar way to how you might have a recipe for how to cook something.")

              )
              var completedCourse= listOf(
              EnrolledCourse(0,AppConstant.authUserId.toString(),courses[0]),
              EnrolledCourse(1,AppConstant.authUserId.toString(),courses[1])
              )
              completedCourse.forEach {enrolledCompletedCourse->
                  LocalDBRepository.enrolledCourse(this,enrolledCompletedCourse)
              }
              lifecycleScope.launch {
                  userPreference.saveInsertCompletedCourse(true)

              }

          }

      })

  }

    private fun mandatoryCourseFor2ndYear(){
        userPreference.courseMandatoryInserted.asLiveData().observe(this, {
            Log.e("insertMandatoryCourse: ",it.toString())
            if(it==null||it==false){
                val courses = listOf(
                         Course(
                        "CS255", "Advanced Data Structure", 1, "CS162", "None", 1, 2, 1,
                "Coding is telling a computer what to do, in a way that, with a bit of translation, it can understand. You give computers instructions in what is known as 'code', in a similar way to how you might have a recipe for how to cook something."),
                Course(
                        "CS263", "Computer Architecture and Organization", 2, "CS255", "None", 1, 2, 1,
                        "Coding is telling a computer what to do, in a way that, with a bit of translation, it can understand. You give computers instructions in what is known as 'code', in a similar way to how you might have a recipe for how to cook something."))
                var mandatoryCourses= listOf(
                        EnrolledCourse(2,AppConstant.authUserId.toString(),courses[0]),
                        EnrolledCourse(3,AppConstant.authUserId.toString(),courses[1])
                )
                mandatoryCourses.forEach {enrolledMandatoryCourse->
                    LocalDBRepository.enrolledCourse(this,enrolledMandatoryCourse)
                }
                lifecycleScope.launch {
                    userPreference.saveInsertMandatoryCourse(true)

                }

            }

        })

    }

}

