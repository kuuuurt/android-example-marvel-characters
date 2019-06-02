package com.marvel.example.core.presentation

/**
 * Copyright 2018, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 20/11/2018
 */
sealed class ActionState {
    object Complete : ActionState()
    object Loading : ActionState()
    class Error(val errorMessage: String) : ActionState()
}