package com.example.covid_sms_gr.util

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.covid_sms_gr.R
import com.tapadoo.alerter.Alerter


fun Activity.createToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Activity.createAlerter(msg: String) {
    Alerter.create(this)
        .setTitle("Προσοχή")
        .setText(msg)
        .setIcon(R.drawable.ic_exclamation_mark)
        .setBackgroundColorRes(R.color.colorPrimary)
        .setDuration(1500)
        .enableSwipeToDismiss() //seems to not work well with OnClickListener
        .show()
}

fun Fragment.hideKeyboard() {
    val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view!!.windowToken, 0)
}


fun Fragment.onBackPressed() {
    activity?.onBackPressed()
}