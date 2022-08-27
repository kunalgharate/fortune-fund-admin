package com.ffs.fortunefundadmin.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ffs.fortunefundadmin.MainActivity
import com.ffs.fortunefundadmin.core.Utils
import com.ffs.fortunefundadmin.domain.model.Response
import fortunefundadmin.databinding.FragmentHomeBinding
import ss.com.bannerslider.Slider
import java.util.*

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Slider.init( PicassoImageLoadingService(requireActivity()));
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        binding.bannerSlider1.postDelayed({
//            binding.bannerSlider1.setAdapter(MainSliderAdapter())
//            binding.bannerSlider1.setSelectedSlide(0)
//        }, 1500)


        binding.fab.setOnClickListener {
            startActivity(Intent(requireActivity(),AddInvestmentPlanActivity::class.java))
        }

        var activity = activity as MainActivity

        activity.viewModel.respons.observe(activity)
        {
            when(val booksResponse = it) {
                is Response.Loading ->     Log.d("Dataaa", "loading: ")
                is Response.Success -> {

                    binding.invtPlanRv.adapter =InvestmentPlanAdapter(booksResponse.data)
                }
                is Error -> booksResponse.message?.let { Utils.printMessage(it) }
                else -> {}
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}