package com.example.covid_sms_gr.ui.fragments

import android.util.Log
import androidx.constraintlayout.motion.widget.MotionLayout
import com.example.covid_sms_gr.R
import com.example.covid_sms_gr.data.base.BaseFragment
import com.example.covid_sms_gr.databinding.FragmentDetailsBinding
import com.example.covid_sms_gr.util.hideKeyboard

class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {


    override fun getViewBinding(): FragmentDetailsBinding =
        FragmentDetailsBinding.inflate(layoutInflater)

    override fun setUpObservers() {

    }

    override fun setUpViews() {

        setNotEditable()

        binding.motion.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
                hideKeyboard()
            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {

            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                if (p0?.progress != 0f) {
                    setEditable()
                } else {
                    setNotEditable()
                }
            }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
            }

        })

    }


    private fun setNotEditable() {
        binding.firstLastName.editText?.isClickable = false
        binding.firstLastName.editText?.isClickable = false
        binding.firstLastName.editText?.isFocusable = false
        binding.firstLastName.editText?.isLongClickable = false
        binding.firstLastName.editText?.isFocusableInTouchMode = false
        binding.firstLastName.editText?.isCursorVisible = false
        binding.address.editText?.isClickable = false
        binding.address.editText?.isClickable = false
        binding.address.editText?.isFocusable = false
        binding.address.editText?.isLongClickable = false
        binding.address.editText?.isFocusableInTouchMode = false
        binding.address.editText?.isCursorVisible = false

    }

    private fun setEditable() {
        binding.firstLastName.editText?.isClickable = true
        binding.firstLastName.editText?.isClickable = true
        binding.firstLastName.editText?.isFocusable = true
        binding.firstLastName.editText?.isLongClickable = true
        binding.firstLastName.editText?.isFocusableInTouchMode = true
        binding.firstLastName.editText?.isCursorVisible = true
        binding.address.editText?.isClickable = true
        binding.address.editText?.isClickable = true
        binding.address.editText?.isFocusable = true
        binding.address.editText?.isLongClickable = true
        binding.address.editText?.isFocusableInTouchMode = true
        binding.address.editText?.isCursorVisible = true
    }
}