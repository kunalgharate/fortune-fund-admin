package com.ffs.fortunefundadmin.ui.recent_transaction

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ffs.fortunefundadmin.MainActivity
import com.ffs.fortunefundadmin.core.Utils
import com.ffs.fortunefundadmin.domain.model.Response
import com.ffs.fortunefundadmin.ui.home.InvestmentPlanAdapter
import fortunefundadmin.databinding.FragmentDashboardBinding

class RecentTransactionFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val recentTransactionViewModel =
            ViewModelProvider(this).get(RecentTransactionViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root



        var activity = activity as MainActivity

        activity.viewModel.appUsersList.observe(activity)
        {
            when(val booksResponse = it) {
                is Response.Loading ->     Log.d("Dataaa", "loading: ")
                is Response.Success -> {
                    binding.appUsersRv.adapter = AppUsersAdapter(booksResponse.data)
                }
                is Error -> booksResponse.message?.let { Utils.printMessage(it) }
                else -> {}
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}