package com.ffs.fortunefundadmin.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.ffs.fortunefundadmin.MainActivity
import com.ffs.fortunefundadmin.domain.model.InvestmentPlan
import com.ffs.fortunefundadmin.ui.investment_plan.InvestmentPlanViewModel
import dagger.hilt.android.AndroidEntryPoint
import fortunefundadmin.R
import fortunefundadmin.databinding.ActivityAddInvestmentPlanBinding
import java.time.LocalDateTime
import java.util.*


@AndroidEntryPoint
class AddInvestmentPlanActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddInvestmentPlanBinding
    lateinit var viewModel : InvestmentPlanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[InvestmentPlanViewModel::class.java];

        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_investment_plan)

        var investmentPlan = InvestmentPlan(UUID.randomUUID().toString(),binding.planNameEd.text.toString(),
        "Testing.jpg","This is description", Date(),
            Date()
        );
        binding.button.setOnClickListener {
            viewModel.addInvestmentPlan(investmentPlan)
        }


    }




}