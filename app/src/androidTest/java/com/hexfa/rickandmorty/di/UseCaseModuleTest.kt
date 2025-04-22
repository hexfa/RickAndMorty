package com.hexfa.rickandmorty.di

import com.hexfa.rickandmorty.domain.usecase.GetCharactersUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class UseCaseModuleTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var getCharactersUseCase: GetCharactersUseCase

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun `GetCharactersUseCaseIsInjectedSuccessfully`() {
        assertNotNull(getCharactersUseCase)
    }
}
