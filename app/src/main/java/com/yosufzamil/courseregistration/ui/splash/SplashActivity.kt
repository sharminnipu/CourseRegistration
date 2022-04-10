package com.yosufzamil.courseregistration.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.asLiveData
import com.yosufzamil.courseregistration.databinding.ActivitySplashBinding
import com.yosufzamil.courseregistration.ui.home.MainActivity
import com.yosufzamil.courseregistration.ui.authentication.AuthenticationActivity
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

        binding.getStartedBtn.setOnClickListener {
            userPreference.authEmail.asLiveData().observe(this, {
                Log.e("auth", it.toString())
                if(it!=null){
                    finish()
                    startActivity(Intent(this, MainActivity::class.java))
                }else{
                    finish()
                    startActivity(Intent(this,AuthenticationActivity::class.java))
                }

            })
        }


    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}