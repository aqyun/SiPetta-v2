package com.dicoding.sipetta.view

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText

class MyEditText : AppCompatEditText, View.OnTouchListener {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        setOnTouchListener(this)

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Do nothing.
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.toString().length < 8) {
                    setError("Password tidak boleh kurang dari 8 karakter", null)
                } else {
                    error = null
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                // Do nothing.
            }
        })
    }

    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        return false
    }
}