package com.marvel.example.core.presentation.app

import android.app.Activity
import android.app.Application
import android.content.Context
import com.marvel.example.core.di.CoreComponent
import com.marvel.example.core.di.DaggerCoreComponent

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 06/02/2019
 */
class MarvelApplication : Application() {

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.create()
    }

    companion object {
        @JvmStatic
        fun coreComponent(context: Context) =
            (context.applicationContext as MarvelApplication).coreComponent
    }
}

fun Activity.coreComponent() = MarvelApplication.coreComponent(this)