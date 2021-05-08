package com.dasilva.carlos.seriesfan.structure

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import java.lang.IllegalStateException

abstract class BindingFragment<out T>(@LayoutRes layout: Int) : Fragment(layout) {

    abstract val binder: (View) -> T

    private var _binding: T? = null
    val binding: T
        get() = _binding ?: throw IllegalStateException("Null binding at ${this.javaClass}")

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = super.onCreateView(inflater, container, savedInstanceState)?.also {
        _binding = binder(it)
    }
}
