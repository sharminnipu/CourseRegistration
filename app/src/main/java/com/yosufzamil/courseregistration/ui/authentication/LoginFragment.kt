package com.yosufzamil.courseregistration.ui.authentication

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.yosufzamil.courseregistration.R
import com.yosufzamil.courseregistration.databinding.FragmentLoginBinding
import com.yosufzamil.courseregistration.ui.home.MainActivity
import com.yosufzamil.courseregistration.utils.AppConstant
import com.yosufzamil.courseregistration.utils.sessionManager.UserPreference
import com.yosufzamil.courseregistration.viewModel.AuthenticationViewModel
import kotlinx.coroutines.delay

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel:AuthenticationViewModel
    private lateinit var userPreference: UserPreference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return FragmentLoginBinding.inflate(inflater,container,false).also {
            binding=it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userPreference= UserPreference(requireContext())

        binding.tvSignUp.setOnClickListener {
            findNavController().navigate(R.id.registrationFragment)
        }

        binding.loginBtn.setOnClickListener {
            userLogin()
        }
    }

    private fun userLogin(){
        viewModel= ViewModelProvider(this).get(AuthenticationViewModel::class.java)
       var getEmail=binding.etEmail.text.toString().trim()
        var getPassword=binding.etPassword.text.toString().trim()

        Log.e("userEmail:",getEmail)
        Log.e("userPass:",getPassword)

        if(!TextUtils.isEmpty(getEmail) && !TextUtils.isEmpty(getPassword) ){
           var student= viewModel.getEmailAndPassword(requireContext(),getEmail,getPassword)

            Log.e("studentREsult:",student.toString())
                if(student!=null){
                    if(student.studentEmail==getEmail && student.studentPassword==getPassword){
                      var result= viewModel.saveAuthStudent(requireContext(),student.studentEmail,student.studentId,student.studentName)
                        if(result){
                            activity?.finish()
                            startActivity(Intent(requireActivity(), MainActivity::class.java))
                        }

                    }else{
                        Toast.makeText(requireContext(),"The credential isn't matches!!",Toast.LENGTH_SHORT).show()
                    }

                }else{
                    Toast.makeText(requireContext(),"The credential isn't matches!!",Toast.LENGTH_SHORT).show()
                }

        }else{
            Toast.makeText(requireContext(),"Please fill up the field.",Toast.LENGTH_SHORT).show()
        }


    }







}