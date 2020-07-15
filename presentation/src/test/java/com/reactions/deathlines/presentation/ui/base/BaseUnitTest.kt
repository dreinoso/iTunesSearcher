package com.reactions.deathlines.presentation.ui.base

import android.content.Context
import android.content.res.Resources
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import kotlin.random.Random

@RunWith(MockitoJUnitRunner.Silent::class)
abstract class BaseUnitTest {

    @Mock
    protected lateinit var mockContext: Context

    @Mock
    protected lateinit var mockResources: Resources

    protected var testString: String = "Hey Jude"
    protected var testBoolean: Boolean = Random.nextBoolean()
    protected var testInt: Int = Random.nextInt()
    protected var testLong: Long = Random.nextLong()
    protected var testObject: Any = Any()

    @Before
    open fun setUp() {
        `when`(mockContext.resources).thenReturn(mockResources)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { call -> Schedulers.trampoline() }
    }

    @After
    open fun tearDown() {
    }

}
