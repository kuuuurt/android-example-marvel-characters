package com.marvel.example.core.domain.base

import java.lang.Exception

/**
 * Copyright (c) 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 19/04/2019
 */
abstract class BaseUseCase<in Params, out Result> {
    @Throws(Exception::class)
    abstract suspend fun execute(params: Params): Result
}