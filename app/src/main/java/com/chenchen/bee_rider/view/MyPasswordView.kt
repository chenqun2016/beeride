package com.chenchen.bee_rider.view

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import com.chenchen.bee_rider.R

class MyPasswordView : LinearLayout {
    var editTextView: EditText? = null
    var imagebutton: ImageButton? = null

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context) : super(context) {
        init(context)
    }

    var toogle = false
    private fun init(context: Context) {
        inflate(context, R.layout.item_password, this)
        editTextView = findViewById<View>(R.id.password_edit) as EditText
        imagebutton = findViewById<View>(R.id.imagebutton) as ImageButton
        imagebutton!!.setImageResource(R.drawable.icon_biyan)
        post {
            imagebutton!!.setOnClickListener {
                if (toogle) {
                    imagebutton!!.setImageResource(R.drawable.icon_biyan)
                    toogle = false
                    editTextView!!.inputType =
                        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                } else {
                    imagebutton!!.setImageResource(R.drawable.icon_zhengyan)
                    toogle = true
                    editTextView!!.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                }
                editTextView!!.setSelection(
                    editTextView!!.text.toString().trim { it <= ' ' }.length
                )
            }
        }
    }
}