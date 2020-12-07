package com.example.covid_sms_gr.ui.fragments

import android.util.Log
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.MotionScene
import com.example.covid_sms_gr.R
import com.example.covid_sms_gr.data.base.BaseFragment
import com.example.covid_sms_gr.databinding.FragmentSplashBinding


class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    private var toggle = false

    override fun getViewBinding(): FragmentSplashBinding =
        FragmentSplashBinding.inflate(layoutInflater)

    override fun setUpObservers() {

    }

    override fun setUpViews() {


        binding.motion.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {

            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                navController?.navigate(R.id.action_splashFragment_to_detailsFragment)
            }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
            }

        })
    }
}