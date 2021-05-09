package com.dasilva.carlos.seriesfan.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat.getDrawable
import com.dasilva.carlos.seriesfan.R
import com.dasilva.carlos.seriesfan.databinding.ViewErrorBinding
import java.net.UnknownHostException

class ErrorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyle, defStyleRes) {

    private val binding = ViewErrorBinding
        .inflate(LayoutInflater.from(context), this, true)

    fun showError(e: Throwable, onTryAgain: () -> Unit) {
        binding.tryAgainButton.setOnClickListener {
            onTryAgain()
        }

        when (e) {
            is UnknownHostException -> setupNetworkError()
        }
    }

    private fun setupNetworkError() {
        binding.run {
            title.text = context.getString(R.string.internet_error_title)
            description.text = context.getString(R.string.internet_error_description)
            icon.setImageDrawable(getDrawable(context, R.drawable.ic_wifi_off))
        }
    }
}
