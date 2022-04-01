package com.yosufzamil.courseregistration.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yosufzamil.courseregistration.databinding.ActivitySplashBinding
import com.yosufzamil.courseregistration.ui.authentication.AuthenticationActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getStartedBtn.setOnClickListener {
             finish()
            startActivity(Intent(this,AuthenticationActivity::class.java))
        }


    }
}