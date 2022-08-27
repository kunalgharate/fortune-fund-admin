package com.ffs.fortunefundadmin.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.ffs.fortunefundadmin.utils.Constants
import com.ffs.fortunefundadmin.MainActivity
import com.ffs.fortunefundadmin.domain.model.AppUser
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import fortunefundadmin.R
import fortunefundadmin.databinding.ActivityRegisterBinding


@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding
    lateinit var userViewModel: UserViewModel
    var auth = FirebaseAuth.getInstance();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        val mobile = intent.getStringExtra(Constants.MOBILE)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding.editMobile.setText(mobile)

        binding.createAccountBtn.setOnClickListener {

            if (binding.editName.text.isNotBlank() && binding.editMobile.text.isNotBlank() && binding.editEmail.text.isNotBlank()) {

                Log.d("prpinmg", "onCreate: +pringgggg")
                userViewModel.addUser(
                    AppUser(
                        auth.uid,
                        binding.editName.text.toString(),
                        binding.editMobile.text.toString(),
                        binding.editEmail.text.toString(),
                        "admin"
                    )
                ).isCompleted.apply {

                    val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }

            } else {
                Toast.makeText(this, "Please fill the details", Toast.LENGTH_LONG)
            }

        }


    }


    companion object {
        private const val TAG = "RegisterActivity"
    }

//    fun showProgressBar() {
//        binding.loginProgress.visibility = View.VISIBLE
//        binding.layout3.visibility = View.GONE
//    }
//
//    fun hideProgressBar() {
//        binding.loginProgress.visibility = View.GONE
//        binding.layout3.visibility = View.VISIBLE
//    }


}