package com.example.covid_sms_gr.ui.view

import android.content.Context
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.covid_sms_gr.R
import com.example.covid_sms_gr.databinding.TextInputLayoutBinding


class TextInput(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private var hint = ""
    private var binding = TextInputLayoutBinding.inflate(LayoutInflater.from(context), this, true)
    private var isEditable = false
    private lateinit var function: () -> Unit
    private var imeOptions = 0
    private var inputType = 0
    private var maxLines = 0
    private var textAllCaps = true

    var text: String? = null
        get() = run {
            if (binding.textInputEditText.text.toString().isEmpty())
                return@run null
            return binding.textInputEditText.text.toString()
        }
        set(value) {
            field = value
            binding.textInputEditText.setText(value)
        }

    var error: String? = null
        get() = binding.textInputLayout.error.toString()
        set(value) {
            field = value
            binding.textInputLayout.error = value
        }

    init {

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.TextInput)
        try {
            hint = attributes.getString(R.styleable.TextInput_hint).toString()
            isEditable = attributes.getBoolean(R.styleable.TextInput_editable, false)
            inputType = attributes.getInteger(R.styleable.TextInput_text_type, InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL)
            imeOptions = attributes.getInteger(R.styleable.TextInput_imeOptions, EditorInfo.IME_ACTION_NEXT)
            maxLines = attributes.getInteger(R.styleable.TextInput_max_lines, 1)
            textAllCaps = attributes.getBoolean(R.styleable.TextInput_all_caps, false)

        } catch (e: Exception) {
            e.printStackTrace()
        }
        attributes.recycle()


        binding.textInputEditText.imeOptions = imeOptions

        binding.textInputEditText.inputType = inputType

        binding.textInputLayout.hint = hint


        binding.textInputEditText.maxLines = maxLines

        if (textAllCaps)
            binding.textInputEditText.inputType = InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS



        if (!isEditable) {
            binding.textInputEditText.keyListener = null
            binding.textInputEditText.isClickable = false
            binding.textInputEditText.isFocusable = false
            binding.textInputEditText.isLongClickable = false
            binding.textInputEditText.isFocusableInTouchMode = false
            binding.textInputEditText.isCursorVisible = false
            binding.textInputLayout.isClickable = false
            binding.textInputLayout.isFocusable = false
            binding.textInputLayout.isLongClickable = false
            binding.textInputLayout.isFocusableInTouchMode = false

        }


        binding.textInputLayout.setOnClickListener {
            try {
                function.invoke()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        binding.textInputEditText.setOnClickListener {
            try {
                function.invoke()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


    }

    fun onClickListener(function: () -> Unit) {
        this.function = function
    }


}





