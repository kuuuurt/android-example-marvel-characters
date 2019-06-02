
/*
 * Copyright 2018 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:JvmName("ActivityHelper")
package com.marvel.example.core.presentation.helpers

import android.content.Context
import android.content.Intent

const val PACKAGE_NAME = "com.marvel.example"

fun Context.intentTo(activity: AddressableActivity) =
    Intent(this, Class.forName(activity.className))

interface AddressableActivity {
    val className: String
}

object Activities {
    object CharacterDetails: AddressableActivity {
        override val className: String = "$PACKAGE_NAME.characterdetails.presentation.CharacterDetailsActivity"
        const val EXTRA_CHARACTER_ID = "character_id"
    }
}