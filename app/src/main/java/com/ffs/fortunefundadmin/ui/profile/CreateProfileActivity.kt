package com.ffs.fortunefundadmin.ui.profile

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ffs.fortunefundadmin.utils.Constants
import fortunefundadmin.R
import fortunefundadmin.databinding.ActivityCreateProfileBinding
class CreateProfileActivity : AppCompatActivity() {

    lateinit var binding: ActivityCreateProfileBinding
    //lateinit var loginViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_profile)
       // loginViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        val mobile = intent.getStringExtra(Constants.MOBILE)

        if (mobile != null) {
            binding.editMobile.setText(mobile)
            binding.createAccountBtn.setOnClickListener {

                registerUser(mobile)
            }

        } else {
            Toast.makeText(this,"Phone no not valid",Toast.LENGTH_LONG)

        }

    }

    private fun registerUser(userMobile: String) {
  //      loginViewModel.registerUser(
//            binding.editName.text.toString(),
//            binding.editEmail.text.toString(),
//            userMobile!!.replace("+91", ""),
//            "+91",
//            "123456"
//        ).observe(
//            this
//        ) {
//          //  val intent = Intent(this, DashboardActivity::class.java)
//            intent.putExtra(Constants.MOBILE, userMobile)
//            startActivity(intent)
//            finish()
//
//        }
    }

    companion object {
        private const val TAG = "RegisterActivity"
    }

    fun showProgressBar() {
        binding.loginProgress.visibility = View.VISIBLE
        binding.layout3.visibility = View.GONE
    }

    fun hideProgressBar() {
        binding.loginProgress.visibility = View.GONE
        binding.layout3.visibility = View.VISIBLE
    }


}