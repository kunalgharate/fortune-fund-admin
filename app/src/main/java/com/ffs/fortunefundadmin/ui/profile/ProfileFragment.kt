package com.ffs.fortunefundadmin.ui.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.ffs.fortunefundadmin.MainActivity
import com.ffs.fortunefundadmin.core.Utils
import com.ffs.fortunefundadmin.domain.model.Response
import fortunefundadmin.R
import fortunefundadmin.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        return binding.root;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
     //   viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        var activity = activity as MainActivity
        activity.viewModel.appUser.observe(activity){

            when(val booksResponse = it) {
                is Response.Loading ->     Log.d("Dataaa", "loading: ")
                is Response.Success -> {
                    binding.user= booksResponse.data
                }
                is Error -> booksResponse.message?.let { Utils.printMessage(it) }
                else -> {}
            }



        }
    }

}