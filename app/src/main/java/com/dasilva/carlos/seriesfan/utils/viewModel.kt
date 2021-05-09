package com.dasilva.carlos.seriesfan.utils

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import org.koin.android.viewmodel.ViewModelOwner
import org.koin.android.viewmodel.koin.getViewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.java.KoinJavaComponent.getKoin

inline fun <reified VM : ViewModel> Fragment.sharedGraphViewModel(
    @IdRes navGraphId: Int,
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
) = lazy {
    getKoin().getViewModel(
        qualifier = qualifier,
        owner = { ViewModelOwner.from(findNavController().getViewModelStoreOwner(navGraphId)) },
        clazz = VM::class,
        parameters = parameters
    )
}
