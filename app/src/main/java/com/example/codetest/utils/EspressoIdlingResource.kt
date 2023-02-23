package com.example.codetest.utils

import androidx.test.espresso.idling.CountingIdlingResource

// Used for running tests that involve some pre-loading time
object EspressoIdlingResource {

    private const val RESOURCE_NAME = "GLOBAL"

    @JvmField val countingIdlingResource = CountingIdlingResource(RESOURCE_NAME)

    fun increment() {
        countingIdlingResource.increment()
    }

    fun decrement() {
        if (!countingIdlingResource.isIdleNow)
            countingIdlingResource.decrement()
    }
}