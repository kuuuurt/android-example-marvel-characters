package com.marvel.example.core.ui.characters

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.marvel.example.core.R
import com.marvel.example.core.ui.base.ActionState
import com.marvel.example.core.ui.base.BaseActivity
import com.marvel.example.core.utils.livedata.EventObserver
import com.marvel.example.core.views.EmptyView
import com.marvel.example.core.views.LoadingView

/**
 * Copyright (c) 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 18/04/2019
 */
class CharactersActivity : BaseActivity<CharactersViewModel>() {
    override val viewModel: CharactersViewModel by lazy {
        val factory = CharactersViewModelFactory()
        ViewModelProviders.of(this, factory).get(CharactersViewModel::class.java)
    }
    override val layout: Int = R.layout.activity_characters

    private val charactersAdapter by lazy { CharactersPagedListAdapter() }

    private val swpCharacters by lazy { findViewById<SwipeRefreshLayout>(R.id.swp_characters) }
    private val recCharacters by lazy { findViewById<RecyclerView>(R.id.rec_characters) }
    private val loadingCharacters by lazy { findViewById<LoadingView>(R.id.loading_characters) }
    private val emptyCharacters by lazy { findViewById<EmptyView>(R.id.empty_characters) }

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

        viewModel.charactersState.observe(this, EventObserver {
            recCharacters.visibility =
                if (it is ActionState.Complete || swpCharacters.isRefreshing) {
                    View.VISIBLE
                } else {
                    View.INVISIBLE
                }
            loadingCharacters.visibility =
                if (it is ActionState.Loading && !swpCharacters.isRefreshing) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
            emptyCharacters.visibility =
                if (it is ActionState.Error) {
                    View.VISIBLE
                } else {
                    View.GONE
                }

            swpCharacters.isRefreshing = false
        })
    }
}