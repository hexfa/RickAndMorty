package com.hexfa.rickandmorty.di

import com.apollographql.apollo3.ApolloClient
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class NetworkModuleTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var apolloClient: ApolloClient

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun `ApolloClientIsInjectedSuccessfully`() {
        assertNotNull(apolloClient)
    }
}