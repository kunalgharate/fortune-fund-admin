package com.ffs.fortunefundadmin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ffs.fortunefundadmin.core.Utils.Companion.printMessage
import com.ffs.fortunefundadmin.domain.model.Response
import com.ffs.fortunefundadmin.ui.investment_plan.InvestmentPlanViewModel
import com.ffs.fortunefundadmin.ui.login.LoginActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import fortunefundadmin.R
import fortunefundadmin.databinding.ActivityMainBinding


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
     val viewModel : InvestmentPlanViewModel by viewModels()
    var auth = FirebaseAuth.getInstance();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
       // viewModel = ViewModelProvider(this)[InvestmentPlanViewModel::class.java]

        if (auth.currentUser == null)
        {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
        viewModel.respons.observe(this){

            when(val booksResponse = it) {
                is Response.Loading ->     Log.d("Dataaa", "loading: ")
                is Response.Success -> {
                    Log.d("Dataaa", "${booksResponse.data[0].name} ")
                }
                is Error -> booksResponse.message?.let { printMessage(it) }
                else -> {}
            }

        }
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_recent_transactions, R.id.navigation_wallet,R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}