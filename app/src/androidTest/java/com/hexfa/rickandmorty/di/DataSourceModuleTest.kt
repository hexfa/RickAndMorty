package com.hexfa.rickandmorty.di

import com.hexfa.rickandmorty.data.datasource.local.SettingDataSource
import com.hexfa.rickandmorty.data.datasource.remote.RemoteCharacterDataSource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class DataSourceModuleTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var remoteCharacterDataSource: RemoteCharacterDataSource
    @Inject
    lateinit var settingDataSource: SettingDataSource

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun `RemoteCharacterDataSourceIsInjected`() {
        assertNotNull(remoteCharacterDataSource)
    }

    @Test
    fun `SettingDataSourceIsInjected`() {
        assertNotNull(settingDataSource)
    }
}