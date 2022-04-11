package com.yosufzamil.courseregistration.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.asLiveData
import com.yosufzamil.courseregistration.databinding.ActivitySplashBinding
import com.yosufzamil.courseregistration.ui.home.MainActivity
import com.yosufzamil.courseregistration.ui.authentication.AuthenticationActivity
import com.yosufzamil.courseregistration.utils.AppConstant
import com.yosufzamil.courseregistration.utils.sessionManager.UserPreference

class SplashActivity : AppCompatActivity() {
    private  var _binding: ActivitySplashBinding?=null
    private  val binding get()=_binding!!
    private lateinit var userPreference: UserPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding= ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userPreference= UserPreference(this)
        studentEmail()
        studentName()
        studentId()
        goToActivity()
    }
    private fun goToActivity(){
        binding.getStartedBtn.setOnClickListener {
            if(AppConstant.authUserEmail!=null){
                finish()
                startActivity(Intent(this, MainActivity::class.java))
            }else{
                finish()
                startActivity(Intent(this,AuthenticationActivity::class.java))
            }
        }

    }
    private  fun studentName(){
        userPreference.authName.asLiveData().observe(this, {
            Log.e("authName", it.toString())
            if(it!=null){
                AppConstant.authUserName=it
            }else{
                AppConstant.authUserName=null
            }

        })
    }
    private fun studentEmail(){
        userPreference.authEmail.asLiveData().observe(this, {
            Log.e("authEmail", it.toString())
            if(it!=null){
               AppConstant.authUserEmail=it
            }else{
                AppConstant.authUserEmail=null
            }

        })
    }
    private fun studentId(){
        userPreference.authId.asLiveData().observe(this, {
            Log.e("authId", it.toString())
            if(it!=null){
                AppConstant.authUserId=it
            }else{
                AppConstant.authUserId=null
            }

        })
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}