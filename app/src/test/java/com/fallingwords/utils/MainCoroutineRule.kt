package com.fallingwords.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/** This class is used to create a Test Coroutine rule for testing coroutines functionality in unit tests **/
@ExperimentalCoroutinesApi
class MainCoroutineRule(private val dispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()) :
    TestWatcher() {

    /** This function will be called when Test Coroutines is starting **/
    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    /** This function will be called when Test Coroutines is finished **/
    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}