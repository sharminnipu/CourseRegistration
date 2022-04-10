package com.yosufzamil.courseregistration.ui.authentication

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.yosufzamil.courseregistration.R
import com.yosufzamil.courseregistration.databinding.FragmentLoginBinding
import com.yosufzamil.courseregistration.ui.home.MainActivity
import com.yosufzamil.courseregistration.viewModel.AuthenticationViewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel:AuthenticationViewModel
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

        binding.tvSignUp.setOnClickListener {
            findNavController().navigate(R.id.registrationFragment)
        }

        binding.loginBtn.setOnClickListener {
            userLogin()
        }
    }

    fun userLogin(){
        viewModel= ViewModelProvider(this).get(AuthenticationViewModel::class.java)
       var getEmail=binding.etEmail.text.toString().trim()
        var getPassword=binding.etPassword.text.toString().trim()

        if(!TextUtils.isEmpty(getEmail) && !TextUtils.isEmpty(getPassword) ){
            viewModel.getEmailAndPassword(requireContext(),getEmail,getPassword)?.observe(requireActivity(), Observer {
                if(it!=null){
                    if(it.studentEmail==getEmail && it.studentPassword==getPassword){
                        viewModel.saveAuthStudent(requireContext(),it.studentEmail,it.studentId)
                        activity?.finish()
                        startActivity(Intent(requireActivity(), MainActivity::class.java))
                    }else{
                        Toast.makeText(requireContext(),"The credential isn't matches!!",Toast.LENGTH_SHORT).show()
                    }

                }else{
                    Toast.makeText(requireContext(),"The credential isn't matches!!",Toast.LENGTH_SHORT).show()
                }
            })
        }else{
            Toast.makeText(requireContext(),"Please fill up the field.",Toast.LENGTH_SHORT).show()
        }


    }


}