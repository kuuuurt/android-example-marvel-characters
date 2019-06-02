package com.marvel.example.core.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * Base Class of Fragments for handling Fragment Lifecycle Functions
 *
 * @author Kurt Renzo Acosta
 * @since 10/8/17 <version>
 */
abstract class BaseFragment<T : BaseViewModel> : Fragment() {
    abstract val viewModel: T
    abstract val layout: Int

    open fun setupPage(view: View) {}
    open fun subscribeToInputs(view: View) {}
    open fun subscribeToOutputs(view: View) {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPage(view)
        subscribeToInputs(view)
        subscribeToOutputs(view)
    }
}
