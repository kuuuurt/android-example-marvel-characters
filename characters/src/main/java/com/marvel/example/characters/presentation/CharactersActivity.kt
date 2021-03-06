package com.marvel.example.characters.presentation

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.marvel.example.characters.R
import com.marvel.example.characters.di.DaggerCharactersComponent
import com.marvel.example.core.presentation.UiState
import com.marvel.example.core.presentation.BaseActivity
import com.marvel.example.core.presentation.app.coreComponent
import com.marvel.example.core.presentation.helpers.livedata.EventObserver
import com.marvel.example.core.presentation.views.EmptyView
import com.marvel.example.core.presentation.views.LoadingView
import javax.inject.Inject

/**
 * Copyright (c) 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 18/04/2019
 */
class CharactersActivity : BaseActivity<CharactersViewModel>() {
    override val layout: Int = R.layout.activity_characters

    @Inject
    lateinit var factory: CharactersViewModelFactory
    override val viewModel: CharactersViewModel by lazy {
        ViewModelProviders.of(this, factory).get(CharactersViewModel::class.java)
    }

    private val charactersAdapter by lazy { CharactersPagedListAdapter() }

    private val swpCharacters by lazy { findViewById<SwipeRefreshLayout>(R.id.swp_characters) }
    private val recCharacters by lazy { findViewById<RecyclerView>(R.id.rec_characters) }
    private val loadingCharacters by lazy { findViewById<LoadingView>(R.id.loading_characters) }
    private val emptyCharacters by lazy { findViewById<EmptyView>(R.id.empty_characters) }

    override fun inject() {
        DaggerCharactersComponent
            .builder()
            .coreComponent(coreComponent())
            .build()
            .inject(this)
    }

    override fun setupPage() {
        super.setupPage()
        recCharacters.adapter = charactersAdapter
    }

    override fun setupInputs() {
        super.setupInputs()
        swpCharacters.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    override fun setupOutputs() {
        super.setupOutputs()
        viewModel.characters.observe(this, Observer {
            charactersAdapter.submitList(it)
        })

        viewModel.charactersUiState.observe(this, EventObserver {
            recCharacters.visibility =
                if (it is UiState.Complete || swpCharacters.isRefreshing) {
                    View.VISIBLE
                } else {
                    View.INVISIBLE
                }
            loadingCharacters.visibility =
                if (it is UiState.Loading && !swpCharacters.isRefreshing) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
            emptyCharacters.visibility =
                if (it is UiState.Error) {
                    View.VISIBLE
                } else {
                    View.GONE
                }

            swpCharacters.isRefreshing = false
        })
    }
}