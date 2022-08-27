package com.ffs.fortunefundadmin.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ffs.fortunefundadmin.utils.Constants
import com.ffs.fortunefundadmin.FortuneFundApp
import com.ffs.fortunefundadmin.MainActivity
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.AndroidEntryPoint
import fortunefundadmin.R
import fortunefundadmin.databinding.ActivityLoginBinding
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    // [START declare_auth]
    private lateinit var auth: FirebaseAuth
    var mobile: String? = null
    // [END declare_auth]
    private var storedVerificationId: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var loginBinding: ActivityLoginBinding;



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)

        hideAllLayouts()
        loginBinding.layout1.visibility = View.VISIBLE;

        //  loginViewModel.init()
        // [START initialize_auth]
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance();
        // [END initialize_auth]
        // Initialize phone auth callbacks
        // [START phone_auth_callbacks]
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Log.d(TAG, "onVerificationCompleted:$credential")
                signInWithPhoneAuthCredential(credential)
                hideProgressBar()
            }

            override fun onVerificationFailed(e: FirebaseException) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w(TAG, "onVerificationFailed", e)

                if (e is FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                } else if (e is FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                }

                hideAllLayouts()
                loginBinding.layout1.visibility = View.VISIBLE;
//                Utils.showLongToast(
//                    this@LoginActivity,
//                    "Verification failed,Please try again later !"
//                )
                // Show a message and update the UI
                hideProgressBar()
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent:$verificationId")
                hideAllLayouts()
                loginBinding.layout2.visibility = View.VISIBLE;
                hideProgressBar()
                //  loginBinding.phonenumberText.text=""

                // Save verification ID and resending token so we can use them later
                storedVerificationId = verificationId
                resendToken = token
                loginBinding.phonenumberText.text =
                    "+91 " + loginBinding.phonenumber.text.toString()
            }
        }
        loginBinding.submit1.setOnClickListener {
            showProgressBar()
            startPhoneNumberVerification(loginBinding.phonenumber.text.toString())
        }

        loginBinding.submit2.setOnClickListener {
            showProgressBar()
            verifyPhoneNumberWithCode(storedVerificationId, loginBinding.pinView.text.toString())
        }

        loginBinding.resentCode.setOnClickListener {
            showProgressBar()
            resendVerificationCode(loginBinding.phonenumber.text.toString(), resendToken)
        }


        loginBinding.skip.setOnClickListener {
            FortuneFundApp.getInstance().sharedPreferences.edit().putBoolean("isSkipped", true)
                .commit();
            finish()
        }


        // [END phone_auth_callbacks]
    }

    // [START on_start_check_user]
    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        // updateUI(currentUser)
    }
    // [END on_start_check_user]

    private fun startPhoneNumberVerification(phoneNumber: String) {
        // [START start_phone_auth]
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber("+91$phoneNumber")       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
        // [END start_phone_auth]
    }

    private fun verifyPhoneNumberWithCode(verificationId: String?, code: String) {
        // [START verify_with_code]
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        // [END verify_with_code]
        signInWithPhoneAuthCredential(credential)
    }

    // [START resend_verification]
    private fun resendVerificationCode(
        phoneNumber: String,
        token: PhoneAuthProvider.ForceResendingToken?
    ) {
        val optionsBuilder = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber("+91$phoneNumber")       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
        if (token != null) {
            optionsBuilder.setForceResendingToken(token) // callback's ForceResendingToken
        }
        PhoneAuthProvider.verifyPhoneNumber(optionsBuilder.build())
    }
    // [END resend_verification]

    // [START sign_in_with_phone]
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = task.result?.user
                    val isNew = task.result.additionalUserInfo!!.isNewUser
                    if (isNew) {
                        val intent = Intent(this, RegisterActivity::class.java)
                        intent.putExtra(Constants.MOBILE,user?.phoneNumber)
                        startActivity(intent)
                        finish()
                    } else {
                        if (user != null) {
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        else
                        {
                            FirebaseCrashlytics.getInstance().log("FIrebase user is null after login")
                        }
                    }

                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
               //     Utils.showLongToast(this, "Verification Failed .... Try again later")
                    // Update UI
                }

                hideProgressBar()
            }
    }



    companion object {
        private const val TAG = "PhoneAuthActivity"
    }

    fun showProgressBar() {
        loginBinding.loginProgress.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        loginBinding.loginProgress.visibility = View.GONE
    }


    fun hideAllLayouts() {
        loginBinding.layout1.visibility = View.GONE;
        loginBinding.layout2.visibility = View.GONE

    }



}