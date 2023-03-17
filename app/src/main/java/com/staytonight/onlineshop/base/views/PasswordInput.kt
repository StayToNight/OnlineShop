package com.staytonight.onlineshop.base.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.staytonight.onlineshop.R
import com.staytonight.onlineshop.base.extensions.show
import com.staytonight.onlineshop.databinding.InputPasswordBinding

class PasswordInput @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding = InputPasswordBinding.inflate(LayoutInflater.from(context), this)

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.PasswordInput)
        binding.etPassword.hint = typedArray.getText(R.styleable.PasswordInput_hintText)

        val label = typedArray.getText(R.styleable.PasswordInput_label)
        if (!label.isNullOrEmpty()) {
            binding.tvLabel.text = label
            binding.tvLabel.show()
        }
        typedArray.recycle()
    }

    fun getText(): String = binding.etPassword.text.toString()

}