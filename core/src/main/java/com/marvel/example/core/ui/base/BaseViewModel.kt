package com.marvel.example.core.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

/**
 * Base Class of Fragment View Models for implementing Fragment Lifecycle Functions
 *
 * @author Kurt Renzo Acosta
 * @since 10/8/17 <version>
 */
open class BaseViewModel : ViewModel() {
    private val viewModelJob = Job()
    protected val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
