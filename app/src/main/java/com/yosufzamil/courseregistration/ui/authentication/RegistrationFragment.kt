package com.yosufzamil.courseregistration.ui.authentication

import android.content.Intent
import android.os.Binder
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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.yosufzamil.courseregistration.R
import com.yosufzamil.courseregistration.database.entites.Student
import com.yosufzamil.courseregistration.databinding.FragmentRegistrationBinding
import com.yosufzamil.courseregistration.viewModel.AuthenticationViewModel
import kotlinx.coroutines.launch


class RegistrationFragment : Fragment() {
    private lateinit var binding: FragmentRegistrationBinding
    private lateinit var viewModel:AuthenticationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return   FragmentRegistrationBinding.inflate(inflater, container, false).also {
            binding=it
        }.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signUpButtonPress()
        binding.tvLogin.setOnClickListener {
            activity?.finish()
            startActivity(Intent(requireActivity(),AuthenticationActivity::class.java))
        }
    }
    private fun signUpButtonPress(){
        binding.signUpBtn.setOnClickListener {
            insertStudentToDb() }
    }
    private fun insertStudentToDb(){
        viewModel= ViewModelProvider(this).get(AuthenticationViewModel::class.java)
        val studentName=binding.etStudentName.text.toString().trim()
        val studentEmail=binding.etStudentEmail.text.toString().trim()
        val studentPhone=binding.etStudentPhone.text.toString().trim()
        val studentId=binding.etStudentId.text.toString().trim()
        val newPassword=binding.etNewPassword.text.toString().trim()
        val confirmPassword=binding.etConfirmNo.text.toString().trim()

        if(!TextUtils.isEmpty(studentName) && !TextUtils.isEmpty(studentEmail) && !TextUtils.isEmpty(studentId) && !TextUtils.isEmpty(newPassword) && !TextUtils.isEmpty(confirmPassword)) {

            if (newPassword == confirmPassword) {
                var result=viewModel.getExistEmailORId(requireContext(),studentEmail,studentId)
                Log.e("result", result.toString() )

                if (result!=null) {
                        if( result.studentEmail == studentEmail){
                            Toast.makeText(
                                    requireContext(),
                                    "This email address already exist",
                                    Toast.LENGTH_SHORT
                            ).show()
                        }else if(result.studentId==studentId){
                            Toast.makeText(
                                    requireContext(),
                                    "Student ID already exist",
                                    Toast.LENGTH_SHORT
                            ).show()
                        }


                    } else {

                        val result = viewModel.registerStudentToDb(
                                requireContext(),
                                Student(studentId, studentName, studentEmail, studentPhone, confirmPassword)
                        )
                        if (result) {
                            binding.etStudentName.text.clear()
                            binding.etStudentEmail.text.clear()
                            binding.etStudentPhone.text.clear()
                            binding.etStudentId.text.clear()
                            binding.etNewPassword.text.clear()
                            binding.etConfirmNo.text.clear()
                            Toast.makeText(
                                    requireContext(),
                                    "Registration is successfully completed!!",
                                    Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                    requireContext(),
                                    "Sorry, Registration isn't successfully completed!!",
                                    Toast.LENGTH_SHORT
                            ).show()
                        }

                    }

            }

        }else{
            Toast.makeText(
                requireContext(),
                "Please fill up the field",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}