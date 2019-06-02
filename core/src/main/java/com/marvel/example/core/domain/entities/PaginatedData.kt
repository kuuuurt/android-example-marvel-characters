package com.marvel.example.core.domain.entities

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 06/02/2019
 */
data class PaginatedData<T>(
    val data: List<T>,
    val totalData: Int
)