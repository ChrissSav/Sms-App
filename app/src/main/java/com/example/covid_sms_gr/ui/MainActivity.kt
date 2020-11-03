package com.example.covid_sms_gr.ui

import com.example.covid_sms_gr.data.base.BaseActivity
import com.example.covid_sms_gr.databinding.ActivityMainBinding


class MainActivity : BaseActivity<ActivityMainBinding>() {



    override fun provideViewBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)


    override fun setUpObservers() {   }

    override fun setUpViews() {   }


}