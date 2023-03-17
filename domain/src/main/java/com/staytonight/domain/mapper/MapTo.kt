package com.staytonight.domain.mapper

interface MapTo<N, M> {
    fun mapTo(): N
}