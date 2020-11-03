package com.example.covid_sms_gr.util

import android.app.Activity
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


fun Activity.hideKeyboard() {
    val focus = this.currentFocus
    if (focus is EditText) {
        focus.clearFocus()
    }
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
}



fun Fragment.onBackPressed() {
    activity?.onBackPressed()
}

fun postDelay(time: Long, function: () -> Unit) {
    /*Timer().schedule(object : TimerTask() {
        override fun run() {
            function.invoke()
        }
    }, time)*/

    val handler = Handler()
    handler.postDelayed({ function.invoke() }, time)
}
