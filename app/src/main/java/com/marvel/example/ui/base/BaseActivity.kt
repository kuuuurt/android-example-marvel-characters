package com.marvel.example.ui.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem

/**
 * Base Class of Activities for handling Activity Lifecycle Functions from the ViewModel
 *
 * @author Kurt Renzo Acosta
 * @since 10/8/17 <version>
 */
abstract class BaseActivity<T : BaseViewModel> : AppCompatActivity() {
    abstract val viewModel: T
    abstract val layout: Int

    open fun setupPage() {}
    open fun setupInputs() {}
    open fun setupOutputs() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        setContentView(layout)

        setupPage()
        setupInputs()
    }

    override fun onResume() {
        super.onResume()
        setupOutputs()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}

