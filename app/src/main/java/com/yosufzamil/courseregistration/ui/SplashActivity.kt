package com.yosufzamil.courseregistration.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yosufzamil.courseregistration.databinding.ActivitySplashBinding
import com.yosufzamil.courseregistration.ui.authentication.AuthenticationActivity

class SplashActivity : AppCompatActivity() {

    private  var _binding: ActivitySplashBinding?=null
    private  val binding get()=_binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding= ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getStartedBtn.setOnClickListener {
             finish()
            startActivity(Intent(this,AuthenticationActivity::class.java))
        }


    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}