package com.marvel.example.characterdetails.presentation

import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.Group
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import com.marvel.example.characterdetails.R
import com.marvel.example.characterdetails.di.CharacterDetailsModule
import com.marvel.example.characterdetails.di.DaggerCharacterDetailsComponent
import com.marvel.example.core.presentation.app.GlideApp
import com.marvel.example.core.presentation.UiState
import com.marvel.example.core.presentation.BaseActivity
import com.marvel.example.core.presentation.app.coreComponent
import com.marvel.example.core.presentation.helpers.Activities
import com.marvel.example.core.presentation.helpers.livedata.EventObserver
import com.marvel.example.core.presentation.views.LoadingView
import javax.inject.Inject

/**
 * Copyright (c) 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 19/04/2019
 */
class CharacterDetailsActivity : BaseActivity<CharacterDetailsViewModel>() {
    override val layout: Int = R.layout.activity_character_details

    @Inject
    lateinit var factory: CharacterDetailsViewModelFactory
    override val viewModel: CharacterDetailsViewModel by lazy {
        ViewModelProviders.of(this, factory).get(CharacterDetailsViewModel::class.java)
    }

    private val tlbCharacterDetails by lazy { findViewById<Toolbar>(R.id.tlb_character_details) }
    private val corCharacter by lazy { findViewById<CoordinatorLayout>(R.id.cor_character) }
    private val swpCharacter by lazy { findViewById<SwipeRefreshLayout>(R.id.swp_character) }
    private val imgThumbnail by lazy { findViewById<AppCompatImageView>(R.id.img_thumbnail) }
    private val txtName by lazy { findViewById<AppCompatTextView>(R.id.txt_name) }
    private val txtDescription by lazy { findViewById<AppCompatTextView>(R.id.txt_description) }
    private val grpDetails by lazy { findViewById<Group>(R.id.grp_details) }
    private val loadingCharacter by lazy { findViewById<LoadingView>(R.id.loading_character) }

    override fun inject() {
        val characterId = intent.getIntExtra(Activities.CharacterDetails.EXTRA_CHARACTER_ID, 0)

        DaggerCharacterDetailsComponent
            .builder()
            .coreComponent(coreComponent())
            .characterDetailsModule(CharacterDetailsModule(characterId))
            .build()
            .inject(this)
    }


    override fun setupPage() {
        super.setupPage()
        setSupportActionBar(tlbCharacterDetails)
        title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun setupInputs() {
        super.setupInputs()
        swpCharacter.setOnRefreshListener {
            viewModel.getCharacterDetails()
        }
    }

    override fun setupOutputs() {
        super.setupOutputs()
        viewModel.name.observe(this, Observer { txtName.text = it })

        viewModel.description.observe(this, Observer {
            txtDescription.text = if(it.isEmpty()) {
                getString(R.string.no_description_label)
            } else {
                it
            }
        })

        viewModel.thumbnailUrl.observe(this, Observer {
            GlideApp.with(imgThumbnail)
                .load(it)
                .placeholder(ContextCompat.getDrawable(this, R.color.blue))
                .centerInside()
                .into(imgThumbnail)
        })

        viewModel.getCharacterDetailsUiState.observe(this, EventObserver {
            if (it is UiState.Error) {
                Toast.makeText(this, it.errorMessage, Toast.LENGTH_SHORT).show()
                Snackbar.make(corCharacter, it.errorMessage, Snackbar.LENGTH_SHORT).show()
            } else {
                grpDetails.visibility = if (it is UiState.Complete) View.VISIBLE else View.GONE
                loadingCharacter.visibility =
                    if (it is UiState.Loading) View.VISIBLE else View.GONE
            }
            swpCharacter.isRefreshing = false
        })
    }
}