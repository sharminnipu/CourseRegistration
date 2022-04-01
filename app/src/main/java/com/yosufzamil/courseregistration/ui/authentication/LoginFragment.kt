package com.yosufzamil.courseregistration.ui.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.yosufzamil.courseregistration.R
import com.yosufzamil.courseregistration.databinding.FragmentLoginBinding
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
    }


    fun loginButton(){
        viewModel= ViewModelProvider(this).get(AuthenticationViewModel::class.java)
    }


}