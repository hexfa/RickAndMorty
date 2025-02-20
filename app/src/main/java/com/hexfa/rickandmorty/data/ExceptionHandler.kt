package com.hexfa.rickandmorty.data

import com.apollographql.apollo3.exception.ApolloNetworkException

object ExceptionHandler {

    fun getErrorMessage(exc: Exception): String {
        return try {
            val httpException: ApolloNetworkException = exc as ApolloNetworkException
            val errorBody: String = httpException.message.toString()
            handleMessage(errorBody)
        } catch (e: Exception) {
            handleMessage("catch")
        }
    }

    private fun handleMessage(errorBody: String): String {
        return when {
            errorBody.contains("Failed to execute GraphQL http network request")
            -> "Check your internet connection."
//            errorBody.contains("This Social Security Number exists")
//            -> setObserver(R.string.this_social_security_number_exists, _nationalCode)
            else -> "Unknown error"
        }
    }
}