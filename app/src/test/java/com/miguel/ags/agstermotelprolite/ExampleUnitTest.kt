package com.miguel.ags.agstermotelprolite

import com.miguel.ags.agstermotelprolite.injection.DependencyModule
import org.junit.Test

import org.junit.Assert.*
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.inject
import org.koin.test.KoinTest
import org.koin.test.inject

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest : KoinTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    // lazy inject BusinessService into property
    val service : App by inject()

    @Test
    fun myTest() {
        // You can start your Koin configuration
        startKoin { modules(DependencyModule) }

    }
}

