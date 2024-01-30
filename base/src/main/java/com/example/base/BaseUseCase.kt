package com.example.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Base Use Case class
 */
abstract class BaseUseCase<Model, Params> {

    abstract suspend fun buildRequest(params: Params?): Flow<Model>

    suspend fun execute(params: Params?): Flow<Model> {
        return try {
            buildRequest(params)
        } catch (exception: Exception) {
            flow { throw exception }
        }
    }
}