package com.yosufzamil.courseregistration.ui.authentication

import android.os.Binder
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.yosufzamil.courseregistration.R
import com.yosufzamil.courseregistration.database.entites.Student
import com.yosufzamil.courseregistration.databinding.FragmentRegistrationBinding
import com.yosufzamil.courseregistration.viewModel.AuthenticationViewModel


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

                val existResult =
                    viewModel.getExistEmailORId(requireContext(), studentEmail, studentId)
                Log.e("student data:", existResult.toString())
                   /* if(existResult){
                    Toast.makeText(
                        requireContext(),
                        "This email address already exist",
                        Toast.LENGTH_SHORT
                    ).show()

                }else{
                    val result = viewModel.insertStudentToDb(
                        requireContext(),
                        Student(studentId, studentName, studentEmail, studentPhone, confirmPassword)
                    )
                    if(result){
                        Toast.makeText(
                            requireContext(),
                            "Registration is successfully completed!!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }else{
                        Toast.makeText(
                            requireContext(),
                            "Sorry, Registration isn't successfully completed!!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }



            } else {
                Toast.makeText(
                    requireContext(),
                    "Sorry the password isn't match",
                    Toast.LENGTH_SHORT
                ).show()
            }  */
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