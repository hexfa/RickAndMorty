package com.hexfa.rickandmorty.data.datasource.local

import android.content.Context
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class SettingDataSourceTest {

    private lateinit var context: Context
    private lateinit var dataSource: SettingDataSource

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()

        val file = context.preferencesDataStoreFile("settings")
        if (file.exists()) file.delete()

        dataSource = SettingDataSource(context)
    }

    @After
    fun tearDown() {
        val file = context.preferencesDataStoreFile("settings")
        if (file.exists()) file.delete()
    }
}
