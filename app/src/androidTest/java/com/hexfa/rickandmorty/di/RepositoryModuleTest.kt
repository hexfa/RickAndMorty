package com.hexfa.rickandmorty.di

import com.hexfa.rickandmorty.domain.repository.CharacterRepository
import com.hexfa.rickandmorty.domain.repository.SettingRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class RepositoryModuleTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var characterRepository: CharacterRepository

    @Inject
    lateinit var settingRepository: SettingRepository

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun `CharacterRepositoryIsInjectedSuccessfully`() {
        assertNotNull(characterRepository)
    }

    @Test
    fun `SettingRepositoryIsInjectedSuccessfully`() {
        assertNotNull(settingRepository)
    }
}
