package com.marvel.example.characters.ui

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.marvel.example.core.ui.BasePagedListAdapter
import com.marvel.example.characters.R
import com.marvel.example.core.app.GlideApp
import com.marvel.example.core.data.models.character.Character
import com.marvel.example.core.ui.characterdetails.CharacterDetailsActivity
import com.marvel.example.core.utils.Extras
import com.marvel.example.core.views.LoadingView


/**
 * Copyright (c) 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 18/04/2019
 */
class CharactersPagedListAdapter : BasePagedListAdapter<Character>(
    itemsSame = { old, new -> old.id == new.id },
    contentsSame = { old, new -> old == new }
) {
    override val itemLayout: Int = R.layout.list_item_character

    override fun bindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val character = getItem(position)

        holder.itemView.apply {
            val imgThumbnail = findViewById<ImageView>(R.id.img_thumbnail)
            val txtName = findViewById<TextView>(R.id.txt_name)
            val loadingCharacter = findViewById<LoadingView>(R.id.loading_character)

            txtName.visibility = if (character != null) View.VISIBLE else View.INVISIBLE
            loadingCharacter.visibility = if (character == null) View.VISIBLE else View.INVISIBLE

            if (character != null) {
                txtName.text = character.name

                GlideApp.with(context)
                    .load(character.thumbnail.getUrl())
                    .placeholder(ContextCompat.getDrawable(context, R.color.blue))
                    .centerInside()
                    .into(imgThumbnail)

                setOnClickListener {
                    val intent = Intent(context, CharacterDetailsActivity::class.java)
                        .putExtra(Extras.CharacterDetails.CHARACTER_ID, character.id)

                    context.startActivity(intent)
                }
            } else {
                GlideApp.with(context).clear(imgThumbnail)
            }
        }
    }
}