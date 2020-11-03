package com.example.covid_sms_gr.ui.fragments

import com.example.covid_sms_gr.R
import com.example.covid_sms_gr.data.base.BaseFragment
import com.example.covid_sms_gr.databinding.FragmentSplashBinding
import com.example.covid_sms_gr.util.postDelay


class SplashFragment : BaseFragment<FragmentSplashBinding>() {


    override fun getViewBinding(): FragmentSplashBinding =
        FragmentSplashBinding.inflate(layoutInflater)

    override fun setUpObservers() {

    }

    override fun setUpViews() {

        postDelay(1000) {
            navController?.navigate(R.id.action_splashFragment_to_detailsFragment)
        }
    }
}