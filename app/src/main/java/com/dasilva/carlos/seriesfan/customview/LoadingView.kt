package com.dasilva.carlos.seriesfan.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.dasilva.carlos.seriesfan.R

class LoadingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyle, defStyleRes) {

    init {
        LayoutInflater
            .from(context)
            .inflate(R.layout.view_loading, this, true)
    }
}
