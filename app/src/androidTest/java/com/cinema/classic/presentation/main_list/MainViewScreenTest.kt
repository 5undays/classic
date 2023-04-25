package com.cinema.classic.presentation.main_list

import com.cinema.classic.MainActivity
import com.cinema.classic.di.AppModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
//import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule

@HiltAndroidTest
@UninstallModules(AppModule::class)
class MainViewScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

//    @get:Rule(order = 1)
//    val composeRule = createComposeTest()

    fun mainScreen() {
    }
}